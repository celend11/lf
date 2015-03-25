package com.android.sunning.riskpatrol.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.adapter.Adapter4AcceptCheckOfProjectList;
import com.android.sunning.riskpatrol.adapter.CommonAdapter;
import com.android.sunning.riskpatrol.entity.generate.login.Site;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/13.
 * 受检项目列表形式的，没有可收缩的交互方式 因为接口返回的数据就是这样的。
 */
public class AcceptCheckOfProjectListActivity extends ListViewActivity<Site>{

    private Adapter4AcceptCheckOfProjectList projectListAdapter ;

    @Override
    protected void findView() {
        listView = (ListView) findViewById(R.id.accept_check_leader_list) ;
    }

    @Override
    protected void findEvent() {
        super.findEvent();
    }

    @Override
    protected CommonAdapter getAdapter() {
        projectListAdapter = new Adapter4AcceptCheckOfProjectList(this , dataForAdapter , R.layout.accept_check_project_child_item) ;
        return  projectListAdapter ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.accept_check_leader_layout);
        super.onCreate(savedInstanceState) ;
        setTitle("受检项目") ;
        setRightListener(this) ;
        sendRequest() ;
    }

    private void sendRequest() {
        List<Site> list = dbHelper.querySites() ;
        dataForAdapter.addAll(list) ;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_menu_content_btn:
                if(projectListAdapter.getSelectFlag() != -1){
                    CreateCheckPointActivity createCheckPointActivity = (CreateCheckPointActivity) application.getSession().get(Const.KEY.CURRENT_CHECK_POINT) ;
                    Site site = projectListAdapter.getItem(projectListAdapter.getSelectFlag()) ;
                    com.android.sunning.riskpatrol.entity.generate.Site selectSite = new com.android.sunning.riskpatrol.entity.generate.Site() ;
                    selectSite.setSiteID(site.getID()) ;
                    selectSite.setSiteName(site.getName()) ;
                    createCheckPointActivity.rootDatum.setSite(selectSite) ;
                    createCheckPointActivity.setProjectTitle(site.getName()) ;
//                    application.getSession().remove(Const.KEY.CURRENT_CHECK_POINT) ;
                    performBackPressed() ;
                }else{
                    toast("请选择受检项目");
                }
                break;
        }
        super.onClick(v);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        projectListAdapter.setSelect(position) ;
        projectListAdapter.notifyDataSetChanged();
        super.onItemClick(parent, view, position, id);
    }

}
