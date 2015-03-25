package com.android.sunning.riskpatrol.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.adapter.Adapter4Collect;
import com.android.sunning.riskpatrol.adapter.CommonAdapter;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.E_MyCollect;
import com.android.sunning.riskpatrol.entity.generate.summary.MySummary;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.util.DateUtils;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/8.
 * 我的汇总
 */
public class MyCollectActivity extends ListViewActivity {

    public MySummary mySummary;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.my_collect);
        super.onCreate(savedInstanceState);
        setTitle("我的汇总");
        hideBackIcon();
        sendRequest();
    }

    private void sendRequest() {
        if (Utils.isNetworkAvailable(currentActivity)) {
            HttpInteraction httpInteraction = new HttpInteraction() {
                @Override
                public void response(BaseEntity entity) {
                    mySummary = (MySummary) entity;
                    application.getSession().put(Const.KEY.COLLECT_LIST_FLAG,mySummary) ;
                    creatorUIByNet(mySummary);
                }
            };
            RequestInfo requestInfo = new RequestInfo(Const.InterfaceName.GET_MY_SUMMARY, httpInteraction) {
                @Override
                protected void addParams() {
                    requestParams.addQueryStringParameter("usID", dbHelper.queryCurrentLogin().getID());
                    requestParams.addQueryStringParameter("st", DateUtils.getPrefixDate("30"));
                    requestParams.addQueryStringParameter("ed", DateUtils.getCurrentDate());
                }
            };
            requestInfo.execute();
        } else {
            //本地数据库读取//
        }
    }


    private void creatorUIByNet(MySummary mySummary) {
        E_MyCollect collect;
        for (int i = 0; i < 5; i++) {
            collect = new E_MyCollect();
            collect.type = i;
            switch (i) {
                case Const.CollectType.DAY:
                    collect.finished = mySummary.getDayFinishedCount() + "";
//                    collect.finishList = mySummary.getDayFinishedData() ;
                    collect.unfinished = mySummary.getDayUnFinishedCount() + "";
//                    collect.unFinishList = mySummary.getDayUnFinishedData() ;
                    collect.title = currentActivity.getString(R.string.check_day);
                    break;
                case Const.CollectType.WEEK:
//                    collect.finishList = mySummary.getWeekFinishedData();
//                    collect.unFinishList = mySummary.getWeekUnFinishedData();
                    collect.finished = mySummary.getWeekFinishedCount() + "";
                    collect.unfinished = mySummary.getWeekUnFinishedCount() + "";
                    collect.title = currentActivity.getString(R.string.check_week);
                    break;
                case Const.CollectType.MONTH:
//                    collect.finishList = mySummary.getMonthFinishedData();
//                    collect.unFinishList = mySummary.getMonthUnFinishedData();
                    collect.finished = mySummary.getMonthFinishedCount() + "";
                    collect.unfinished = mySummary.getMonthUnFinishedCount() + "";
                    collect.title = currentActivity.getString(R.string.check_month);
                    break;
                case Const.CollectType.DEVOTE:
//                    collect.finishList = mySummary.getZXFinishedData();
//                    collect.unFinishList = mySummary.getZXUnFinishedData();
                    collect.finished = mySummary.getZXFinishedCount() + "";
                    collect.unfinished = mySummary.getZXUnFinishedCount() + "";
                    collect.title = currentActivity.getString(R.string.check_devote);
                    break;
                case Const.CollectType.GROUP:
//                    collect.finishList = mySummary.getJTFinishedData();
//                    collect.unFinishList = mySummary.getJTUnFinishedData();
                    collect.finished = mySummary.getJTFinishedCount() + "";
                    collect.unfinished = mySummary.getMonthFinishedCount() + "";
                    collect.title = currentActivity.getString(R.string.check_group);
                    break;
            }
            dataForAdapter.add(collect);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void findView() {
        listView = (ListView) findViewById(R.id.collect_list_view);
    }

    @Override
    protected void findEvent() {
        super.findEvent();
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
    }

    @Override
    protected CommonAdapter getAdapter() {
        return new Adapter4Collect(this, dataForAdapter, R.layout.collect_list_view_item);
    }
}
