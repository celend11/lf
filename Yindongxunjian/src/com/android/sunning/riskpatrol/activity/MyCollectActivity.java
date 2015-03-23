package com.android.sunning.riskpatrol.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.sunning.riskpatrol.Const;
import com.example.yindongxunjian.R;
import com.android.sunning.riskpatrol.adapter.Adapter4Collect;
import com.android.sunning.riskpatrol.adapter.CommonAdapter;
import com.android.sunning.riskpatrol.entity.E_MyCollect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunning on 15/2/8.
 * 我的汇总
 */
public class MyCollectActivity extends ListViewActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.my_collect);
        super.onCreate(savedInstanceState);
        setTitle("我的汇总");
        hideBackIcon() ;
        sendRequest() ;
    }

    private void sendRequest() {
        E_MyCollect collect;
        for (int i = 0; i < 6; i++) {
            collect = new E_MyCollect();
            collect.finished = i + "";
            collect.unfinished = i + "";
            collect.type = i ;
            switch (i) {
                case Const.CollectType.DAY:
                    collect.title = currentActivity.getString(R.string.check_day);
                    break;
                case Const.CollectType.WEEK:
                    collect.title = currentActivity.getString(R.string.check_week);
                    break;
                case Const.CollectType.MONTH:
                    collect.title = currentActivity.getString(R.string.check_month);
                    break;
                case Const.CollectType.DEVOTE:
                    collect.title = currentActivity.getString(R.string.check_devote);
                    break;
                case Const.CollectType.GROUP:
                    collect.title = currentActivity.getString(R.string.check_group);
                    break;
                case Const.CollectType.MONTH_COLLECT:
                    collect.title = currentActivity.getString(R.string.week_month_collect);
                    break;
            }
            dataForAdapter.add(collect) ;
         }
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
