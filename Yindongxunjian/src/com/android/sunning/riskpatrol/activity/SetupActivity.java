package com.android.sunning.riskpatrol.activity;

import android.os.Bundle;
import android.view.View;

import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/8.
 * 我的设置
 */
public class SetupActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.setup);
        super.onCreate(savedInstanceState);
        setTitle("配置");
        hideBackIcon();
    }

    @Override
    protected void findView() {
        findViewById(R.id.setup_logout).setOnClickListener(this) ;
    }

    @Override
    protected void findEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setup_logout:
                openActivity(LoginActivity.class) ;
                break;
        }
    }
}
