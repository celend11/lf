package com.android.sunning.riskpatrol.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.RiskPatrolApplication;
import com.android.sunning.riskpatrol.activity.MainActivity;
import com.android.sunning.riskpatrol.activity.MyCollectListActivity;
import com.android.sunning.riskpatrol.entity.E_MyCollect;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/12.
 */
public class Adapter4Collect extends CommonAdapter<E_MyCollect>{

    public Adapter4Collect(Context context, List adapterData, int itemLayoutId) {
        super(context, adapterData, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, final E_MyCollect item) {
        helper.setText(R.id.collect_list_item_title , item.title) ;
        helper.setImageResource(R.id.collect_list_item_icon , getIcon(item.type)) ;
        TextView finished = helper.getView(R.id.collect_list_item_finished) ;
        TextView unFinish= helper.getView(R.id.collect_list_item_unfinished) ;
        helper.setText(R.id.collect_list_item_finished,(String.format(finished.getText().toString() , item.finished))) ;
        helper.setText(R.id.collect_list_item_unfinished,(String.format(unFinish.getText().toString() , item.unfinished))) ;
        helper.setOnClick(R.id.collect_list_item_finished , new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RiskPatrolApplication application = (RiskPatrolApplication) mContext.getApplicationContext();
                MainActivity mainActivity = (MainActivity) application.getSession().get(RiskPatrolApplication.MAIN_ACTIVITY);
                Map<String,String> param = new HashMap<String, String>() ;
                param.put(Const.KEY.COLLECT_LIST_TYPE,item.type+"") ;
                param.put(Const.KEY.COLLECT_LIST_IS_FINISH,item.type+"") ;
                param.put(Const.KEY.COLLECT_LIST_TITLE,item.title) ;
                mainActivity.startActivityById(MyCollectListActivity.class.getName(), param) ;
            }
        });
        helper.setOnClick(R.id.collect_list_item_unfinished , new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RiskPatrolApplication application = (RiskPatrolApplication) mContext.getApplicationContext();
                MainActivity mainActivity = (MainActivity) application.getSession().get(RiskPatrolApplication.MAIN_ACTIVITY);
                Map<String,String> param = new HashMap<String, String>() ;
                param.put(Const.KEY.COLLECT_LIST_TYPE,item.type+"") ;
                param.put(Const.KEY.COLLECT_LIST_TITLE,item.title) ;
                mainActivity.startActivityById(MyCollectListActivity.class.getName(), param) ;
            }
        });
    }

    private int getIcon(int index){
        switch (index) {
            case Const.CollectType.DAY:
               return R.drawable.home_icon_day ;
            case Const.CollectType.WEEK:
                return R.drawable.home_icon_week;
            case Const.CollectType.MONTH:
                return R.drawable.home_icon_mouth ;
            case Const.CollectType.DEVOTE:
                return R.drawable.home_icon_proper;
            case Const.CollectType.GROUP:
                return R.drawable.home_icon_group ;
            case Const.CollectType.MONTH_COLLECT:
                return R.drawable.home_icon_group_collect ;
        }
        return 0 ;
    }
}
