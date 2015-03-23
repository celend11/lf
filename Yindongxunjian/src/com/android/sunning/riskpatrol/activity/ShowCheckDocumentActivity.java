package com.android.sunning.riskpatrol.activity;

import android.os.Bundle;
import android.view.View;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/15.
 * 查看检查单
 */
public class ShowCheckDocumentActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.show_check_docment_layout);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void findView() {
        findViewById(R.id.check_result_inner_item_parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity templateActivity = currentActivity.getActivityGroup() ;
                templateActivity.startActivityById(ShowCheckResultDetailActivity.class.getName(), null) ;
            }
        });
    }

    @Override
    protected void findEvent() {

    }
}
