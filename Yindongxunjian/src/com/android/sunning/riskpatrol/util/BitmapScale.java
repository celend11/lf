package com.android.sunning.riskpatrol.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.text.TextUtils;

/**
 * @author marco
 * 
 * 图片工具。从Android 2.2 版本的BitmapUtil中扣出来的，兼容到1.6版本
 */
public class BitmapScale {

    //2M
    private static final long MAX_LENGTH = 2097152;
    //4M
//    private static final long MAX_LENGTH = 4196352;

	public interface Option{
		int NONE = 0x0;
		int SCALE_UP = 0x1;
		int RECYCLE_INPUT = 0x2;
	}

    /**
     * 对拍照或图库获取的图片进行压缩
     */
    public static void decodeBitmapForPic(Context context, String imagePath, String saveImagePath) {
        if (TextUtils.isEmpty(saveImagePath) || TextUtils.isEmpty(imagePath)) {
            return;
        }
        if (new File(imagePath).length() < MAX_LENGTH) {
            saveThumb(context, saveImagePath, decodeFile(new File(imagePath)), imagePath, 60);
        } else {
            saveThumb(context, saveImagePath, decodeBitmapOnWidth(context, imagePath, 1024), imagePath, 50);
        }
        saveThumb(context, getRemoteThumbUri(saveImagePath), decodeBitmapOnWidth(context, imagePath, 120), imagePath, 50);
    }

	/**
	 * 指定长度宽度进行缩放
	 * @param source 源图片
	 * @param targetWidth 目标宽度
	 * @param targetHeight 目标高度
	 * @return 处理后的图片
	 */
	public static Bitmap extract(Bitmap source, int targetWidth, int targetHeight) {
		return extractThumbnail(source, targetWidth, targetHeight, Option.NONE);
	}
	
	/**
	 * 按指定比例缩放
	 * @param source 源图片
	 * @param targetWidth 目标宽度
	 * @param targetHeight 目标高度
	 * @return 处理后的图片
	 */
	public static Bitmap prorate(Bitmap source, int targetWidth, int targetHeight){
		if (source == null) return null;
		
		int srcWidth = source.getWidth();
		int srcHeight = source.getHeight();
		if (srcWidth < srcHeight) {
			targetHeight = srcHeight * targetWidth / srcWidth;
		} else { 
			targetWidth = srcWidth * targetHeight / srcHeight;
		}
		return extractThumbnail(source, targetWidth, targetHeight, Option.NONE);
	}

	private static Bitmap extractThumbnail(Bitmap source, int targetWidth,int targetHeight, int options){
		if (source == null) {
			return null;
		}
		float scale;
		if (source.getWidth() < source.getHeight()) {
			scale = targetWidth / (float) source.getWidth();
		} else {
			scale = targetHeight / (float) source.getHeight();
		}
		Matrix matrix = new Matrix();
		matrix.setScale(scale, scale);
        return transform(matrix, source, targetWidth, targetHeight,Option.SCALE_UP | options);
	}

	private static Bitmap transform(Matrix scaler, Bitmap source,
			int targetWidth, int targetHeight, int options){
		boolean scaleUp = (options & Option.SCALE_UP) != 0;
		boolean recycle = (options & Option.RECYCLE_INPUT) != 0;

		int deltaX = source.getWidth() - targetWidth;
		int deltaY = source.getHeight() - targetHeight;
		if (!scaleUp && (deltaX < 0 || deltaY < 0)) {
			Bitmap b2 = null;
			try {
				b2 = Bitmap.createBitmap(targetWidth, targetHeight,Bitmap.Config.ARGB_8888);
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
			Canvas c = new Canvas(b2);
			int deltaXHalf = Math.max(0, deltaX / 2);
			int deltaYHalf = Math.max(0, deltaY / 2);
			Rect src = new Rect(deltaXHalf, deltaYHalf, deltaXHalf
					+ Math.min(targetWidth, source.getWidth()), deltaYHalf
					+ Math.min(targetHeight, source.getHeight()));
			int dstX = (targetWidth - src.width()) / 2;
			int dstY = (targetHeight - src.height()) / 2;
			Rect dst = new Rect(dstX, dstY, targetWidth - dstX, targetHeight
					- dstY);
			c.drawBitmap(source, src, dst, null);
			if (recycle) {
				source.recycle();
			}
			return b2;
		}
		float bitmapWidthF = source.getWidth();
		float bitmapHeightF = source.getHeight();

		float bitmapAspect = bitmapWidthF / bitmapHeightF;
		float viewAspect = (float) targetWidth / targetHeight;

		if (bitmapAspect > viewAspect) {
			float scale = targetHeight / bitmapHeightF;
			if (scale < .9F || scale > 1F) {
				scaler.setScale(scale, scale);
			} else {
				scaler = null;
			}
		} else {
			float scale = targetWidth / bitmapWidthF;
			if (scale < .9F || scale > 1F) {
				scaler.setScale(scale, scale);
			} else {
				scaler = null;
			}
		}

		Bitmap b1 = null;
		if (scaler != null) {
			try{
			b1 = Bitmap.createBitmap(source, 0, 0, source.getWidth(),source.getHeight(), scaler, true);
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		} else {
			b1 = source;
		}
		if (recycle && b1 != source) {
			source.recycle();
		}
		int dx1 = Math.max(0, b1.getWidth() - targetWidth);
		int dy1 = Math.max(0, b1.getHeight() - targetHeight);
		Bitmap b2 = null;
		try{
		b2 = Bitmap.createBitmap(b1, dx1 / 2, dy1 / 2, targetWidth, targetHeight);
		} catch (OutOfMemoryError e) {
			// TODO: handle exception
		}
		if (b2 != b1) {
			if (recycle || b1 != source) {
				b1.recycle();
			}
		}
		return b2;
	}

    /**
     * 解码图像用来减少内存消耗
     */
    private static Bitmap decodeFile(File f) {
        if (f == null) {
            return null;
        }
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inPreferredConfig = Bitmap.Config.RGB_565;
        o.inDither = false;
        o.inPurgeable = true;
        o.inInputShareable = true;
        o.inTempStorage = new byte[4 * 1024];
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            return BitmapFactory.decodeFileDescriptor(fs.getFD(), null, o);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtil.close(fs);
        }
        return null;
    }

    /**
     * 根据宽度来生成Bitmap
     */
    private static Bitmap decodeBitmapOnWidth(Context context, String imagePath, int width) {
        if (TextUtils.isEmpty(imagePath)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        float realWidth = options.outWidth;
        int scale = (int) (realWidth / width);
        if (scale <= 0) {
            scale = 1;
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
        if (bitmap == null) {
            return null;
        }
        return bitmap;
    }

    /**
     * IM显示缩略图大小
     */
    public static Bitmap decodeThumbBitmap(Context context, String imagePath) {
        return decodeBitmapOnWidth(context, imagePath, 110);
    }

    public static String getRemoteThumbUri(String remoteUri) {
        StringBuilder buf = new StringBuilder(remoteUri);
        buf.insert(remoteUri.lastIndexOf("."), "_thumb");
        return buf.toString();
    }

    /**
     * 保存图片到SD卡
     */
    public static void saveThumb(Context context, String saveImagePath, Bitmap value, String imagePath, int quality) {
        if (TextUtils.isEmpty(saveImagePath)) {
            return;
        }
        File file = new File(saveImagePath);
        if ((file.exists() && !file.isFile()) || value == null) {
            return;
        }
        Bitmap rotatedBitmap = resetRotate(context, value, imagePath);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtil.close(outputStream);
            recycleIfNeeded(rotatedBitmap);
        }
    }

    public static Bitmap resetRotate(Context context, Bitmap bitmap, String filePath) {
        int bmpWidth = bitmap.getWidth();
        int bmpHeight = bitmap.getHeight();
        Matrix matrix = new Matrix();
        int retation = ImageUtil.queryOrientation(context, filePath, System.currentTimeMillis());
        if (retation == -1) {
            retation = Integer.parseInt(ImageUtil.getOrientationFromFile(filePath));
        }
        if (retation == ExifInterface.ORIENTATION_ROTATE_180) {
            matrix.postRotate(180);
        } else if (retation == ExifInterface.ORIENTATION_ROTATE_90) {
            matrix.postRotate(90);
        } else if (retation == ExifInterface.ORIENTATION_ROTATE_270) {
            matrix.postRotate(270);
        }
        Bitmap resizeBitmap = null;
        try {
        	resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight, matrix, true);
		} catch (OutOfMemoryError e) {
			// TODO: handle exception
		}
        return resizeBitmap;
    }

    /**
     * 释放Bitmap内存
     */
    public static void recycleIfNeeded(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }
    
    /**
	 * 
	 * 获取网落图片资源
	 * 
	 * @return
	 */
	public static Bitmap getBitmapFromUrl(String imgUrl) {
		URL url;
		Bitmap bitmap = null;
		try {
			url = new URL(imgUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10 * 1000);
			conn.setConnectTimeout(10 * 1000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream is = conn.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				bitmap = BitmapFactory.decodeStream(bis);
				bis.close();
				return bitmap;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return bitmap;
		}
		return bitmap;
	}
}