package com.android.sunning.riskpatrol.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.sunning.riskpatrol.fragment.HomeHistoryFragment;
import com.android.sunning.riskpatrol.fragment.HomeTaskFragment;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/11.
 */
public class HomeActivity extends BaseActivity {

    private LinearLayout myTask , myHistory ;
    private ImageView taskIM , historyIM ;
    private TextView taskTV , historyTV , userNameTV ;
    private int position = -1;

    private static final String MY_TASK = "my_task" ;
    private static final String MY_HISTORY = "my_history" ;

    private Map<String,Fragment> cache = new HashMap();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.home);
        super.onCreate(savedInstanceState);
        String currentName = dbHelper.queryCurrentLogin().getName() ;
        String title = userNameTV.getText().toString() ;
        if(!TextUtils.isEmpty(currentName)){
            String currentUserName = String.format(title,currentName) ;
            userNameTV.setText(currentUserName) ;
        }
        setContentByFragment(MY_TASK) ;
    }

    @Override
    protected void findView() {
        userNameTV = (TextView) findViewById(R.id.common_title_tv) ;
        myTask = (LinearLayout) findViewById(R.id.home_my_task_parent);
        myHistory = (LinearLayout) findViewById(R.id.home_my_history_parent);
        taskIM = (ImageView) findViewById(R.id.home_my_task_im);
        historyIM = (ImageView) findViewById(R.id.home_my_history_im) ;
        taskTV = (TextView) findViewById(R.id.home_my_task_tv);
        historyTV = (TextView) findViewById(R.id.home_my_history_tv);
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
                setContentByFragment(MY_TASK) ;
                break;
            case R.id.home_my_history_parent:
                position = 1 ;
                setContentByFragment(MY_HISTORY) ;
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
//        transaction.setCustomAnimations(R.anim.in_left_to_right, R.anim.in_right_to_left);
        transaction.replace(R.id.home_body , fragment , "") ;
        transaction.commitAllowingStateLoss() ;
    }
}
