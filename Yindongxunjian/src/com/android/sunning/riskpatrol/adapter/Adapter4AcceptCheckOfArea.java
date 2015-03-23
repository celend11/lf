package com.android.sunning.riskpatrol.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.example.yindongxunjian.R;
import com.android.sunning.riskpatrol.entity.E_AcceptCheckArea;
import com.android.sunning.riskpatrol.entity.E_AcceptCheckLeader;
import com.android.sunning.riskpatrol.entity.generate.Area;

import java.util.List;

/**
 * Created by sunning on 15/2/13.
 * 受检区域adapter
 */
public class Adapter4AcceptCheckOfArea extends CommonAdapter<Area>{

    public Adapter4AcceptCheckOfArea(Context context, List<Area> adapterData, int itemLayoutId) {
        super(context, adapterData, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, final Area item) {
        helper.setText(R.id.accept_check_aera_tv_id,item.getAreaName()) ;
        CheckBox checkBox = helper.getView(R.id.accept_check_aera_ck) ;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.isSelect = b ;
            }
        });
    }
}
