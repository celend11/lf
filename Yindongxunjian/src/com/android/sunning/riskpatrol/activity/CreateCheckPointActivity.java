package com.android.sunning.riskpatrol.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.custom.dialog.DateDialog;
import com.android.sunning.riskpatrol.custom.dialog.EditTextDialog;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.entity.generate.login.Login;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/13.
 * 创建检查单
 */
public class CreateCheckPointActivity extends BaseActivity{

    public TextView showDate , projectTitle  ,showarea, leaderNameTV , subTitleTV ,showCanjiarenyuan,showbeizhu;

    public boolean isNewCreate = true ;
    /**
     * 单子的根bean
     */
    public Datum rootDatum ;
    public int i=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.create_check_point_layout);
        super.onCreate(savedInstanceState);
        setTitle("创建检查单") ;
        initDatum() ;
        application.getSession().put(Const.KEY.CURRENT_CHECK_POINT , this) ;
    }

    private void initDatum() {
        String subTitle = null;
        Bundle bundle = getIntent().getExtras() ;
        if(bundle != null){
            if(bundle.containsKey(Const.KEY.CHECK_POINT_TITLE)){
                subTitle = bundle.getString(Const.KEY.CHECK_POINT_TITLE);
                bundle.remove(Const.KEY.CHECK_POINT_TITLE);
            }
            if(bundle.containsKey(Const.KEY.CHECK_POINT_TYPE_NEW)){
                isNewCreate = true ;
                bundle.remove(Const.KEY.CHECK_POINT_TYPE_NEW);
            }else{
                isNewCreate = false ;
            }
        }
        if(isNewCreate){
            rootDatum = new Datum() ;
            rootDatum.setInspectTime(Utils.formatDateYYYYMMDD(System.currentTimeMillis())) ;
            Login login = dbHelper.queryCurrentLogin() ;
            rootDatum.setCreatorName(login.getName()) ;
            rootDatum.setCreatorID(login.getID()) ;
        }else{
            //根据点击的Item取出对应保存的本地文件转成JSON并映射到对象中。
            rootDatum = (Datum) application.getSession().get(Const.KEY.CURRENT_EDIT_DATUM);
            rootDatum.setInspectTime(Utils.formatJSONDate(rootDatum.getInspectTime()));
            if(rootDatum != null){
                setProjectTitle(rootDatum.getSite().getSiteName()) ;
                setLeaderName(rootDatum.getShouJianFuZeRen().getShouJianFuZeRenName()) ;
                showDate.setText(Utils.formatJSONDate(rootDatum.getCreateTime())) ;
            }
            rootDatum.setCreateTime(Utils.formatJSONDate(rootDatum.getCreateTime()));
        }
        if(!TextUtils.isEmpty(subTitle)){
            subTitleTV.setText(subTitle);
        }
    }

    @Override
    protected void findView() {
    	showarea=(TextView) findViewById(R.id.creat_area);
        showDate = (TextView) findViewById(R.id.showdate);
        projectTitle = (TextView) findViewById(R.id.accept_check_project_tv_id);
        leaderNameTV = (TextView) findViewById(R.id.create_check_point_leaderName_name_id);
        subTitleTV = (TextView) findViewById(R.id.create_check_subtitle) ;
        showCanjiarenyuan=(TextView) findViewById(R.id.canjiarenyuan);
        showbeizhu=(TextView) findViewById(R.id.accept_beizhu);
        findViewById(R.id.accept_check_project_parent_id).setOnClickListener(this) ;
        findViewById(R.id.accept_check_area_parent_id).setOnClickListener(this) ;
        findViewById(R.id.accept_check_leader_parent_id).setOnClickListener(this) ;
        findViewById(R.id.accept_check_time_parent_id).setOnClickListener(this) ;
        findViewById(R.id.next_step_btn_id).setOnClickListener(this) ;
        findViewById(R.id.create_check_point_comment).setOnClickListener(this) ;
        findViewById(R.id.canjia_renyuan).setOnClickListener(this);
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
                final EditTextDialog baseDialog = new EditTextDialog(this) ;
                baseDialog.setButton1("确定",new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showbeizhu.setText(baseDialog.getText());
						baseDialog.dismiss();
					}
				});
                baseDialog.setButton2("取消",new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						baseDialog.dismiss();
					}
				});
                baseDialog.show();
                break;
            case R.id.accept_check_project_parent_id:
//                getActivityGroup().startActivityById(AcceptCheckOfProjectActivity.class.getName(),null) ;
                getActivityGroup().startActivityById(AcceptCheckOfProjectListActivity.class.getName(),null) ;
                break;
            case R.id.accept_check_area_parent_id:
                if(rootDatum.getSite() != null){
                    getActivityGroup().startActivityById(AcceptCheckOfAreaActivity.class.getName(),null) ;
                }else{
                    toast("请先选择受检项目") ;
                }
                break;
            case R.id.accept_check_leader_parent_id:
                if(rootDatum.getSite() != null){
                    getActivityGroup().startActivityById(AcceptCheckOfLeaderActivity.class.getName(),null) ;
                }else{
                    toast("请选择受检项目") ;
                }
                break;
            case R.id.canjia_renyuan:
            	if(rootDatum.getCanJianRenYuan()!=null){
            		showCanjiarenyuan.setText(rootDatum.getCanJianRenYuan());
            	}else{
            		final EditTextDialog dialoge = new EditTextDialog(this) ;
                    dialoge.setButton1("确定",new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							showCanjiarenyuan.setText(dialoge.getText());
							rootDatum.setCanJiaRenYuanInput(dialoge.getText());
							dialoge.dismiss();
						}
					});
                    dialoge.setButton2("取消",new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialoge.dismiss();
							
						}
					});
                    dialoge.show();
            	}
            	break;
            case R.id.accept_check_time_parent_id:
                DateDialog dateDialog = new DateDialog(showDate,this , new DateDialog.DateSetFinish() {
                    @Override
                    public void dataFinish(String date) {
                        rootDatum.setCreateTime(date) ;
                    }
                }) ;
                dateDialog.show() ;
                break;
            case R.id.next_step_btn_id:
//                String json = JSONUtils.toJson(rootDatum) ;
//                LogUtils.e("@@@@@@"+json) ;
                getActivityGroup().startActivityById(CheckResultActivity.class.getName(),null) ;
                break;
        }
        super.onClick(v);
    }

    @Override
    public void performBackPressed() {
        super.performBackPressed();
        Utils.destroy(CreateCheckPointActivity.class.getName(), getActivityGroup().getLocalActivityManager());
    }


}
