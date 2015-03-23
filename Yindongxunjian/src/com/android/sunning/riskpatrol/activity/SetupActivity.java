package com.android.sunning.riskpatrol.activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/8.
 * 我的设置
 */
public class SetupActivity extends BaseActivity implements android.widget.RadioGroup.OnCheckedChangeListener,android.widget.CompoundButton.OnCheckedChangeListener{


    private SharedPreferences sp;
    private Editor edit;
    private ToggleButton tlb;
    private boolean flag;
    private RadioGroup rg1,rg2;
    private int []checkid=new int[2];
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.setup);
        super.onCreate(savedInstanceState);
        setTitle("配置");
        sp=getSharedPreferences("msg",MODE_PRIVATE);
        edit=sp.edit();
        flag=sp.getBoolean("update", false);
        checkid[0]=sp.getInt("mapzhiliang", R.id.rd1);
        checkid[1]=sp.getInt("reczhiliang", R.id.rdi1);
        rg1.check(checkid[0]);
        rg2.check(checkid[1]);
        tlb.setChecked(flag);
        hideBackIcon();
    }
    
    @Override
    protected void findView() {
        findViewById(R.id.setup_logout).setOnClickListener(this);
        tlb=(ToggleButton) findViewById(R.id.mTogBtn);
        rg1=(RadioGroup) findViewById(R.id.radiog1);
        rg2=(RadioGroup) findViewById(R.id.radiog2);
        rg1.setOnCheckedChangeListener(this);
        rg2.setOnCheckedChangeListener(this);
        tlb.setOnCheckedChangeListener(this);
    }

    @Override
    protected void findEvent() {

    }

    
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setup_logout:
                openActivity(LoginActivity.class) ;
                break;
        }
    }

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if(group.getId()==R.id.radiog1){
		edit.putInt("mapzhiliang",checkedId);
		edit.commit();
		}
		if(group.getId()==R.id.radiog2){
		edit.putInt("reczhiliang",checkedId);
		edit.commit();
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		edit.putBoolean("update",isChecked);
		edit.commit();
		
	}
}
