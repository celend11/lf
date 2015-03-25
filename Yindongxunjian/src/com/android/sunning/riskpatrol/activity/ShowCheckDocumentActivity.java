package com.android.sunning.riskpatrol.activity;

import android.os.Bundle;
import android.view.View;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/15.
 * 查看检查单
 */
public class ShowCheckDocumentActivity extends BaseActivity{

    private Datum currentDatum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.show_check_docment_layout);
        super.onCreate(savedInstanceState);
        currentDatum = (Datum) application.getSession().get(Const.KEY.CURRENT_SHOW_DATUM) ;
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

    @Override
    public void performBackPressed() {
        super.performBackPressed();
        Utils.destroy(ShowCheckDocumentActivity.class.getName(), getActivityGroup().getLocalActivityManager());
        application.getSession().remove(Const.KEY.CURRENT_SHOW_DATUM) ;
    }
}
