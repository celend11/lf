package com.android.sunning.riskpatrol.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.Area;
import com.android.sunning.riskpatrol.entity.generate.Attachements;
import com.android.sunning.riskpatrol.entity.generate.Attachment;
import com.android.sunning.riskpatrol.entity.generate.CusPatrolItem;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.entity.generate.JianChaXiangMu;
import com.android.sunning.riskpatrol.entity.generate.PatrolItem;
import com.android.sunning.riskpatrol.entity.generate.RiskElement;
import com.android.sunning.riskpatrol.entity.generate.RiskElements;
import com.android.sunning.riskpatrol.entity.generate.Upload;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.service.AutoSaveService;
import com.android.sunning.riskpatrol.util.JSONUtils;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by sunning on 15/2/13.
 * 检查结果录入
 */
public class CheckResultActivity extends BaseActivity{

    private LayoutInflater layoutInflater ;

    private LinearLayout rootView ;

    private JianChaXiangMu jianChaXiangMu ;

    public static PatrolItem patrolItems ;

    private BroadcastReceiver receiver ;

    private List<Integer> successList = Collections.synchronizedList(new ArrayList<Integer>()) ;

    private int successCount , unUploadCount ;

    @Override
    protected void findView() {
        rootView = (LinearLayout) findViewById(R.id.check_result_content);
    }

    @Override
    protected void findEvent() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.check_result_layout);
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,AutoSaveService.class) ;
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE) ;
        layoutInflater = LayoutInflater.from(this) ;
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                drawView() ;
            }
        } ;
        IntentFilter intentFilter = new IntentFilter() ;
        intentFilter.addAction(Const.BroadcastReceiver.ADD_PROJECT);
        registerReceiver(receiver , intentFilter) ;
        setRightListener(R.drawable.add_area_selector,this) ;
        setTitle("检查结果录入");
        drawView() ;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, final IBinder service) {
            LogUtils.e("连接成功");
            AutoSaveService.AutoSaveBinder binder = (AutoSaveService.AutoSaveBinder)service;
            AutoSaveService bindService = binder.getService();
            bindService.start() ;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.e("连接失败");
        }
    } ;

    public void drawView(){
        rootView.removeAllViews();
        CreateCheckPointActivity createCheckPointActivity = (CreateCheckPointActivity) application.getSession().get(Const.KEY.CURRENT_CHECK_POINT);
        if(createCheckPointActivity == null){
            return ;
        }
        jianChaXiangMu = createCheckPointActivity.rootDatum.getJianChaXiangMu() ;
        View space ;
        if(jianChaXiangMu != null){
            if(jianChaXiangMu.getAreas() != null){
                int size = jianChaXiangMu.getAreas().size() ;
                LinearLayout innerLayout = null;
                for(int i = 0 ; i < size ; i++){
                    innerLayout = (LinearLayout) layoutInflater.inflate(R.layout.check_result_layout_inner , null);
                    Area area = jianChaXiangMu.getAreas().get(i) ;
                    if(area.isSelect || !createCheckPointActivity.isNewCreate){
                        View title = createTitle(area) ;
                        innerLayout.addView(title) ;
                        View content = createContentView(area) ;
                        innerLayout.addView(content) ;
                        rootView.addView(innerLayout) ;
                        layoutInflater.inflate(R.layout.space_view , rootView ,true) ;
                    }
                }
            }
            rootView.addView(createSubmitView()) ;
        }
    }

    private View createContentView(Area acceptCheckArea){
        LinearLayout content = (LinearLayout) layoutInflater.inflate(R.layout.check_result_layout_inner , null);
        if(acceptCheckArea != null && acceptCheckArea.getPatrolItems() != null){
            int size = acceptCheckArea.getPatrolItems().size() ;
            for(int i = 0 ; i < size ; i++){
                View view = createInnerView(acceptCheckArea.getPatrolItems().get(i)) ;
                content.addView(view) ;
            }
        }
        return content ;
    }

    private View createInnerView(final PatrolItem patrolItem){
        LinearLayout content = (LinearLayout) layoutInflater.inflate(R.layout.check_result_layout_inner_item , null);
        TextView title = (TextView) content.findViewById(R.id.check_result_item_name) ;
        TextView result = (TextView) content.findViewById(R.id.check_result_item_result) ;
        RelativeLayout parent = (RelativeLayout) content.findViewById(R.id.check_result_inner_item_parent) ;
        parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                patrolItems = patrolItem ;
                getActivityGroup().startActivityById(CheckResultDetailActivity.class.getName(),null) ;
                LogUtils.e("点击了这个"+patrolItem.getItemName()+"========") ;
            }
        });
        title.setText(patrolItem.getItemName()) ;
        return content ;
    }

    private View createTitle(final Area acceptCheckArea){
        View view = layoutInflater.inflate(R.layout.form_title , null) ;
        TextView title = (TextView) view.findViewById(R.id.check_area_name_check_result);
        ImageView addProject = (ImageView) view.findViewById(R.id.check_result_add_project_id) ;
        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivityGroup().startActivityById(AddCheckProjectActivity.class.getName(), null) ;
            }
        });
        title.setText(acceptCheckArea.getAreaName()) ;
        return view ;
    }

    private View createSubmitView(){
        LinearLayout submitView = (LinearLayout) layoutInflater.inflate(R.layout.check_result_layout_inner_submit , null);
        ImageView submit = (ImageView) submitView.findViewById(R.id.check_result_layout_inner_layout_submit_btn) ;
        LinearLayout submitTime = (LinearLayout) submitView.findViewById(R.id.submit_check_time) ;
        LinearLayout joinPerson = (LinearLayout) submitView.findViewById(R.id.submit_join_person_parent) ;
        TextView timeTV = (TextView) submitView.findViewById(R.id.submit_check_time_tv);
        TextView joinTV = (TextView) submitView.findViewById(R.id.submit_join_person_tv);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unUploadCount = application.unUploadCount ;
                LogUtils.e("一共取到＝＝＝" + unUploadCount + "张图片") ;
                if(unUploadCount == 0){
                    submitCheckList() ;
                }else{
                    uploadAttachment() ;
                }
            }
        });
        return submitView ;
    }

    private void uploadAttachment(){
        CreateCheckPointActivity createCheckPointActivity = (CreateCheckPointActivity) application.getSession().get(Const.KEY.CURRENT_CHECK_POINT);
        if(createCheckPointActivity == null){
            return ;
        }
        Datum datum = createCheckPointActivity.rootDatum ;
        uploadFile(datum) ;
    }

    private void submitCheckList() {
        final CreateCheckPointActivity createCheckPointActivity = (CreateCheckPointActivity) application.getSession().get(Const.KEY.CURRENT_CHECK_POINT);
        HttpInteraction httpInteraction = new HttpInteraction() {
            @Override
            public void response(BaseEntity entity) {

            }
        } ;
        RequestInfo requestInfo = new RequestInfo(Const.InterfaceName.SUBMIT_CHECK_LIST , httpInteraction) {
            @Override
            protected void addParams() {
                String jsonData = JSONUtils.toJson(createCheckPointActivity.rootDatum) ;
                String param = null;
                if(!TextUtils.isEmpty(jsonData)){
                    param = Base64.encodeToString(jsonData.getBytes(), Base64.DEFAULT);
                }
                requestParams.addBodyParameter("jsonData",param);
            }
        } ;
        requestInfo.executePost();
    }

    private void uploadFile(Datum datum) {
        if(datum != null && datum.getJianChaXiangMu() != null && datum.getJianChaXiangMu().getAreas() != null){
            List<Area> areas = datum.getJianChaXiangMu().getAreas() ;
            for(Area area : areas){
                List<PatrolItem> patrolItems  = area.getPatrolItems() ;
                List<CusPatrolItem> cusPatrolItems  = area.getCusPatrolItems() ;
                for(CusPatrolItem cusPatrolItem : cusPatrolItems){
                    RiskElements riskElements = cusPatrolItem.getProblemItems() ;
                    handleRiskElement(riskElements) ;
                }
                for(PatrolItem patrolItem : patrolItems){
                    RiskElements riskElements = patrolItem.getProblemItems() ;
                    handleRiskElement(riskElements);
                }
            }
        }
    }

    private void handleRiskElement(RiskElements riskElements) {

        if(riskElements == null){
            return;
        }

        if(riskElements.getRiskElements() == null){
            return;
        }

        for(RiskElement riskElement : riskElements.getRiskElements()){
            Attachements attachements = riskElement.getAttachements() ;
            for(Attachment attachment : attachements.getAttachements()){
                if(attachment.getFile() != null){
                    updateFile(attachment) ;
                }
            }
        }
    }

    private void updateFile(final Attachment attachment){
        HttpInteraction httpInteraction = new HttpInteraction() {
            @Override
            public void response(BaseEntity entity) {
                Upload upload = (Upload) entity ;
                attachment.setFileUrl(upload.getUrl()) ;
                attachment.setFileName(upload.getFileName()) ;
                successList.add(successCount++) ;
                if(successList.size() == unUploadCount){
                    submitCheckList() ;
                }
            }
        } ;
        RequestInfo requestInfo = new RequestInfo(Const.InterfaceName.UPLOAD_ATTACH , httpInteraction) {
            @Override
            protected void addParams() {
                requestParams.addBodyParameter("file",attachment.getFile());
            }
        } ;
        requestInfo.upload() ;
    }

    @Override
    public void onClick(View v) {
        MainActivity templateActivity = currentActivity.getActivityGroup() ;
        switch (v.getId()){
            case R.id.check_result_inner_item_parent:
                templateActivity.startActivityById(CheckResultDetailActivity.class.getName(), null) ;
                break;
            case R.id.check_result_add_project_id:
                templateActivity.startActivityById(AddCheckProjectActivity.class.getName(), null) ;
                break;
            case R.id.title_menu_content_btn:
                templateActivity.startActivityById(AcceptCheckOfAreaActivity.class.getName(), null) ;
                break;
        }
        super.onClick(v);
    }

    @Override
    public void performBackPressed() {
        super.performBackPressed();
        Utils.destroy(CheckResultActivity.class.getName(), getActivityGroup().getLocalActivityManager());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver) ;
    }
}
