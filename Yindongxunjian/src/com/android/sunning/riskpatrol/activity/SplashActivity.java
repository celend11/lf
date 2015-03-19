package com.android.sunning.riskpatrol.activity;

import android.os.Bundle;
import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.R;
import com.android.sunning.riskpatrol.db.DBHelper;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.RiskElements;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.util.Utils;

/**
 * Created by sunning on 15/2/13.
 */
public class SplashActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.splash) ;
        super.onCreate(savedInstanceState) ;
        sendRequest();
//        显示3秒钟后，开启登陆页面，本页面销毁
        riHandlerManager.postDelayed(new Runnable() {
            @Override
            public void run() {
                openActivity(LoginActivity.class) ;
                finish();
            }
        },1000) ;
    }

    private void sendRequest() {
        if(Utils.isNetworkAvailable(this)){
            HttpInteraction httpInteraction = new HttpInteraction() {
                @Override
                public void response(BaseEntity entity) {
                    RiskElements elements = (RiskElements) entity ;
                    dbHelper.saveRiskElement(elements) ;
//                    if(elements.getRiskElements() != null){
//                        dataForAdapter.addAll(elements.getRiskElements()) ;
//                        adapter.notifyDataSetChanged() ;
//                    }

                }
            } ;
            RequestInfo requestInfo = new RequestInfo(Const.InterfaceName.GET_RISK_ELEMENT_DATAS,httpInteraction) {
                @Override
                protected void addParams() {
                    requestParams.addQueryStringParameter("time" , "2015-02-28") ;
                }
            } ;
            requestInfo.execute() ;
        }
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void findEvent() {

    }
}
