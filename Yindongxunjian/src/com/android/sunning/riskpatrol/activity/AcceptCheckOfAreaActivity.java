package com.android.sunning.riskpatrol.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.adapter.Adapter4AcceptCheckOfArea;
import com.android.sunning.riskpatrol.adapter.CommonAdapter;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.Area;
import com.android.sunning.riskpatrol.entity.generate.Areas;
import com.android.sunning.riskpatrol.entity.generate.CusPatrolItem;
import com.android.sunning.riskpatrol.entity.generate.JianChaXiangMu;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/13.
 * 受检区域
 */
public class AcceptCheckOfAreaActivity extends ListViewActivity<Area>{

    private Adapter4AcceptCheckOfArea adapter ;
    private String areaName;
    @Override
    protected CommonAdapter getAdapter() {
        adapter = new Adapter4AcceptCheckOfArea(this , dataForAdapter , R.layout.accept_check_area_item) ;
        return adapter ;
    }

    @Override
    protected void findView() {
        listView = (android.widget.ListView) findViewById(R.id.accept_check_area_list) ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.accept_check_area_layout);
        super.onCreate(savedInstanceState) ;
        setTitle("受检区域");
        setRightListener(this) ;
        sendRequest() ;
    }

    private void sendRequest() {
        HttpInteraction httpInteraction = new HttpInteraction() {
            @Override
            public void response(BaseEntity entity) {
                Areas areas = (Areas) entity ;
                CreateCheckPointActivity createCheckPointActivity = (CreateCheckPointActivity) application.getSession().get(Const.KEY.CURRENT_CHECK_POINT) ;
                if(createCheckPointActivity != null){
                    JianChaXiangMu jianChaXiangMu = new JianChaXiangMu() ;
                    List<Area> areas1 = areas.getAreas() ;
                    for(Area area : areas1){
                        List<CusPatrolItem> list = new ArrayList<CusPatrolItem>() ;
                        CusPatrolItem c = new CusPatrolItem() ;
                        c.setItemID("00000000-0000-0000-0000-000000000000") ;
                        c.setItemName("自定义");
                        list.add(c) ;
                        area.setCusPatrolItems(list);
                    }
                    jianChaXiangMu.setAreas(areas.getAreas()) ;
                    createCheckPointActivity.rootDatum.setJianChaXiangMu(jianChaXiangMu) ;
                }
                if(areas.getAreas() != null){
                    dataForAdapter.addAll(areas.getAreas());
                    adapter.notifyDataSetChanged() ;
                }
            }
        } ;
        RequestInfo requestInfo = new RequestInfo(Const.InterfaceName.GET_SITE_DATAS,httpInteraction) {
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
    public void performBackPressed() {
        super.performBackPressed();
        Utils.destroy(AcceptCheckOfAreaActivity.class.getName(), getActivityGroup().getLocalActivityManager());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_menu_content_btn:
                if(adapter.getSelectFlag() != -1){
                    CreateCheckPointActivity createCheckPointActivity = (CreateCheckPointActivity) application.getSession().get(Const.KEY.CURRENT_CHECK_POINT) ;
//                    createCheckPointActivity.currentArea.areaList = dataForAdapter ;
                Intent intent = new Intent(Const.BroadcastReceiver.ADD_PROJECT) ;
                sendBroadcast(intent) ;
                Area area=adapter.getItem(adapter.getSelectFlag());
                areaName=area.getAreaName();
                createCheckPointActivity.showarea.setText(areaName);
                createCheckPointActivity.rootDatum.getJianChaXiangMu().getAreas().get(createCheckPointActivity.i).setAreaName(areaName);
                createCheckPointActivity.i++;
//                    createCheckPointActivity.setLeaderName(createCheckPointActivity.currentSelectLeader.leaderName) ;
//                    application.getSession().remove(Const.KEY.CURRENT_CHECK_POINT) ;
                    performBackPressed() ;
                }else{
                    toast("请选择受检区域");
                }
                break;
        }
        super.onClick(v);
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
    		long id) {
    	adapter.setSelect(position) ;
    	adapter.notifyDataSetChanged();
        super.onItemClick(parent, view, position, id);
    }



}
