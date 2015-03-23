package com.android.sunning.riskpatrol.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.sunning.riskpatrol.Const;
import com.example.yindongxunjian.R;
import com.android.sunning.riskpatrol.entity.E_AreaPatrolItems;
import com.android.sunning.riskpatrol.entity.generate.Area;
import com.android.sunning.riskpatrol.entity.generate.JianChaXiangMu;
import com.android.sunning.riskpatrol.entity.generate.PatrolItem;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by sunning on 15/2/13.
 * 检查结果录入
 */
public class CheckResultActivity extends BaseActivity{

    private LayoutInflater layoutInflater ;

    private LinearLayout rootView ;

    private JianChaXiangMu jianChaXiangMu ;

    public static PatrolItem patrolItems ;

    private BroadcastReceiver receiver ;


    @Override
    protected void findView() {
        rootView = (LinearLayout) findViewById(R.id.check_result_content);
    }

    @Override
    protected void findEvent() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.check_result_layout);
        super.onCreate(savedInstanceState);
        layoutInflater = LayoutInflater.from(this) ;
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                drawView() ;
            }
        } ;
        IntentFilter intentFilter = new IntentFilter() ;
        intentFilter.addAction(Const.BroadcastReceiver.ADD_PROJECT);
        registerReceiver(receiver , intentFilter) ;
        setRightListener(R.drawable.add_area_selector,this) ;
        setTitle("检查结果录入");
        drawView() ;
    }

    public void drawView(){
        rootView.removeAllViews();
        CreateCheckPointActivity createCheckPointActivity = (CreateCheckPointActivity) application.getSession().get(Const.KEY.CURRENT_CHECK_POINT);
        if(createCheckPointActivity == null){
            return ;
        }
        jianChaXiangMu = createCheckPointActivity.rootDatum.getJianChaXiangMu() ;
        View space ;
        if(jianChaXiangMu != null){
            if(jianChaXiangMu.getAreas() != null){
                int size = jianChaXiangMu.getAreas().size() ;
                LinearLayout innerLayout = null;
                for(int i = 0 ; i < size ; i++){
                    innerLayout = (LinearLayout) layoutInflater.inflate(R.layout.check_result_layout_inner , null);
                    Area area = jianChaXiangMu.getAreas().get(i) ;
                    if(area.isSelect){
                        View title = createTitle(area) ;
                        innerLayout.addView(title) ;
                        View content = createContentView(area) ;
                        innerLayout.addView(content) ;
                        rootView.addView(innerLayout) ;
                        layoutInflater.inflate(R.layout.space_view , rootView ,true) ;
                    }
                }
            }
            rootView.addView(createSubmitView()) ;
        }
    }

    private View createContentView(Area acceptCheckArea){
        LinearLayout content = (LinearLayout) layoutInflater.inflate(R.layout.check_result_layout_inner , null);
        if(acceptCheckArea != null && acceptCheckArea.getPatrolItems() != null){
            int size = acceptCheckArea.getPatrolItems().size() ;
            for(int i = 0 ; i < size ; i++){
                View view = createInnerView(acceptCheckArea.getPatrolItems().get(i)) ;
                content.addView(view) ;
            }
        }
        return content ;
    }

    private View createInnerView(final PatrolItem patrolItem){
        LinearLayout content = (LinearLayout) layoutInflater.inflate(R.layout.check_result_layout_inner_item , null);
        TextView title = (TextView) content.findViewById(R.id.check_result_item_name) ;
        TextView result = (TextView) content.findViewById(R.id.check_result_item_result) ;
        RelativeLayout parent = (RelativeLayout) content.findViewById(R.id.check_result_inner_item_parent) ;
        parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                patrolItems = patrolItem ;
                getActivityGroup().startActivityById(CheckResultDetailActivity.class.getName(),null) ;
                LogUtils.e("点击了这个"+patrolItem.getItemName()+"========") ;
            }
        });
        title.setText(patrolItem.getItemName()) ;
        return content ;
    }

    private View createTitle(final Area acceptCheckArea){
        View view = layoutInflater.inflate(R.layout.form_title , null) ;
        TextView title = (TextView) view.findViewById(R.id.check_area_name_check_result);
        ImageView addProject = (ImageView) view.findViewById(R.id.check_result_add_project_id) ;
        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivityGroup().startActivityById(AddCheckProjectActivity.class.getName(),null) ;
            }
        });
        title.setText(acceptCheckArea.getAreaName()) ;
        return view ;
    }

    private View createSubmitView(){
        LinearLayout submitView = (LinearLayout) layoutInflater.inflate(R.layout.check_result_layout_inner_submit , null);
        LinearLayout submitTime = (LinearLayout) submitView.findViewById(R.id.submit_check_time) ;
        LinearLayout joinPerson = (LinearLayout) submitView.findViewById(R.id.submit_join_person_parent) ;
        TextView timeTV = (TextView) submitView.findViewById(R.id.submit_check_time_tv);
        TextView joinTV = (TextView) submitView.findViewById(R.id.submit_join_person_tv);

        return submitView ;
    }

    @Override
    public void onClick(View v) {
        MainActivity templateActivity = currentActivity.getActivityGroup() ;
        switch (v.getId()){
            case R.id.check_result_inner_item_parent:
                templateActivity.startActivityById(CheckResultDetailActivity.class.getName(), null) ;
            break;
            case R.id.check_result_add_project_id:
                templateActivity.startActivityById(AddCheckProjectActivity.class.getName(), null) ;
            case R.id.title_menu_content_btn:
                templateActivity.startActivityById(AcceptCheckOfAreaActivity.class.getName(), null) ;
            break;
        }
        super.onClick(v);
    }

    @Override
    public void performBackPressed() {
        super.performBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver) ;
    }
}
