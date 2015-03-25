package com.android.sunning.riskpatrol.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.custom.dialog.ProgressBarDialog;
import com.android.sunning.riskpatrol.db.DBHelper;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.login.Login;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/13.
 */
public class LoginActivity extends Activity implements View.OnClickListener,OnCheckedChangeListener{

    private EditText user , pwd ;
    private String userNameContent , pwdContent,usName,usPwd;
    private ProgressBarDialog progressBar ;
    private SharedPreferences sp;
    private Editor edit;
    private CheckBox cb;
    private boolean flag=true;
    protected void findView() {
        findViewById(R.id.start_login).setOnClickListener(this) ;
        user = (EditText) findViewById(R.id.userland_user_et) ;
        pwd = (EditText) findViewById(R.id.userland_pwd_et) ;
        cb=(CheckBox) findViewById(R.id.record_pass);
        sp=getSharedPreferences("data",MODE_PRIVATE);
        edit=sp.edit();
        flag=sp.getBoolean("record_pwd", true);
        cb.setChecked(flag);
        cb.setOnCheckedChangeListener(this);
        if(flag){
        	usName=sp.getString("name","");
        	usPwd=sp.getString("pwd","");
        	user.setText(usName);
        	pwd.setText(usPwd);
        }else{
        	edit.putString("name","");
        	edit.putString("pwd","");
        	edit.commit();
        }
    }

    protected void findEvent() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login) ;
        progressBar = new ProgressBarDialog(this) ;
        findView() ;
        findEvent() ;
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_login:
                if(checkUserInput()==1){
                    startLogin() ;
                }else if(checkUserInput()==-1){
                	Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                }else if(checkUserInput()==-2){
                	Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    
    private void startLogin() {
        if(Utils.isNetworkAvailable(this)){
            progressBar.show() ;
            HttpInteraction interaction = new HttpInteraction() {
                @Override
                public void response(BaseEntity entity) {
                    if(entity.code == Const.SUCCESS){
                        progressBar.dismiss() ;
                        openActivity(MainActivity.class , null) ;
                        Login eLogin = (Login) entity ;
//                    LogUtils.e(eLogin.toString()) ;
                        DBHelper dbHelper = DBHelper.getDbHelperInstance(LoginActivity.this) ;
                        dbHelper.saveLogin(eLogin);
                        dbHelper.saveSites(eLogin.getSites()) ;
                        edit.putString("name",userNameContent);
                        edit.putString("pwd",pwdContent);
                        edit.commit();
                    }
                    if(entity.code==Const.FAIL){
                    	progressBar.dismiss();
                    	Toast.makeText(LoginActivity.this,"密码或用户名错误",Toast.LENGTH_LONG).show();
                    }
                }
            } ;
            RequestInfo requestInfo = new RequestInfo(Const.InterfaceName.LOGIN , interaction) {
                @Override
                protected void addParams() {
                    requestParams.addQueryStringParameter("usName",userNameContent) ;
                    requestParams.addQueryStringParameter("pwd",pwdContent) ;
                }
            } ;
            requestInfo.execute();
        }else{
        	if(userNameContent.equals(sp.getString("name",""))&&pwdContent.equals(sp.getString("pwd",""))){
            progressBar.dismiss() ;
            openActivity(MainActivity.class , null) ;
        	}else{
        		Toast.makeText(LoginActivity.this,"密码或用户名错误",Toast.LENGTH_LONG).show();	
        	}
        }
    }


    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.in_right_to_left,R.anim.roll);
    }

    private int checkUserInput(){
        userNameContent = user.getText().toString().trim()+"" ;
        pwdContent = pwd.getText().toString().trim()+"";
        if(userNameContent.equals("")){
        	return -1;
        }
        if(pwdContent.equals("")){
        	return -2;
        }
        return 1 ;
    }

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(isChecked){
			edit.putBoolean("record_pwd", true);
			edit.commit();
		}else{
			edit.putBoolean("record_pwd", false);
			edit.commit();
		}
		
	}
}
