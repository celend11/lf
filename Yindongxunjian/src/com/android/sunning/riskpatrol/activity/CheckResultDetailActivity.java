package com.android.sunning.riskpatrol.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.android.sunning.riskpatrol.Const;
import com.example.yindongxunjian.R;
import com.android.sunning.riskpatrol.adapter.Adapter4UploadRecyclerView;
import com.android.sunning.riskpatrol.custom.ActionSheet;
import com.android.sunning.riskpatrol.custom.crop.Crop;
import com.android.sunning.riskpatrol.custom.crop.CropUtil;
import com.android.sunning.riskpatrol.entity.*;
import com.android.sunning.riskpatrol.entity.generate.*;
import com.android.sunning.riskpatrol.entity.generate.RiskElement;
import com.android.sunning.riskpatrol.util.Utils;
import com.lidroid.xutils.util.LogUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sunning on 15/2/15.
 */
public class CheckResultDetailActivity extends BaseActivity implements ActionSheet.ActionSheetListener{

    private boolean standard = true ;

    private ImageView standardView ;

    private LinearLayout rootView ;

    public RiskElements currentSelectRiskElement ;

    private LayoutInflater layoutInflater ;

    private BroadcastReceiver receiver ;

    private List<Adapter4UploadRecyclerView> adapterList = new ArrayList<Adapter4UploadRecyclerView>() ;

    private int position = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.check_result_detail_layout) ;
        super.onCreate(savedInstanceState) ;
        setTitle("检查结果详情填写") ;
        layoutInflater = LayoutInflater.from(this) ;
        initReceiver() ;
        drawView() ;
    }

    private void initReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                drawView() ;
            }
        } ;
        IntentFilter intentFilter = new IntentFilter() ;
        intentFilter.addAction(Const.BroadcastReceiver.ADD_RISK_ELEMENT);
        registerReceiver(receiver , intentFilter) ;
    }

    public void setCurrentSelectRiskElement(RiskElements currentSelectRiskElement) {
        this.currentSelectRiskElement = currentSelectRiskElement;
        CheckResultActivity.patrolItems.setProblemItems(currentSelectRiskElement) ;
    }

    @Override
    protected void findView() {
        rootView = (LinearLayout) findViewById(R.id.add_hidden_parent) ;
        standardView = (ImageView) findViewById(R.id.check_result_detail_standard) ;
        standardView.setOnClickListener(this) ;
        TextView title = (TextView) findViewById(R.id.check_result_detail_title) ;
        if(CheckResultActivity.patrolItems != null){
//            title.setText(CheckResultActivity.patrolItems.itemName) ;
        }
    }

    public void showActionSheet() {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("拍照上传", "本地上传")
                .setCancelableOnTouchOutside(true).setListener(this).show();
    }

    public void drawView(){
        rootView.removeAllViews();
        position = -1 ;
        LinearLayout content = null;
        if(CheckResultActivity.patrolItems != null && CheckResultActivity.patrolItems.getProblemItems() != null){
            int size = CheckResultActivity.patrolItems.getProblemItems().getRiskElements().size() ;
            content = (LinearLayout) layoutInflater.inflate(R.layout.check_result_layout_inner , null) ;
            for(int i = 0 ; i < size ; i++){
                RiskElement riskElement = CheckResultActivity.patrolItems.getProblemItems().getRiskElements().get(i) ;
                riskElement.index = i ;
//                Attachment attachment = CheckResultActivity.patrolItems.attachments(i) ;
                View riskElementView = createRiskElement(riskElement) ;
                content.addView(riskElementView) ;
                layoutInflater.inflate(R.layout.space_view , content ,true) ;
            }
        }

        if(content != null){
            rootView.addView(content) ;
        }
        rootView.addView(createCustomView()) ;
    }

    private View createCustomView() {
        View view = layoutInflater.inflate(R.layout.check_result_detail_layout_bottom_control,null) ;
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.check_result_detail_bottom_add_hidden) ;
        ImageView confirm = (ImageView) view.findViewById(R.id.confirm_detail_for_check_result) ;
        ImageView delete = (ImageView) view.findViewById(R.id.delete_detail_for_check_result) ;
        parent.setOnClickListener(this) ;
        confirm.setOnClickListener(this) ;
        delete.setOnClickListener(this) ;
        return view;
    }

    private View createRiskElement(final RiskElement riskElement) {
        View view = layoutInflater.inflate(R.layout.hidden_detail_layout,null);
        TextView title = (TextView) view.findViewById(R.id.hidden_detail_title);
        ImageView delete = (ImageView) view.findViewById(R.id.hidden_detail_delete_btn) ;
        ImageView uploadImg = (ImageView) view.findViewById(R.id.hidden_detail_upload_img) ;
        ImageView uploadRecord = (ImageView) view.findViewById(R.id.hidden_detail_upload_record) ;
        initRecyclerView(view,riskElement) ;
        EditText userIO = (EditText) view.findViewById(R.id.hidden_detail_detail_userIO) ;
        title.setText(riskElement.getEName()) ;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = riskElement.index ;
                showActionSheet() ;
            }
        });
        uploadRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view ;
    }

    private void initRecyclerView(View view , RiskElement riskElement){
        EUploadImgList data2Adapter = new EUploadImgList() ;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview_horizontal) ;
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this) ;
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLayoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setTag(riskElement) ;
        Adapter4UploadRecyclerView adapter4UploadRecyclerView = new Adapter4UploadRecyclerView(this, data2Adapter, new Adapter4UploadRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(this, ImageBrowserActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(Const.KEY.UPLOADING_IMG, data2Adapter);
//                bundle.putInt("position", position);
//                intent.putExtras(bundle);
//                baseActivity.startActivity(intent);
//                baseActivity.overridePendingTransition(R.anim.zoom_enter, 0);
            }
        });
        adapterList.add(adapter4UploadRecyclerView) ;
        recyclerView.setAdapter(adapter4UploadRecyclerView) ;
    }

    @Override
    protected void findEvent() {

    }

    @Override
    public void performBackPressed() {
        super.performBackPressed();
        Utils.destroy(CheckResultDetailActivity.class.getName(), getActivityGroup().getLocalActivityManager());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete_detail_for_check_result:

                break;
            case R.id.confirm_detail_for_check_result:

                break;
            case R.id.check_result_detail_bottom_add_hidden:
                application.getSession().put(Const.KEY.CURRENT_RESULT_DETAIL , this) ;
                MainActivity templateActivity = currentActivity.getActivityGroup() ;
                templateActivity.startActivityById(AddHiddenDescActivity.class.getName(), null) ;
                break;
            case R.id.check_result_detail_standard:
                standard =! standard ;
                if(standard){
                    standardView.setImageResource(R.drawable.acceptable_quality_icon);
                }else{
                    standardView.setImageResource(R.drawable.unacceptable_quality_icon);
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
                application.mImagePath = Const.Path.CAMERA + UUID.randomUUID().toString();
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
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
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
                    if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        Toast.makeText(this, "SD卡不可用", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    BitmapFactory.Options opt = new BitmapFactory.Options();
                    opt.inPreferredConfig = Bitmap.Config.RGB_565;
                    opt.inPurgeable = true;
                    opt.inInputShareable = true;
                    Bitmap bitmap = BitmapFactory.decodeFile(application.mImagePath, opt);
                    int degree = Utils.getBitmapDegree(application.mImagePath) ;
                    bitmap = Utils.rotateBitmapByDegree(bitmap,degree) ;
                    if (uri == null) {
                        uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
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
                    if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
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
                if(data != null){
                    Uri cropFinishURI = Crop.getOutput(data) ;
                    File file = CropUtil.getFromMediaUri(getContentResolver(), cropFinishURI) ;
                    if(position != -1){
                        if(adapterList.size() >= position){
                            Adapter4UploadRecyclerView adapter = adapterList.get(position) ;
                            EUploadImgList.EUploadImg eUploadImg = new EUploadImgList.EUploadImg() ;
                            eUploadImg.imgUrl = cropFinishURI.toString() ;
                            adapter._data.imgList.add(eUploadImg) ;
                            adapter.notifyDataSetChanged() ;
                        }
                    }
                    LogUtils.e("======"+file.getPath()) ;
                }else{
                    toast("图片上传失败") ;
                }
                break;
        }
    }


    private void startActivity(Uri uri) {
        Uri outputUri = Uri.fromFile(new File(Const.Path.CAMERA, UUID.randomUUID().toString() + ".jpg"));
        new Crop(uri).output(outputUri).asSquare().start(this);
    }

}
