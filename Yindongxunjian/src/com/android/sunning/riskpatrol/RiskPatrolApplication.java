package com.android.sunning.riskpatrol;

import android.app.Application;
import android.content.Intent;
import com.android.sunning.riskpatrol.activity.BaseActivity;
import com.android.sunning.riskpatrol.activity.MainActivity;
import com.android.sunning.riskpatrol.db.DBHelper;
import com.android.sunning.riskpatrol.system.ActivityStackOrderManager;
import com.android.sunning.riskpatrol.system.HandlerManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunning on 15/2/7.
 */
public class RiskPatrolApplication extends Application{

    public static final String MAIN_ACTIVITY ="mainActivity";

    public String mImagePath ;

    public int mImageType = -1;

    private RiskPatrolApplication application;

    private Map<String,Object> session = new HashMap<String, Object>();

    public Map<String,Object> getSession(){
        return session;
    }


    /**
     * 完全退出
     *
     * */
    @SuppressWarnings("deprecation")
    public void exit(BaseActivity activity){
        HandlerManager.getInstance().destroyHandler();
        MainActivity mf = (MainActivity)session.get(MAIN_ACTIVITY);
        mf.finish();
        mf.getLocalActivityManager().dispatchDestroy(true);
        session.clear();
        application = (RiskPatrolApplication) activity.getApplication() ;
        application.getSession().clear() ;
        ActivityStackOrderManager.getASOMInstance().destroy();
    }



}
