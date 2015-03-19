package com.android.sunning.riskpatrol.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.android.sunning.riskpatrol.R;
import com.android.sunning.riskpatrol.entity.E_AcceptCheckLeader;

import java.util.List;

/**
 * Created by sunning on 15/2/13.
 * 受检负责人adapter
 */
public class Adapter4AcceptCheckOfLeader extends CommonAdapter<E_AcceptCheckLeader>{

    public Adapter4AcceptCheckOfLeader(Context context, List<E_AcceptCheckLeader> adapterData, int itemLayoutId) {
        super(context, adapterData, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, E_AcceptCheckLeader item) {
        helper.setText(R.id.accept_check_leader_real_name_id,item.leaderName) ;
        helper.setText(R.id.accept_check_leader_job_title_id,item.leaderUnit) ;
        ImageView imageView = helper.getView(R.id.accept_check_leader_ck) ;
        if(item.isSelect){
            imageView.setImageResource(R.drawable.inner_ck_select);
        }else{
            imageView.setImageResource(R.drawable.inner_ck_unselect);
        }
    }

    public void setSelect(int position){
        int size = adapterData.size() ;
        for (int i = 0; i < size ; i++) {
            adapterData.get(i).isSelect = false ;
        }
        getItem(position).isSelect = true ;
    }


    public int getSelectFlag(){
        int size = adapterData.size() ;
        for (int i = 0; i < size ; i++) {
            if(getItem(i).isSelect == true){
                return i ;
            }
        }
        return -1 ;
    }
}
