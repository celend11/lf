package com.android.sunning.riskpatrol.activity;

import android.os.Bundle;
import android.view.View;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/16.
 * 创建专项检查单
 */
public class CreateProperCheckPointActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.create_proper_check_point_layout);
        super.onCreate(savedInstanceState);
        setTitle("创建专项检查单") ;
    }

    @Override
    protected void findView() {
        findViewById(R.id.proper_next_step_btn_id).setOnClickListener(this) ;
    }

    @Override
    protected void findEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.proper_next_step_btn_id:
                MainActivity templateActivity = currentActivity.getActivityGroup() ;
                templateActivity.startActivityById(CheckResultActivity.class.getName(), null) ;
            break;
        }
        super.onClick(v);
    }
}
