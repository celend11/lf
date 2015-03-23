package com.android.sunning.riskpatrol.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.android.sunning.riskpatrol.Const;
import com.example.yindongxunjian.R;
import com.android.sunning.riskpatrol.custom.dialog.BaseDialog;
import com.android.sunning.riskpatrol.custom.dialog.DateDialog;
import com.android.sunning.riskpatrol.custom.dialog.EditTextDialog;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.util.JSONUtils;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by sunning on 15/2/13.
 * 创建检查单
 */
public class CreateCheckPointActivity extends BaseActivity{


    public TextView showDate , projectTitle  , leaderNameTV ;

    private boolean isNewCreate = true ;

    /**
     * 单子的根bean
     */
    public Datum rootDatum  ;

//    //当前选中的站点信息
//    public Site currentSelectSite ;
//
//    //当前选中的责任人
//    public E_AcceptCheckLeader currentSelectLeader ;
//
//    //当前选择的受检区域
//    public E_Area currentArea ;

//    //备注和其他参加人员
//    public String beiZhu , otherPerson ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.create_check_point_layout);
        super.onCreate(savedInstanceState);
        setTitle("创建检查单") ;
        initDatum() ;
    }

    private void initDatum() {
        String title = null;
        Bundle bundle = getIntent().getExtras() ;
        if(bundle != null){
            if(bundle.containsKey(Const.KEY.CHECK_POINT_TITLE)){
                title = bundle.getString(Const.KEY.CHECK_POINT_TITLE) ;
                bundle.remove(Const.KEY.CHECK_POINT_TITLE);
            }
            if(bundle.containsKey(Const.KEY.CHECK_POINT_TYPE_NEW)){
                isNewCreate = true ;
                bundle.remove(Const.KEY.CHECK_POINT_TYPE_NEW);
            }else if(bundle.containsKey(Const.KEY.CHECK_POINT_TYPE_WEB)){
                bundle.remove(Const.KEY.CHECK_POINT_TYPE_WEB);
                isNewCreate = false ;
            }
        }
        if(isNewCreate){
            rootDatum = new Datum() ;
        }else{
            //TODO 读取本地文件，根据点击的Item取出对应保存的本地文件转成JSON并映射到对象中。
        }
        if(!TextUtils.isEmpty(title)){
            setTitle(title) ;
        }
    }

    @Override
    protected void findView() {
        showDate = (TextView) findViewById(R.id.showdate);
        projectTitle = (TextView) findViewById(R.id.accept_check_project_tv_id);
        leaderNameTV = (TextView) findViewById(R.id.create_check_point_leaderName_name_id);
        findViewById(R.id.accept_check_project_parent_id).setOnClickListener(this) ;
        findViewById(R.id.accept_check_area_parent_id).setOnClickListener(this) ;
        findViewById(R.id.accept_check_leader_parent_id).setOnClickListener(this) ;
        findViewById(R.id.accept_check_time_parent_id).setOnClickListener(this) ;
        findViewById(R.id.next_step_btn_id).setOnClickListener(this) ;
        findViewById(R.id.create_check_point_comment).setOnClickListener(this) ;
    }

    @Override
    protected void findEvent() {
    }

    public void setProjectTitle(String projectName){
        if(!TextUtils.isEmpty(projectName)){
            projectTitle.setText(projectName) ;
        }
    }
    public void setLeaderName(String leaderName){
        if(!TextUtils.isEmpty(leaderName)){
            leaderNameTV.setText(leaderName) ;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_check_point_comment:
                BaseDialog baseDialog = new EditTextDialog(this) ;
                baseDialog.show();
                break;
            case R.id.accept_check_project_parent_id:
//                getActivityGroup().startActivityById(AcceptCheckOfProjectActivity.class.getName(),null) ;
                application.getSession().put(Const.KEY.CURRENT_CHECK_POINT , this) ;
                getActivityGroup().startActivityById(AcceptCheckOfProjectListActivity.class.getName(),null) ;
                break;
            case R.id.accept_check_area_parent_id:
                if(rootDatum.getSite() != null){
                    application.getSession().put(Const.KEY.CURRENT_CHECK_POINT , this) ;
                    getActivityGroup().startActivityById(AcceptCheckOfAreaActivity.class.getName(),null) ;
                }else{
                    toast("请先选择受检项目") ;
                }
                break;
            case R.id.accept_check_leader_parent_id:
                if(rootDatum.getSite() != null){
                    application.getSession().put(Const.KEY.CURRENT_CHECK_POINT , this) ;
                    getActivityGroup().startActivityById(AcceptCheckOfLeaderActivity.class.getName(),null) ;
                }else{
                    toast("请选择受检项目") ;
                }
                break;
            case R.id.accept_check_time_parent_id:
                DateDialog dateDialog = new DateDialog(showDate,this) ;
                dateDialog.show() ;
                break;
            case R.id.next_step_btn_id:
                String json = JSONUtils.toJson(rootDatum) ;
                LogUtils.e("@@@@@@"+json) ;
                getActivityGroup().startActivityById(CheckResultActivity.class.getName(),null) ;
                break;
        }
        super.onClick(v);
    }
}
