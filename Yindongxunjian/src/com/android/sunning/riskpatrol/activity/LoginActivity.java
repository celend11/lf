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
import android.widget.Switch;
import android.widget.Toast;

import com.android.sunning.riskpatrol.Const;
import com.example.yindongxunjian.R;
import com.android.sunning.riskpatrol.custom.dialog.ProgressBarDialog;
import com.android.sunning.riskpatrol.db.DBHelper;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.login.Login;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.util.Utils;

/**
 * Created by sunning on 15/2/13.
 * 通过shareprenfrence对配置以及用户名，密码进行记录，可以对简单的数据类型保存
 * 通过Dbutil对自定义的类进行保存，包括对从服务器端获取到的数据保存，它是操作数据库的二次封装
 */
public class LoginActivity extends Activity implements View.OnClickListener{

    private EditText user , pwd ;
    private String userNameContent , pwdContent ;
    private ProgressBarDialog progressBar ;
    private SharedPreferences sp;
    private Editor edit;
    private CheckBox cb_pass;
    private boolean flag=false;
    Switch st;
    protected void findView() {
        findViewById(R.id.start_login).setOnClickListener(this) ;
        user = (EditText) findViewById(R.id.userland_user_et) ;
        pwd = (EditText) findViewById(R.id.userland_pwd_et) ;
        cb_pass=(CheckBox) findViewById(R.id.savepass);
        sp=getSharedPreferences("msg",MODE_PRIVATE);
        flag=sp.getBoolean("save",false);
        cb_pass.setChecked(flag);
       
        edit=sp.edit();
        if(savePwd()){
        	user.setText(sp.getString("username",""));
        	pwd.setText(sp.getString("userpwd",""));
        }
        cb_pass.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				edit.putBoolean("save",isChecked);
			}
		});
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
                if(checkUserInput()){
                    startLogin() ;
                }else{
                	showerror(1);
                }
                break;
        }
    }
 /**
  *    		通过get方式，将name与pwd传递到服务器端，获取相应数据，通过返回的status判断登陆是否成功1：成功，2：失败
  */
    private void startLogin() {
//    	判断网络状态
        if(Utils.isNetworkAvailable(this)){
            progressBar.show() ;
            HttpInteraction interaction = new HttpInteraction() {
                @Override
                public void response(BaseEntity entity) {
                    if(entity.code == Const.SUCCESS){
                        openActivity(MainActivity.class ,null) ;  
                        Login eLogin = (Login) entity ;
                        DBHelper dbHelper = DBHelper.getDbHelperInstance(LoginActivity.this) ;
                        dbHelper.saveLogin(eLogin);
                        dbHelper.saveSites(eLogin.getSites());
                        edit.putString("username", userNameContent);
                        edit.putString("userpwd", pwdContent);
                        edit.commit();
                        progressBar.dismiss() ;
                    }
                    if(entity.code==Const.FAIL){
                    	progressBar.dismiss() ;
                    	showerror(3);
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
            //    向服务器接口发送请求
            requestInfo.execute();
        }else{
        	String name=sp.getString("username","");
        	String pwd=sp.getString("userpwd","");
        	if(userNameContent.equals(name)&&pwdContent.equals(pwd)){
                openActivity(MainActivity.class , null) ;  
        	}else{
        		showerror(2);
        	}
        }
    }

 /**
  *    	打开新的activity，如果bundle！=null 则向开启的activity传递基本的数据类型
  * 	并实现activity之间的跳转效果	
  */ 		
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.in_right_to_left,R.anim.roll);
    }

    private boolean checkUserInput(){
        userNameContent = user.getText().toString().trim()+"" ;
        pwdContent = pwd.getText().toString().trim()+"";
        if(userNameContent.equals("")||pwdContent.equals("")){
        	return false;
        }
        return true ;
    }
    private void showerror(int i){
    	if(i==1){
    		Toast.makeText(LoginActivity.this, "用户名或密码不能为空",Toast.LENGTH_LONG).show();
    	}else if(i==2){
    		Toast.makeText(LoginActivity.this, "用户名或密码错误",Toast.LENGTH_LONG).show();
    	}else{
    		Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新输入",Toast.LENGTH_LONG).show();
    	}
    }
    private boolean savePwd(){
    	if(cb_pass.isChecked()){
    		return true;
    	}
		return false;
    }
}
