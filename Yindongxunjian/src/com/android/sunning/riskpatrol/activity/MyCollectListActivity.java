package com.android.sunning.riskpatrol.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.adapter.AdapterForCollectList;
import com.android.sunning.riskpatrol.adapter.CommonAdapter;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.entity.generate.summary.MySummary;
import com.android.sunning.riskpatrol.entity.generate.summary.SummaryItemDatum;
import com.android.sunning.riskpatrol.exception.BException;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/3/3.
 */
public class MyCollectListActivity extends ListViewActivity {

    private int currentType = -1;
    private boolean isFinish;
    private String title ;

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        if(Utils.isNetworkAvailable(this)){
            sendRequest(position) ;
        }
        super.onItemClick(parent, view, position, id);
    }

    private void sendRequest(final int position){
        HttpInteraction interaction = new HttpInteraction() {
            @Override
            public void response(BaseEntity entity) {
                Datum datum = (Datum) entity ;
                application.getSession().put(Const.KEY.CURRENT_SHOW_DATUM,datum) ;
                if(isFinish){
                    getActivityGroup().startActivityById(ShowCheckDocumentActivity.class.getName(), null) ;
                }else {

                }
            }

            @Override
            public void fail(BException exception) {
                super.fail(exception);
            }
        } ;
        RequestInfo requestInfo = new RequestInfo(Const.InterfaceName.GET_DETAIL,interaction) {
            @Override
            protected void addParams() {
                SummaryItemDatum itemDatum = (SummaryItemDatum) adapter.getItem(position);
                requestParams.addQueryStringParameter("Id",itemDatum.ReportID());
                requestParams.addQueryStringParameter("Type",currentType+"");
            }
        } ;
        requestInfo.execute();
    }

    @Override
    protected CommonAdapter getAdapter() {
        return new AdapterForCollectList(this, dataForAdapter, R.layout.collect_list_layout);
    }

    @Override
    protected void findView() {
        listView = (android.widget.ListView) findViewById(R.id.collect_list_view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.my_collect_list);
        super.onCreate(savedInstanceState);
        getValue();

    }

    private void getValue() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(Const.KEY.COLLECT_LIST_TYPE)) {
            currentType = Integer.parseInt(bundle.getString(Const.KEY.COLLECT_LIST_TYPE));
        }if (bundle != null && bundle.containsKey(Const.KEY.COLLECT_LIST_TITLE)) {
            title = bundle.getString(Const.KEY.COLLECT_LIST_TITLE);
        }
        isFinish = bundle.containsKey(Const.KEY.COLLECT_LIST_IS_FINISH);
        if (currentType != -1) {
            creatorUI();
        }
        setTitle(title + "-" +(isFinish?"已完成":"未完成")) ;
    }

    private void creatorUI() {
        MySummary mySummary = (MySummary) application.getSession().get(Const.KEY.COLLECT_LIST_FLAG);
        if(mySummary == null){
            return;
        }
        switch (currentType) {
            case Const.CollectType.DAY:
                if (isFinish)
                    dataForAdapter.addAll(mySummary.getDayFinishedData());
                else
                    dataForAdapter.addAll(mySummary.getDayUnFinishedData());
                break;
            case Const.CollectType.WEEK:
                if (isFinish)
                    dataForAdapter.addAll(mySummary.getWeekFinishedData());
                else
                    dataForAdapter.addAll(mySummary.getWeekUnFinishedData());
                break;
            case Const.CollectType.MONTH:
                if (isFinish)
                    dataForAdapter.addAll(mySummary.getMonthFinishedData());
                else
                    dataForAdapter.addAll(mySummary.getMonthUnFinishedData());
                break;
            case Const.CollectType.DEVOTE:
                if (isFinish)
                    dataForAdapter.addAll(mySummary.getZXFinishedData());
                else
                    dataForAdapter.addAll(mySummary.getZXUnFinishedData());
                break;
            case Const.CollectType.GROUP:
                if (isFinish)
                    dataForAdapter.addAll(mySummary.getJTFinishedData());
                else
                    dataForAdapter.addAll(mySummary.getJTUnFinishedData());
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void performBackPressed() {
        super.performBackPressed();
        Utils.destroy(MyCollectListActivity.class.getName(), getActivityGroup().getLocalActivityManager());
    }

}
