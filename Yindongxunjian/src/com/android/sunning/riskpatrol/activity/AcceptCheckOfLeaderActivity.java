package com.android.sunning.riskpatrol.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.adapter.Adapter4AcceptCheckOfLeader;
import com.android.sunning.riskpatrol.adapter.CommonAdapter;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.E_AcceptCheckLeader;
import com.android.sunning.riskpatrol.entity.E_FuZeRen;
import com.android.sunning.riskpatrol.entity.generate.JianChaFuZeRenC;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/13.
 * 受检负责人
 */
public class AcceptCheckOfLeaderActivity extends ListViewActivity<E_AcceptCheckLeader>{

    private Adapter4AcceptCheckOfLeader adapter ;

    @Override
    protected void findView() {
        listView = (ListView) findViewById(R.id.accept_check_leader_list) ;
    }

    @Override
    protected void findEvent() {
        super.findEvent();
    }

    @Override
    protected CommonAdapter getAdapter() {
        adapter =  new Adapter4AcceptCheckOfLeader(this , dataForAdapter , R.layout.accept_check_leader_item)  ;
        return adapter ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.accept_check_leader_layout);
        super.onCreate(savedInstanceState) ;
        setTitle("受检负责人") ;
        sendRequest() ;
        setRightListener(this) ;
    }

    private void sendRequest() {
        HttpInteraction httpInteraction = new HttpInteraction() {
            @Override
            public void response(BaseEntity entity) {
                E_FuZeRen fuZeRen = (E_FuZeRen) entity ;
                if(fuZeRen != null){
                    dataForAdapter.addAll(fuZeRen.fuZeRenList) ;
                    adapter.notifyDataSetChanged() ;
                }
            }
        } ;
        RequestInfo requestInfo = new RequestInfo(Const.InterfaceName.GET_SITE_USERS,httpInteraction) {
            @Override
            protected void addParams() {
                CreateCheckPointActivity checkPointActivity = (CreateCheckPointActivity) application.getSession().get(Const.KEY.CURRENT_CHECK_POINT);
                requestParams.addQueryStringParameter("time" , checkPointActivity.showDate.getText().toString()) ;
                requestParams.addQueryStringParameter("siteID" , checkPointActivity.rootDatum.getSite().getSiteID()) ;
            }
        } ;
        requestInfo.execute() ;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        adapter.setSelect(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_menu_content_btn:
                if(adapter.getSelectFlag() != -1){
                    CreateCheckPointActivity createCheckPointActivity = (CreateCheckPointActivity) application.getSession().get(Const.KEY.CURRENT_CHECK_POINT) ;
                    E_AcceptCheckLeader leader = adapter.getItem(adapter.getSelectFlag()) ;
                    if(leader != null){
                        JianChaFuZeRenC jianChaFuZeRenC = new JianChaFuZeRenC() ;
                        jianChaFuZeRenC.setJianChaFuZeRen(leader.leaderName) ;
                        jianChaFuZeRenC.setJianChaFuZeRenID(leader.leaderID) ;
                        createCheckPointActivity.rootDatum.setJianChaFuZeRenC(jianChaFuZeRenC) ;
                        createCheckPointActivity.setLeaderName(leader.leaderName) ;
                        application.getSession().remove(Const.KEY.CURRENT_CHECK_POINT) ;
                        performBackPressed() ;
                    }
                }else{
                    toast("请选择受检责任人");
                }
                break;
        }
        super.onClick(v);
    }

    @Override
    public void performBackPressed() {
        super.performBackPressed();
        Utils.destroy(AcceptCheckOfLeaderActivity.class.getName(), getActivityGroup().getLocalActivityManager());
    }
}
