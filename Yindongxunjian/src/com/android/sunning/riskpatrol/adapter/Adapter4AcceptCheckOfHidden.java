package com.android.sunning.riskpatrol.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.android.sunning.riskpatrol.entity.generate.RiskElement;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/26.
 * 风险因素adapter
 */
public class Adapter4AcceptCheckOfHidden extends CommonAdapter<RiskElement>{

    public List<RiskElement> select = new ArrayList<RiskElement>() ;

    public Adapter4AcceptCheckOfHidden(Context context, List<RiskElement> adapterData, int itemLayoutId) {
        super(context, adapterData, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, final RiskElement item) {
        helper.setText(R.id.accept_check_aera_tv_id,item.getEName()) ;
        CheckBox checkBox = helper.getView(R.id.accept_check_aera_ck) ;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.isSelect = b ;
                if(b){
                    select.add(item) ;
                }else{
                    select.remove(item) ;
                }
            }
        });
    }
}
