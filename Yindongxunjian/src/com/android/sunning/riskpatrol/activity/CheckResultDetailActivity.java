package com.android.sunning.riskpatrol.activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.custom.ActionSheet;
import com.android.sunning.riskpatrol.custom.crop.Crop;
import com.android.sunning.riskpatrol.custom.crop.CropUtil;
import com.android.sunning.riskpatrol.custom.dialog.RecordDialog;
import com.android.sunning.riskpatrol.entity.AttachmentType;
import com.android.sunning.riskpatrol.entity.generate.Attachements;
import com.android.sunning.riskpatrol.entity.generate.Attachment;
import com.android.sunning.riskpatrol.entity.generate.RiskElement;
import com.android.sunning.riskpatrol.entity.generate.RiskElements;
import com.android.sunning.riskpatrol.util.ImageLoader;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by sunning on 15/2/15.
 */
public class CheckResultDetailActivity extends BaseActivity implements
		ActionSheet.ActionSheetListener {

	private boolean standard = true;

	private ImageView standardView;

	private LinearLayout rootView;

	public RiskElements currentSelectRiskElement;

	private LayoutInflater layoutInflater;

	private BroadcastReceiver receiver;

	private List<LinearLayout> viewList = new ArrayList<LinearLayout>();

	private SparseArray<Attachements> attachArray = new SparseArray<Attachements>();

	private int position = -1;

	private int unUploadCount;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.check_result_detail_layout);
		super.onCreate(savedInstanceState);
		setTitle("检查结果详情填写");
		layoutInflater = LayoutInflater.from(this);
		initReceiver();
		drawView();
	}

	private void initReceiver() {
		receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				drawView();
			}
		};
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Const.BroadcastReceiver.ADD_RISK_ELEMENT);
		registerReceiver(receiver, intentFilter);
	}

	public void setCurrentSelectRiskElement(
			RiskElements currentSelectRiskElement) {
		this.currentSelectRiskElement = currentSelectRiskElement;
		CheckResultActivity.patrolItems
				.setProblemItems(currentSelectRiskElement);
	}

	@Override
	protected void findView() {
		rootView = (LinearLayout) findViewById(R.id.add_hidden_parent);
		standardView = (ImageView) findViewById(R.id.check_result_detail_standard);
		standardView.setOnClickListener(this);
		TextView title = (TextView) findViewById(R.id.check_result_detail_title);
		if (CheckResultActivity.patrolItems != null) {
			// title.setText(CheckResultActivity.patrolItems.itemName) ;
		}
	}

	public void showActionSheet() {
		ActionSheet.createBuilder(this, getSupportFragmentManager())
				.setCancelButtonTitle("取消")
				.setOtherButtonTitles("拍照上传", "本地上传")
				.setCancelableOnTouchOutside(true).setListener(this).show();
	}

	public void drawView() {
		rootView.removeAllViews();
		position = -1;
		LinearLayout content = null;
		if (CheckResultActivity.patrolItems != null
				&& CheckResultActivity.patrolItems.getProblemItems() != null) {
			int size = CheckResultActivity.patrolItems.getProblemItems()
					.getRiskElements().size();
			content = (LinearLayout) layoutInflater.inflate(
					R.layout.check_result_layout_inner, null);
			for (int i = 0; i < size; i++) {
				RiskElement riskElement = CheckResultActivity.patrolItems
						.getProblemItems().getRiskElements().get(i);
				riskElement.index = i;
				View riskElementView = createRiskElement(riskElement, i);
				content.addView(riskElementView);
				layoutInflater.inflate(R.layout.space_view, content, true);
			}
		}

		if (content != null) {
			rootView.addView(content);
		}
		rootView.addView(createCustomView());
	}

	private View createCustomView() {
		View view = layoutInflater.inflate(
				R.layout.check_result_detail_layout_bottom_control, null);
		RelativeLayout parent = (RelativeLayout) view
				.findViewById(R.id.check_result_detail_bottom_add_hidden);
		ImageView confirm = (ImageView) view
				.findViewById(R.id.confirm_detail_for_check_result);
		ImageView delete = (ImageView) view
				.findViewById(R.id.delete_detail_for_check_result);
		parent.setOnClickListener(this);
		confirm.setOnClickListener(this);
		delete.setOnClickListener(this);
		return view;
	}

	private View createRiskElement(final RiskElement riskElement, int index) {
		View view = layoutInflater.inflate(R.layout.hidden_detail_layout, null);
		LinearLayout imageBrowser = (LinearLayout) view
				.findViewById(R.id.ly_bt_content);
		if (imageBrowser != null) {
			viewList.add(imageBrowser);
		}
		Attachements attachements = new Attachements();
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		attachements.setAttachements(attachmentList);
		attachArray.put(index, attachements);
		TextView title = (TextView) view.findViewById(R.id.hidden_detail_title);
		ImageView delete = (ImageView) view
				.findViewById(R.id.hidden_detail_delete_btn);
		ImageView uploadImg = (ImageView) view
				.findViewById(R.id.hidden_detail_upload_img);
		ImageView uploadRecord = (ImageView) view
				.findViewById(R.id.hidden_detail_upload_record);
		EditText userIO = (EditText) view
				.findViewById(R.id.hidden_detail_detail_userIO);
		title.setText(riskElement.getEName());
		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		uploadImg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				position = riskElement.index;
				showActionSheet();
			}
		});
		uploadRecord.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				position = riskElement.index;
				showRecordDialog();
			}
		});
		return view;
	}

	private void showRecordDialog() {
		final RecordDialog record = new RecordDialog(this);
		record.setRecording(false);
		record.btn1(new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (!record.isRecording()) {
					LogUtils.e("录音开始");
					record.start();
					record.setRecording(true);
				}
			}
		});
		record.btn2(new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (record.isRecording()) {
					LogUtils.e("录音结束");
					record.stop();
					record.setRecording(false);
					record.dismiss();
				}
			}
		});
		record.show();
	}

	@Override
	protected void findEvent() {

	}

	@Override
	public void performBackPressed() {
		super.performBackPressed();
		Utils.destroy(CheckResultDetailActivity.class.getName(),
				getActivityGroup().getLocalActivityManager());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.delete_detail_for_check_result:

			break;
		case R.id.confirm_detail_for_check_result:
			application.unUploadCount = unUploadCount;
			LogUtils.e("待上传一共＝＝＝" + unUploadCount + "张图片");
			performBackPressed();
			break;
		case R.id.check_result_detail_bottom_add_hidden:
			application.getSession().put(Const.KEY.CURRENT_RESULT_DETAIL, this);
			 MainActivity templateActivity =currentActivity.getActivityGroup() ;
			 templateActivity.startActivityById(AddHiddenDescActivity.class.getName(),
			 null) ;
			break;
		case R.id.check_result_detail_standard:
			standard = !standard;
			if (standard) {
				standardView
						.setImageResource(R.drawable.acceptable_quality_icon);
			} else {
				standardView
						.setImageResource(R.drawable.unacceptable_quality_icon);
			}
			break;
		}
		super.onClick(v);
	}

	@Override
	public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

	}

	@Override
	public void onOtherButtonClick(ActionSheet actionSheet, int index) {
		Intent intent;
		switch (index) {
		case 0:
			intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			application.mImagePath = Const.Path.CAMERA
					+ UUID.randomUUID().toString();
			File file = new File(application.mImagePath);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {

				}
			}
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
			startActivityForResult(intent, index);
			break;
		case 1:
			intent = new Intent(Intent.ACTION_PICK, null);
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					"image/*");
			startActivityForResult(intent, index);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Uri uri = null;
		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					Toast.makeText(this, "SD卡不可用", Toast.LENGTH_SHORT).show();
					return;
				}
				BitmapFactory.Options opt = new BitmapFactory.Options();
				opt.inPreferredConfig = Bitmap.Config.RGB_565;
				opt.inPurgeable = true;
				opt.inInputShareable = true;
				Bitmap bitmap = BitmapFactory.decodeFile(
						application.mImagePath, opt);
				int degree = Utils.getBitmapDegree(application.mImagePath);
				bitmap = Utils.rotateBitmapByDegree(bitmap, degree);
				if (uri == null) {
					uri = Uri.parse(MediaStore.Images.Media.insertImage(
							getContentResolver(), bitmap, null, null));
				}
				startActivity(uri);
			} else {
				Toast.makeText(this, "取消拍照", Toast.LENGTH_SHORT).show();
			}
			break;

		case 1:
			if (data == null) {
				Toast.makeText(this, "取消上传", Toast.LENGTH_SHORT).show();
				return;
			}
			if (resultCode == RESULT_OK) {
				if (uri == null) {
					uri = data.getData();
				}
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					Toast.makeText(this, "SD卡不可用", Toast.LENGTH_SHORT).show();
					return;
				}
				if (uri != null) {
					startActivity(uri);
				}
			} else {
				Toast.makeText(this, "获取错误", Toast.LENGTH_SHORT).show();
			}
			break;
		case Crop.REQUEST_CROP:
			if (data != null) {
				Uri cropFinishURI = Crop.getOutput(data);
				File file = CropUtil.getFromMediaUri(getContentResolver(),
						cropFinishURI);
				if (position != -1) {
					viewList.get(position).addView(insertView(cropFinishURI));
					Attachment attachment = new Attachment();
					attachment.attachmentType = AttachmentType.IMAGE;
					attachment.setFile(file);
					attachArray.get(position).getAttachements().add(attachment);
					CheckResultActivity.patrolItems.getProblemItems()
							.getRiskElements().get(position)
							.setAttachements(attachArray.get(position));
					unUploadCount++;
				}

				// if(position != -1){
				// if(adapterList.size() >= position){
				// Adapter4UploadRecyclerView adapter =
				// adapterList.get(position) ;
				//
				// EUploadImgList.EUploadImg eUploadImg = new
				// EUploadImgList.EUploadImg() ;
				//
				// eUploadImg.imgUrl = cropFinishURI.toString() ;
				//
				// adapter._data.imgList.add(eUploadImg) ;
				//
				// Attachment attachment = new Attachment() ;
				//
				// attachment.setFileName(eUploadImg.fileName);
				//
				// attachment.setFileUrl(eUploadImg.imgUrl) ;
				//
				// for(EUploadImgList.EUploadImg eUploadImg1 :
				// adapter._data.imgList){
				// eUploadImg1.imgUrl
				// }
				//
				// Attachements attachements = new Attachements() ;
				// // attachements.setAttachements() ;
				//
				//
				// //
				// CheckResultActivity.patrolItems.getProblemItems().getRiskElements().get(position).setAttachements();
				// adapter.notifyDataSetChanged() ;
				// }
				// }
				LogUtils.e("======" + file.getPath());
			} else {
				toast("图片获取失败");
			}
			break;
		}
	}

	private View insertView(Uri cropFinishURI) {
		View child = getLayoutInflater().inflate(
				R.layout.com_mark_utils_picsel_bottom_item, null);
		ImageView img = (ImageView) child.findViewById(R.id.id_item_image);
		ImageLoader imageLoader = new ImageLoader();
		imageLoader.display(img, cropFinishURI.toString());
		// ImageLoader2.getInstance(1,
		// com.android.sunning.riskpatrol.util.ImageLoader2.Type.LIFO).loadImage(cropFinishURI.toString()
		// , img);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				Utils.dip2px(this, 77), Utils.dip2px(this, 77));
		child.setLayoutParams(params);

		RelativeLayout.LayoutParams paramsr = new RelativeLayout.LayoutParams(
				Utils.dip2px(this, 57), Utils.dip2px(this, 57));
		paramsr.topMargin = Utils.dip2px(this, 10);
		paramsr.leftMargin = Utils.dip2px(this, 10);
		img.setLayoutParams(paramsr);
		child.findViewById(R.id.id_item_close).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// ly_bt_content.removeView((View) view.getParent());
						// mSelectedImage.remove(uri);
						// mAdapter.notifyDataSetChanged();
						// id_sel_count.setText(mSelectedImage.size() + "/"
						// + MAX_SEL_ENABLE);
					}
				});
		return child;
	}

	private void startActivity(Uri uri) {
		Uri outputUri = Uri.fromFile(new File(Const.Path.CAMERA, UUID
				.randomUUID().toString() + ".jpg"));
		new Crop(uri).output(outputUri).asSquare().start(this);
	}
}
