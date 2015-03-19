package com.android.sunning.riskpatrol.adapter;

import android.content.Context;
import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.R;
import com.android.sunning.riskpatrol.entity.E_MyCollect;

import java.util.List;

/**
 * Created by sunning on 15/2/12.
 */
public class Adapter4Collect extends CommonAdapter<E_MyCollect>{

    public Adapter4Collect(Context context, List adapterData, int itemLayoutId) {
        super(context, adapterData, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, E_MyCollect item) {
        helper.setText(R.id.collect_list_item_title , item.title) ;
        helper.setImageResource(R.id.collect_list_item_icon , getIcon(item.type)) ;
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
