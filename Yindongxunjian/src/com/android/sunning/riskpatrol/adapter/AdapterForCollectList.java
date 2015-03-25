package com.android.sunning.riskpatrol.adapter;

import java.util.List;

import android.content.Context;

import com.android.sunning.riskpatrol.entity.generate.summary.SummaryItemDatum;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/3/3.
 */
public class AdapterForCollectList extends CommonAdapter<SummaryItemDatum>{

    public AdapterForCollectList(Context context, List<SummaryItemDatum> adapterData, int itemLayoutId) {
        super(context, adapterData, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, SummaryItemDatum item) {
        helper.setText(R.id.collect_list_item_num,item.InspectNum()) ;
    }
}
