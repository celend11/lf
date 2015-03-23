package com.android.sunning.riskpatrol.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.yindongxunjian.R;
import com.android.sunning.riskpatrol.adapter.CommonAdapter;
import com.lidroid.xutils.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunning on 15/2/13.
 * 有listView的activity
 */
public abstract class ListViewActivity<T> extends BaseActivity implements AdapterView.OnItemClickListener{

    protected ListView listView ;

    protected CommonAdapter<T> adapter ;

    protected List<T> dataForAdapter = new ArrayList<T>() ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter() ;
    }

    private void initAdapter() {
        adapter = getAdapter() ;
        if(listView != null ){
            listView.setAdapter(adapter) ;
        }
    }

    @Override
    protected void findEvent() {
        if(listView != null){
            listView.setOnItemClickListener(this) ;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    protected abstract CommonAdapter getAdapter() ;
}
