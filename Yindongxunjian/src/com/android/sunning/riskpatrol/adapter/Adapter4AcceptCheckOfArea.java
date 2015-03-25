package com.android.sunning.riskpatrol.adapter;

import java.util.List;

import android.content.Context;
import android.widget.ImageView;

import com.android.sunning.riskpatrol.entity.generate.Area;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/13.
 * 受检区域adapter
 */
public class Adapter4AcceptCheckOfArea extends CommonAdapter<Area>{
    private byte[] flag ;

//    public Area selectArea ;
    public Adapter4AcceptCheckOfArea(Context context, List<Area> adapterData, int itemLayoutId) {
        super(context, adapterData, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, final Area item) {
        helper.setText(R.id.accept_check_aera_tv_id,item.getAreaName()) ;
        ImageView checkBox = helper.getView(R.id.accept_check_aera_ck) ;
        if(item.isSelect){
            checkBox.setImageResource(R.drawable.inner_ck_select);
        }else{
            checkBox.setImageResource(R.drawable.inner_ck_unselect);
        }
    }
    public void setSelect(int position){
        int size = adapterData.size() ;
        for (int i = 0; i < size ; i++) {
            adapterData.get(i).isSelect = false ;
        }
        getItem(position).isSelect = true ;
    }
    public byte getSelect(int position){
        return flag[position] ;
    }

    public int getSelectFlag(){
        int size = adapterData.size() ;
        for (int i = 0; i < size ; i++) {
            if(getItem(i).isSelect){
                return i ;
            }
        }
        return -1 ;
    }
    public boolean isClicked(){
        for (int i = 0; i < flag.length; i++) {
            if(flag[i] == 1){
                return true ;
            }
        }
        return false ;
    }
    public void resetFlag(){
        for (int i = 0; i < flag.length; i++) {
            flag[i] = 0 ;
        }
    }

    public void clearFlag(int position){
        flag[position] = 0 ;
    }
}
