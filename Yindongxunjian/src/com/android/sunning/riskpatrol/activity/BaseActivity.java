package com.android.sunning.riskpatrol.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sunning.riskpatrol.RiskPatrolApplication;
import com.android.sunning.riskpatrol.custom.dialog.ProgressBarDialog;
import com.android.sunning.riskpatrol.db.DBHelper;
import com.android.sunning.riskpatrol.system.ActivityStackOrderManager;
import com.android.sunning.riskpatrol.system.HandlerCallBackListener;
import com.android.sunning.riskpatrol.system.HandlerManager;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener , HandlerCallBackListener{
	protected RiskPatrolApplication application;
	protected String lastIndexKey;
	public static BaseActivity currentActivity;
	protected HandlerManager.SunnyHandler riHandlerManager;
    protected ActivityStackOrderManager activityStackOrderManager;
    protected DBHelper dbHelper ;


    private boolean isExit ;
    private TextView titleView ;
    private TextView titleRightView ;
    private ImageView backIcon ;
    protected ProgressBarDialog loading ;


    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		application = (RiskPatrolApplication) this.getApplication();
		currentActivity = this;
        loading = new ProgressBarDialog(this) ;
        findView() ;
        findEvent() ;
        riHandlerManager = HandlerManager.getInstance().getHandler(this) ;
        dbHelper = DBHelper.getDbHelperInstance(this) ;
        String clazz = ((Object) this).getClass().getName();
        if(clazz.equals(SplashActivity.class.getName())){
            return;
        }
        changeStack();
        initTitle() ;

	}

    private void initTitle() {
        titleView = (TextView) findViewById(R.id.common_title_tv) ;
        titleRightView = (TextView) findViewById(R.id.title_menu_content) ;
        backIcon = (ImageView) findViewById(R.id.title_back_icon) ;
    }

    protected void setRightListener(View.OnClickListener clickListener){
        Button rightBtn = (Button) findViewById(R.id.title_menu_content_btn);
        rightBtn.setVisibility(View.VISIBLE) ;
        rightBtn.setOnClickListener(clickListener) ;
    }

    protected void setRightListener(int resId , View.OnClickListener clickListener){
        Button rightBtn = (Button) findViewById(R.id.title_menu_content_btn);
        rightBtn.setBackgroundResource(resId) ;
        rightBtn.setVisibility(View.VISIBLE) ;
        rightBtn.setOnClickListener(clickListener) ;
    }

    protected void setTitle(String title){
        if(titleView != null){
            titleView.setText(title) ;
        }
        if(backIcon != null){
            backIcon.setOnClickListener(this) ;
        }
    }

    protected void hideBackIcon(){
        if(backIcon != null){
            backIcon.setVisibility(View.GONE) ;
        }
    }


    protected abstract void findView() ;
    protected abstract void findEvent() ;


	public MainActivity getActivityGroup() {
		return (MainActivity) application.getSession().get(
				RiskPatrolApplication.MAIN_ACTIVITY);
	}

	public void obtainMsg(Message msg) {

	}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_icon:
                performBackPressed();
                break;
        }
    }

    /**
     * <p>执行返回键按下需要实现的功能,这里默认实现了将末端activity销毁，并返回到上一个activity</p>
     * @param
     * @return void
     */
    @SuppressWarnings("deprecation")
    public void performBackPressed() {
        String thisKey = ((Object) this).getClass().getSimpleName() ;
        if(Utils.isRadioRootActivity(thisKey)){
            Timer timer = null ;
            if(!isExit){
                toast("再按一次退出程序") ;
                isExit = true ;
                timer = new Timer() ;
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        isExit = false ;
                    }
                }, 2000) ;
            }else{
                application.exit(BaseActivity.this);
            }
            return;
        }
        MainActivity templateActivity = getActivityGroup();
        Utils.destroy(thisKey, templateActivity.getLocalActivityManager());
        activityStackOrderManager.printStack(getActivityGroup().currentMenuItemId);
        String lastIndexKey = activityStackOrderManager.getLastActivityPath(getActivityGroup().currentMenuItemId) ;
        BaseActivity backToActivity = (BaseActivity)templateActivity.getLocalActivityManager().getActivity(lastIndexKey) ;
        if(backToActivity != null){
            BaseActivity.currentActivity = backToActivity;
            View view = backToActivity.getWindow().getDecorView() ;
            templateActivity.setBody(view, MainActivity.BACK);
            backToActivity.backRefresh();
        }
        else{
            this.finish() ;
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }
    }

    protected void backRefresh(){

    }
    public void show(){
        if(loading != null && !loading.isShowing()){
            loading.show() ;
        }
    }

    public void dismiss(){
        if(loading != null && loading.isShowing()){
            loading.dismiss() ;
        }
    }

    public void changeStack(){
        String clazz = ((Object) this).getClass().getName();
        activityStackOrderManager = ActivityStackOrderManager.getASOMInstance();
        activityStackOrderManager.addActivityPath(getActivityGroup().currentMenuItemId, clazz);
//        activityStackOrderManager.printStack(getActivityGroup().lastView.getId());
    }


    public void toast(String content) {
        if(!TextUtils.isEmpty(content)&&!content.equals("null")){
//            snackBar.show(content,SnackBar.SHORT_SNACK) ;
            this.toast(content, Toast.LENGTH_SHORT) ;
        }
    }
    public void toast(String content , int time) {
        Toast.makeText(currentActivity,content,time).show() ;
    }

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
        overridePendingTransition(R.anim.in_right_to_left,R.anim.roll);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.in_right_to_left,R.anim.roll);
    }

    public void openActivityByResult(Class<?> pClass ,int requestCode , Bundle pBundle){
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivityForResult(intent , requestCode) ;
        overridePendingTransition(R.anim.in_right_to_left,R.anim.roll);

    }

    public void openActivityByResult(Class<?> pClass ,int requestCode){
        this.openActivityByResult(pClass , requestCode , null);
    }

    /**
     * 通过Action启动Activity
     *
     * @param pAction
     */
    public void openActivity(String pAction) {
        openActivity(pAction, null);
    }

    /**
     * 通过Action启动Activity，并且含有Bundle数据
     *
     * @param pAction
     * @param pBundle
     */
    public void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }
}
