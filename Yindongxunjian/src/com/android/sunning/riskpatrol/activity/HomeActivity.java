package com.android.sunning.riskpatrol.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sunning.riskpatrol.R;
import com.android.sunning.riskpatrol.db.DBHelper;
import com.android.sunning.riskpatrol.entity.generate.login.Login;
import com.android.sunning.riskpatrol.fragment.HomeHistoryFragment;
import com.android.sunning.riskpatrol.fragment.HomeTaskFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;

/**
 * Created by sunning on 15/2/11.
 */
public class HomeActivity extends BaseActivity {

    private LinearLayout myTask , myHistory ;
    private ImageView taskIM , historyIM ;
    private TextView taskTV , historyTV,titleTV ;
    private int position = -1;
    private DBHelper dbhelper;
    private Login login=new Login();
    private static final String MY_TASK = "my_task" ;
    private static final String MY_HISTORY = "my_history" ;

    private Map<String,Fragment> cache = new HashMap();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.home);
        super.onCreate(savedInstanceState);
        setContentByFragment(MY_TASK) ;
        dbhelper=DBHelper.getDbHelperInstance(HomeActivity.this);
        login=null;
        login=dbHelper.queryCurrentLogin();        
        titleTV.setText("当前用户："+login.getName());
    }
    
    @Override
    protected void findView() {
        myTask = (LinearLayout) findViewById(R.id.home_my_task_parent);
        myHistory = (LinearLayout) findViewById(R.id.home_my_history_parent);
        taskIM = (ImageView) findViewById(R.id.home_my_task_im);
        historyIM = (ImageView) findViewById(R.id.home_my_history_im) ;
        titleTV=(TextView) findViewById(R.id.common_title_tv);
        taskTV = (TextView) findViewById(R.id.home_my_task_tv);
        historyTV = (TextView) findViewById(R.id.home_my_history_tv);
//        titleTV.setText("当前用户："+login.getName());
    }
    
    @Override
    protected void findEvent() {
        myTask.setOnClickListener(this) ;
        myHistory.setOnClickListener(this) ;
    }

    @Override
    public void onClick(View v) {
        int position = 0;
        switch (v.getId()){
            case R.id.home_my_task_parent:
                position = 0 ;
//			显示我的任务的fragment界面
                setContentByFragment(MY_TASK);
                break;
            case R.id.home_my_history_parent:
                position = 1 ;
//    		显示完成历史的fragment界面                
                setContentByFragment(MY_HISTORY);
                break;
        }
        changeColor(position) ;
    }

    private void changeColor(int index){
        if(index == position){
            return;
        }
        Resources resources = getResources() ;
        switch (index){
            case 0:
                myTask.setBackgroundColor(resources.getColor(R.color.blue_bgk_color)) ;
                myHistory.setBackgroundColor(resources.getColor(R.color.unselected_bgk_color)) ;
                taskIM.setImageResource(R.drawable.index_tab_icon_task) ;
                historyIM.setImageResource(R.drawable.index_tab_icon_history_unselect) ;
                taskTV.setTextColor(resources.getColor(R.color.blue_bgk_text_color));
                historyTV.setTextColor(resources.getColor(R.color.white));
                break;
            case 1:
                myTask.setBackgroundColor(resources.getColor(R.color.unselected_bgk_color)) ;
                myHistory.setBackgroundColor(resources.getColor(R.color.blue_bgk_color)) ;
                taskIM.setImageResource(R.drawable.index_tab_icon_task_unselect) ;
                historyIM.setImageResource(R.drawable.index_tab_icon_history) ;
                taskTV.setTextColor(resources.getColor(R.color.white));
                historyTV.setTextColor(resources.getColor(R.color.blue_bgk_text_color));

        }
        position = index ;
    }

    private void setContentByFragment(String flag){
//    	添加fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction() ;
        Fragment fragment = null;
        if(flag.equals(MY_TASK)){
            fragment = cache.get(MY_TASK) ;
            if(fragment == null){
                fragment = new HomeTaskFragment() ;
            }
            cache.put(MY_TASK, fragment) ;
        }else{
            fragment = cache.get(MY_HISTORY) ;
            if(fragment == null){
                fragment = new HomeHistoryFragment() ;
            }
            cache.put(MY_HISTORY, fragment) ;
        }
        transaction.replace(R.id.home_body , fragment , "") ;
//        相对与commit来说，commit在系统数据没有保存已经前跳转，是会报错的，但是commitallowingstateloss,是应许这种情况的，不会报错
        transaction.commitAllowingStateLoss() ;
    }
}
