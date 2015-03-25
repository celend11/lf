package com.android.sunning.riskpatrol.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.adapter.Adapter4AcceptCheckOfHidden;
import com.android.sunning.riskpatrol.adapter.CommonAdapter;
import com.android.sunning.riskpatrol.entity.generate.RiskElement;
import com.android.sunning.riskpatrol.entity.generate.RiskElements;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by sunning on 15/2/14.
 * 添加隐患说明
 */
public class AddHiddenDescActivity extends ListViewActivity<RiskElement> {

    private Adapter4AcceptCheckOfHidden adapter ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.add_hidden_desc_layout);
        super.onCreate(savedInstanceState);
        setTitle("添加隐患说明") ;
        setRightListener(this) ;
//        getAllRiskElements() ;
    }

    private void getAllRiskElements() {
        List<RiskElement> elements = new ArrayList<RiskElement>();
    	RiskElement risk1=new RiskElement();
    	risk1.setEName("aaaaaaaaaaa");
    	risk1.setFengBu("aaaaaaaaaaa");
    	risk1.setFengXiang("aaaaa");
    	risk1.setID("AAAAA");
    	elements.add(risk1);
    	RiskElement risk2=new RiskElement();
    	risk2.setEName("bbaaaaaaaaaa");
    	risk2.setFengBu("bbaaaaaaaaaaa");
    	risk2.setFengXiang("bbaaaaa");
    	risk2.setID("bbAAAAA");
    	elements.add(risk2);
        if(elements != null){
            dataForAdapter.addAll(elements) ;
            adapter.notifyDataSetChanged() ;
        }
    }

    @Override
    protected void findView() {
        listView = (ListView) findViewById(R.id.add_hidden_desc_list) ;
    }

    @Override
    protected void findEvent() {

    }

    @Override
    public void performBackPressed() {
        super.performBackPressed();
        Utils.destroy(AddHiddenDescActivity.class.getName(), getActivityGroup().getLocalActivityManager());
    }

    @Override
    protected CommonAdapter getAdapter() {
        adapter = new Adapter4AcceptCheckOfHidden(this,dataForAdapter,R.layout.accept_check_area_item) ; ;
        return adapter ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_menu_content_btn:
                CheckResultDetailActivity checkResultDetail = (CheckResultDetailActivity) application.getSession().get(Const.KEY.CURRENT_RESULT_DETAIL) ;
                RiskElements riskElements = new RiskElements() ;
                riskElements.setRiskElements(adapter.select) ;
                LogUtils.e("===============" + riskElements.getRiskElements().size());
                checkResultDetail.setCurrentSelectRiskElement(riskElements) ;
                Intent intent = new Intent(Const.BroadcastReceiver.ADD_RISK_ELEMENT);
                sendBroadcast(intent);
                performBackPressed();
                break;
        }
        super.onClick(v);
    }
}
