package com.android.sunning.riskpatrol.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.RiskPatrolApplication;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.local.LocalHelper;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by sunning on 15/3/5.
 * 自动保存
 */
public class AutoSaveService extends Service{

    private Timer timer = new Timer() ;

    RiskPatrolApplication application = (RiskPatrolApplication) getApplicationContext() ;

    LocalHelper localHelper ;

    @Override
    public void onCreate() {
        super.onCreate();
        localHelper = new LocalHelper() ;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return saveBinder;
    }

    public class AutoSaveBinder extends Binder {

        public AutoSaveService getService(){
            return AutoSaveService.this;
        }
    }

    public void start(){
        LogUtils.e("自动保存开始＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝") ;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LogUtils.e("10秒了,自动保存一次＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝") ;
                saveData() ;
            }
        },Const.AUTO_SAVE_TIME,Const.AUTO_SAVE_TIME) ;
    }

    public void stop(){
        timer.cancel() ;
    }

    private void saveData(){
        Datum datum = (Datum) application.getSession().get(Const.KEY.CURRENT_CHECK_POINT) ;
        localHelper.saveSingleFile(datum) ;
    }

    private AutoSaveBinder saveBinder = new AutoSaveBinder();

}
