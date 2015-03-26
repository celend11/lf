package com.android.sunning.riskpatrol.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.activity.CreateCheckPointActivity;
import com.android.sunning.riskpatrol.activity.CreateProperCheckPointActivity;
import com.android.sunning.riskpatrol.activity.MainActivity;
import com.android.sunning.riskpatrol.custom.CollapsibleLinearLayout;
import com.android.sunning.riskpatrol.db.DBHelper;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.HomePageEntity;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.entity.generate.HomeEntity;
import com.android.sunning.riskpatrol.local.Sorting;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/11.
 * 首页我的任务fragment
 */
public class HomeTaskFragment extends BaseFragment {
    private HomeEntity homeEntity ;
    private SparseArray<List<HomePageEntity>> uiLevel ;
    private List<View> views;

    @Override
    protected int onCreateViewByLayoutId() {
        return R.layout.home_content;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = DBHelper.getDbHelperInstance(currentActivity).queryCurrentLogin();
        sendRequest();
    }

    private void sendRequest() {
        if (Utils.isNetworkAvailable(getActivity())) {
            HttpInteraction httpInteraction = new HttpInteraction() {
                @Override
                public void response(BaseEntity entity) {
                    homeEntity = (HomeEntity) entity;
                    DBHelper dbheler=DBHelper.getDbHelperInstance(getActivity());                   
                    uiLevel = new Sorting().sort(homeEntity).obtainProcessFinishDatum();
                    int size=uiLevel.size();
                    for(int i=0;i<size;i++){
                    	dbheler.saveHomePageEntity(uiLevel.get(i),i);
                    }
//                    Toast.makeText(getActivity(), dbheler.queryHomePageEntitys().get(Const.CollectType.DAY).creatorName,Toast.LENGTH_LONG).show();
                    start();
                }
            };
            RequestInfo requestInfo = new RequestInfo(Const.InterfaceName.GET_NORMAL_CHECK_LIST, httpInteraction) {
                @Override
                protected void addParams() {
                    requestParams.addQueryStringParameter("usID", login.getID());
                    //2013-02-26 8条数据
                    //2014-02-26 2条数据
                    //2014-02-27 1条数据
                    //todo 现在时间是写死了，到时候要改回去。
                    requestParams.addQueryStringParameter("time",Utils.getCurrentDate());
                }
            };
            requestInfo.execute();
        } else {
        		
//        		start();
        }
    }

    protected void start() {
        if (!isFirst) {
            return;
        }
        views = createContentViews();
        LinearLayout infoContainer = (LinearLayout) currentView.findViewById(R.id.home_container);
        if (views != null && views.size() > 0) {
            for (View childView : views) {
                View dividerView = new View(currentActivity);
                dividerView.setBackgroundColor(Color.parseColor("#eeeeee"));
                infoContainer.addView(dividerView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.dip2px(currentActivity, 10)));
                infoContainer.addView(childView);
            }
        }
        isFirst = false;
    }

    private List<View> createContentViews() {
        List<View> contentViews = new ArrayList<View>();
        for (int index = 0; index < 5; index++) {
            View childView = createItem(index);
            if (childView != null) {
                contentViews.add(childView);
                childView.setOnClickListener(this);
            }
        }
        return contentViews;
    }

    @Override
    public void findView() {
    }

    private View createChildItem(final HomePageEntity entity) {
        View childView = inflater.inflate(R.layout.collapsible_child_item, null);
        TextView date = (TextView) childView.findViewById(R.id.home_page_task_create_date);
        TextView dispatchName = (TextView) childView.findViewById(R.id.home_page_task_dispatch_name);
        TextView num = (TextView) childView.findViewById(R.id.home_page_task_num);
        TextView title = (TextView) childView.findViewById(R.id.home_page_task_patrol_name);
        date.setText(Utils.formatJSONDate(entity.createDate));
        if(entity.isDispatch){
            dispatchName.setText(entity.creatorName+"派发") ;
        }else{
            dispatchName.setVisibility(View.GONE);
        }
        num.setText("("+entity.inspectNumFormat+")");
        title.setText(entity.patrolItemName) ;
        childView.findViewById(R.id.home_page_child_item_parent_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity templateActivity = currentActivity.getActivityGroup();
                getDatumByInsectNum(entity.inspectNum);
                Map<String,String> params = new HashMap<String, String>() ;
                params.put(Const.KEY.URI,"") ;//填这个只是为了进下一个界面的时候bundle不为null
                templateActivity.startActivityById(CreateCheckPointActivity.class.getName(), params) ;
            }
        });
        return childView;
    }

    private View createItem(final int index){
        final View item = inflater.inflate(R.layout.collapsible_item, null);
        TextView title = (TextView) item.findViewById(R.id.item_name);
        ImageView icon = (ImageView) item.findViewById(R.id.item_icon);
        String data = getCurrentItemData(index);
        if (!TextUtils.isEmpty(data)) {
            String[] res = data.split("&");
            if (res.length == 2) {
                String titleRes = res[0];
                int count=0;
                if(uiLevel.get(index)!=null){
                	count=uiLevel.get(index).size();
                }
                int iconRes = Integer.parseInt(res[1]);
                title.setText(titleRes+"("+count+")");
                icon.setImageResource(iconRes);
            }
        }
        ImageView createTask = (ImageView) item.findViewById(R.id.home_icon_create_task);
        final String titleText = getCurrentItemData(index).split("&")[0] ;
        if (index == Const.CollectType.DEVOTE) {
            createTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity templateActivity = currentActivity.getActivityGroup();
                    templateActivity.startActivityById(CreateProperCheckPointActivity.class.getName(), null);
                }
            });
        } else {
            createTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity templateActivity = currentActivity.getActivityGroup();
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(Const.KEY.CHECK_POINT_TITLE, titleText);
                    params.put(Const.KEY.CHECK_POINT_TYPE_NEW, Const.KEY.CHECK_POINT_TYPE);
                    templateActivity.startActivityById(CreateCheckPointActivity.class.getName(), params);
                }
            });
        }
        final CollapsibleLinearLayout childContainer = (CollapsibleLinearLayout) item.findViewById(R.id.combo_products_container);
        if(index < uiLevel.size()){
            List<HomePageEntity> currentEntity = uiLevel.get(index) ;
            int size = currentEntity.size() ;
            for (int i = 0; i < size; i++) {
                HomePageEntity entity = currentEntity.get(i) ;
                View childView = createChildItem(entity);
                childContainer.addView(childView);
            }
        }
        if (index == 0)
            childContainer.expand();
        else
            childContainer.collapse();
        return item;
    }

    @Override
    public void onClick(View v) {
        for (View view : views) {
            CollapsibleLinearLayout collapsibleLinearLayout = (CollapsibleLinearLayout) view.findViewById(R.id.combo_products_container);
            if (collapsibleLinearLayout != null && v == view) {
                collapsibleLinearLayout.toggle();
            } else {
                if (collapsibleLinearLayout != null && collapsibleLinearLayout.isExpanded()) {
                    collapsibleLinearLayout.collapse();
                }
            }
        }
    }

    public void getDatumByInsectNum(String insectNum){
        if(homeEntity!= null && homeEntity.getData()!=null){
            List<Datum> datumList = homeEntity.getData() ;
            for(Datum datum : datumList){
                if(datum.getInspectNum().equals(insectNum)){
                    application.getSession().put(Const.KEY.CURRENT_EDIT_DATUM , datum) ;
                    return;
                }
            }
        }
    }

    private String getFilePath(HomePageEntity homeEntity){
        String fileName = new StringBuilder(Const.Path.FILE_SYNCHRONIZATION).append(homeEntity.creatorID).append("@").append(homeEntity.inspectNum).append(".rp").toString() ;
        return  fileName;
    }

    public String getCurrentItemData(int index) {
        String returnData = null;
        switch (index) {
            case Const.CollectType.DAY:
                returnData = currentActivity.getString(R.string.check_day) + "&" + R.drawable.home_icon_day;
                break;
            case Const.CollectType.WEEK:
                returnData = currentActivity.getString(R.string.check_week) + "&" + R.drawable.home_icon_week;
                break;
            case Const.CollectType.MONTH:
                returnData = currentActivity.getString(R.string.check_month) + "&" + R.drawable.home_icon_mouth;
                break;
            case Const.CollectType.DEVOTE:
                returnData = currentActivity.getString(R.string.check_devote) + "&" + R.drawable.home_icon_proper;
                break;
            case Const.CollectType.GROUP:
                returnData = currentActivity.getString(R.string.check_group) + "&" + R.drawable.home_icon_group;
                break;
        }
        return returnData;
    }
}
