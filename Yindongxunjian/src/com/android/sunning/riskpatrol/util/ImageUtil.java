package com.android.sunning.riskpatrol.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.android.sunning.riskpatrol.Const;

/**
 * 手机照片处理
 */
public class ImageUtil {

	private static final String TAG = "ImagePictureUtil";

	private static final int PIC_MIN = 200;
	private static final int IMG_WIDTH_MAX = 480;
	private static final int IMG_HEIGHT_MIN = 800;

	/**
	 * 拍照后返回
	 */
	public static Intent takeBigPicture(String path) {
		if (path == null) {
			path = newPicturePath();
		}
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, newPictureUri(path));
		return intent;
	}

	/**
	 * 剪切图片
	 */
	public static Intent cropPicture(Uri uri, int aspectX, int aspectY,
			int outWidth, int outHeight, String path) {
		if (path == null) {
			path = newPicturePath();
		}
		return cropPicture(uri, aspectX, aspectY, outWidth, outHeight,
				new File(path));
	}

	/**
	 * 剪切图片
	 */
	public static Intent cropPicture(Uri uri, int aspectX, int aspectY,
			int outWidth, int outHeight, File outputFile) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", aspectX);
		intent.putExtra("aspectY", aspectY);
		intent.putExtra("outputX", outWidth);
		intent.putExtra("outputY", outHeight);
		intent.putExtra("scale", true);
		intent.putExtra("scaleUpIfNeeded", true);
		intent.putExtra("return-data", false);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true);
		return intent;
	}

	/**
	 * 剪切并且选择图片
	 * 
	 * @param outWidth
	 * @param outHeight
	 * @param path
	 * @return
	 */
	public static Intent chooseAndCropPicture(int outWidth, int outHeight,
			String path) {
		if (path == null) {
			path = newPicturePath();
		}
		return chooseAndCropPicture(outWidth, outHeight, new File(path));
	}

	public static Intent chooseAndCropPicture(int outWidth, int outHeight,
			File outputFile) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT)
				.setType("image/*")
				.putExtra("crop", "true")
				.putExtra("aspectX", outWidth)
				.putExtra("aspectY", outHeight)
				.putExtra("outputX", outWidth)
				.putExtra("outputY", outHeight)
				.putExtra("scale", true)
				// 黑边
				.putExtra("scaleUpIfNeeded", true)
				// 黑边
				.putExtra("return-data", false)
				.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile))
				.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
				.putExtra("noFaceDetection", true);
		return intent;
	}

	/**
	 * 预览图片
	 */
	public static Intent previewPicture(Uri uri) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "image/*");
		return intent;
	}

	/**
	 * 选择图片
	 * 
	 * @return
	 */
	public static Intent choosePicture() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		Intent wrapperIntent = Intent.createChooser(intent, null);
		return wrapperIntent;
	}

	public static String retrievePath(Context context, Intent sourceIntent,
			Intent dataIntent) {
		String picPath = null;
		try {
			Uri uri;
			if (dataIntent != null) {
				uri = dataIntent.getData();
				if (uri != null) {
					if (uri.getScheme().startsWith("content")) {
						picPath = queryPath(context, uri);
					} else if (uri.getScheme().startsWith("file")) {
						picPath = uri.getEncodedPath();
					}
				}
				if (!TextUtils.isEmpty(picPath)) {
					return picPath;
				}

				Log.i(TAG, String.format(
						"retrievePath failed from dataIntent:%s, extras:%s",
						dataIntent, dataIntent.getExtras()));
			}

			if (sourceIntent != null) {
				uri = sourceIntent.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
				if (uri != null) {
					if (uri.getScheme().startsWith("file")) {
						picPath = uri.getEncodedPath();
					}
				}
				if (!TextUtils.isEmpty(picPath)) {
					File file = new File(picPath);
					if (!file.exists() || !file.isFile()) {
						Log.i(TAG,
								String.format(
										"retrievePath file not found from sourceIntent path:%s",
										picPath));
					}
				}
			}
			return picPath;
		} finally {
			Log.d(TAG, "retrievePath(" + sourceIntent + "," + dataIntent
					+ ") ret: " + picPath);
		}
	}

	public static String queryPath(Context context, Uri uri) {
		String path = null;
		Cursor actualImagecursor = context.getContentResolver().query(uri,
				new String[] { MediaStore.Images.Media.DATA
				// , MediaStore.Images.Media.ORIENTATION
				}, null, null, null);
		if (actualImagecursor != null) {
			if (actualImagecursor.moveToFirst()) {
				int actualImageColumnIndex = actualImagecursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				/*
				 * int orientationIndex = actualImagecursor
				 * .getColumnIndexOrThrow(MediaStore.Images.Media.ORIENTATION);
				 */
				path = actualImagecursor.getString(actualImageColumnIndex);
			}

			actualImagecursor.close();
		}
		return path;
	}

	public static int queryOrientation(Context context, String filePath,
			long captureTime) {
		int rotation = -1;
		long fileSize = new File(filePath).length();

		Cursor mediaCursor = context.getContentResolver().query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaStore.Images.ImageColumns.ORIENTATION,
						MediaStore.MediaColumns.SIZE },
				MediaStore.MediaColumns.DATE_ADDED + ">=?",
				new String[] { String.valueOf(captureTime / 1000 - 1) },
				MediaStore.MediaColumns.DATE_ADDED + " DESC");

		if (mediaCursor != null) {
			while (mediaCursor.moveToNext()) {
				long size = mediaCursor.getLong(1);
				if (size == fileSize) {
					rotation = mediaCursor.getInt(0);
					break;
				}
			}
			mediaCursor.close();
		}
		return rotation;
	}

	public static String getOrientationFromFile(String filePath) {
		try {
			ExifInterface exif = new ExifInterface(filePath);
			String exifOrientation = exif
					.getAttribute(ExifInterface.TAG_ORIENTATION);
			return exifOrientation;
		} catch (IOException e) {
		}
		return null;
	}

	private static Uri newPictureUri(String path) {
		return Uri.fromFile(new File(path));
	}

	private static String newPicturePath() {
		return Const.Path.CAMERA + "/" + System.currentTimeMillis()
				+ ".jpg";
	}

	/**
	 * 创建压缩图
	 */
	public static String generateCompressedPicture(Context context,
			String oldPath) {
		String path = newPicturePath();
		BitmapScale.decodeBitmapForPic(context, oldPath, path);
		return path;
	}

	/**
	 * 保存照片
	 * 
	 * @return File
	 */
	public static String onSaveCamera(Context context) {
		createSDCardFileDir();
		// 拍照
		String tempPath = Const.Path.CAMERA + Const.Path.CAMERA_TEMP;
		String savePath = Const.Path.CAMERA+"/"+System.currentTimeMillis() + ".jpg";
		
		File file = new File(tempPath);
		BitmapFactory.Options options = new BitmapFactory.Options();
		if (!file.exists() && file.length() / 1024 > 1024) {
			options.inSampleSize = 2;
		}
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Bitmap photo = BitmapFactory.decodeFile(tempPath, options);
			Log.i("info", "photo1:"+photo);
			if (photo != null) {
				photo.compress(Bitmap.CompressFormat.JPEG, 50, baos);
			}
			Log.i("info", "photo2:"+photo);
			long size = getBitmapsize(photo);
			Log.i("info", "size:"+size);
			if (photo != null && size/1024 > PIC_MIN) {
				photo = compSize(photo);
//				photo = BitmapScale.prorate(photo, IMG_WIDTH_MAX, IMG_HEIGHT_MIN);
			}
			Log.i("info", "photo3:"+photo);
			if (photo != null) {
				photo = BitmapScale.resetRotate(context, photo, tempPath);
			}
			Log.i("info", "photo4:"+photo);
			return compressBmpToFile(photo, new File(savePath));
		} catch (Exception e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 保存相册图片
	 * 
	 * @param mContext
	 * @param data
	 * @return
	 */
	public static String onSavePhoto(Context mContext, Intent data) {
		createSDCardFileDir();
		// 相册
		Uri originalUri = data.getData(); // 获得图片的uri
		Log.i("info", "orginalUri:"+originalUri);
		ContentResolver resolver = mContext.getContentResolver();
		try {
			Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
			Log.i("info", "photo:"+photo);
			String savePath = Const.Path.CAMERA+"/"+System.currentTimeMillis() + ".jpg";
			
			if (photo != null && getBitmapsize(photo)/1024 > PIC_MIN) {
//				photo = BitmapScale.prorate(photo, IMG_WIDTH_MAX, IMG_HEIGHT_MIN);
				photo = compSize(photo);
			}
			if (photo == null) {
				return null;
			}
			return compressBmpToFile(photo, new File(savePath));
		} catch (Exception e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 获取链接后缀名
	 * 
	 * @return 链接后缀名是否为图片地址
	 */
	public static Boolean isImageUrl(String picUrl) {
		if (picUrl != null) {
			int typeIndex = picUrl.lastIndexOf(".");
			if (typeIndex != -1) {
				return isImage(picUrl.substring(typeIndex + 1).toLowerCase());
			}
		}
		return false;
	}

	/**
	 * 根据后缀名判断是否是图片文件
	 * 
	 * @param type
	 * @return 是否是图片结果true or false
	 */
	public static boolean isImage(String type) {
		if (type != null
				&& (type.equals("jpg") || type.equals("gif")
						|| type.equals("png") || type.equals("jpeg")
						|| type.equals("bmp") || type.equals("wbmp")
						|| type.equals("ico") || type.equals("jpe"))) {
			return true;
		}
		return false;
	}

	/**
	 * 质量压缩图片
	 * 
	 * @param bmp
	 * @param file
	 */
	public static String compressBmpToFile(Bitmap bmp, File file) {
		if (bmp == null) {
			return null;
		}
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int options = 100;
			bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
			while (baos.toByteArray().length / 1024 > PIC_MIN) {
				baos.reset();
				options -= 10;
				bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
			}
			
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(baos.toByteArray());
			fos.flush();
			fos.close();
			Log.i("info", "--->path:"+file.getAbsolutePath());
			return file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BitmapScale.recycleIfNeeded(bmp);
		}
		return null;
	}

	@SuppressLint("NewApi")
	public static long getBitmapsize(Bitmap bitmap) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			return bitmap.getByteCount();
		}
		return bitmap.getRowBytes() * bitmap.getHeight();

	}
	
	public static Bitmap getRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			left = 0;
			top = 0;
			right = width;
			bottom = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);

		paint.setAntiAlias(true);// 设置画笔无锯齿

		canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas
		paint.setColor(color);

		// 以下有两种方法画圆,drawRounRect和drawCircle
		// canvas.drawRoundRect(rectF, roundPx, roundPx, paint);//
		// 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。
		canvas.drawCircle(roundPx, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452
		canvas.drawBitmap(bitmap, src, dst, paint); // 以Mode.SRC_IN模式合并bitmap和已经draw了的Circle

		return output;
	}
	
	private static void createSDCardFileDir(){
		File file = new File(Const.Path.APP_DIR);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	/**
	 * 只压缩图片尺寸
	 * 
	 * @param image
	 * @return
	 */
	public static Bitmap compSize(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 50, baos);
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		newOpts.inPreferredConfig = Config.RGB_565;// 降低图片从ARGB888到RGB565
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

		return bitmap;
	}

}