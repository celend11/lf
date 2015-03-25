package com.android.sunning.riskpatrol.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/15.
 */
public class GetCustomItem {

    private Context context ;
    private LayoutInflater layoutInflater ;

    public GetCustomItem(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context) ;
    }

    public View getCustomItem(){
        View returnView = layoutInflater.inflate(R.layout.get_custom_view , null) ;
        return returnView;
    }




}
