package com.android.sunning.riskpatrol.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.sunning.riskpatrol.RiskPatrolApplication;
import com.android.sunning.riskpatrol.activity.BaseActivity;
import com.android.sunning.riskpatrol.system.HandlerCallBackListener;
import com.android.sunning.riskpatrol.system.HandlerManager;

/**
 * Created by sunning on 15-2-11.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener ,HandlerCallBackListener {
    protected View currentView ;
    protected BaseActivity currentActivity;
    protected HandlerManager.SunnyHandler handler ;
    protected RiskPatrolApplication application ;
    protected Fragment currentFragment ;
    protected LayoutInflater inflater ;
    protected boolean isFirst = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentFragment = this ;
        currentActivity = (BaseActivity) getActivity();
        application = (RiskPatrolApplication) currentActivity.getApplication() ;
        inflater = currentActivity.getLayoutInflater() ;
    }


    protected BaseFragment() {
        handler = HandlerManager.getInstance().getHandler(this) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(currentView == null){
            currentView = inflater.inflate(onCreateViewByLayoutId(),null) ;
        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView) ;
        }
        return currentView;
    }

    protected abstract int onCreateViewByLayoutId();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView() ;
        start() ;
    }

    protected abstract void start();

    public abstract void findView() ;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }

    @Override
    public void obtainMsg(Message msg) {
    }

    public HandlerManager.SunnyHandler getHandler() {
        return handler;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
}
