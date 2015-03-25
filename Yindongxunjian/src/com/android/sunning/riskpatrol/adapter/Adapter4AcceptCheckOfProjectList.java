package com.android.sunning.riskpatrol.adapter;

import java.util.List;

import android.content.Context;
import android.widget.ImageView;

import com.android.sunning.riskpatrol.entity.generate.login.Site;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/13.
 * 受检负责人adapter
 */
public class Adapter4AcceptCheckOfProjectList extends CommonAdapter<Site>{

    private byte[] flag ;

    public Site selectSite ;

    public Adapter4AcceptCheckOfProjectList(Context context, List<Site> adapterData, int itemLayoutId) {
        super(context, adapterData, itemLayoutId);
    }

    public void addData(List<Site> list){
        adapterData.addAll(list) ;
        flag = new byte[adapterData.size()] ;
    }


    @Override
    public void convert(final ViewHolder helper, final Site item) {
        helper.setText(R.id.accept_check_project_title, item.getName()) ;
        ImageView imageView = helper.getView(R.id.accept_check_project_ck) ;
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
