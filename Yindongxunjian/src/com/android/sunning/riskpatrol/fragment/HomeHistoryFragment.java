package com.android.sunning.riskpatrol.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.activity.MainActivity;
import com.android.sunning.riskpatrol.activity.ShowCheckDocumentActivity;
import com.android.sunning.riskpatrol.custom.CollapsibleLinearLayout;
import com.android.sunning.riskpatrol.db.DBHelper;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.util.Utils;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/27.
 * 首页历史任务fragment
 */
public class HomeHistoryFragment extends BaseFragment {

    private List<View> views;
    private DBHelper dbhelper;
    @Override
    protected int onCreateViewByLayoutId() {
        return R.layout.home_content;
    }

    @Override
    public void onResume() {
        super.onResume();
        start() ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendRequest();
    }

    private void sendRequest() {
        HttpInteraction httpInteraction = new HttpInteraction() {
            @Override
            public void response(BaseEntity entity) {
            	
            }
        };
        RequestInfo requestInfo = new RequestInfo(Const.InterfaceName.GET_NORMAL_CHECK_LIST, httpInteraction) {
            @Override
            protected void addParams() {
            	dbhelper=DBHelper.getDbHelperInstance(getActivity());
            	login=dbhelper.queryCurrentLogin();
                requestParams.addQueryStringParameter("usID", login.getID());
                requestParams.addQueryStringParameter("time", Utils.getCurrentDate());
            }
        };
        requestInfo.execute();
    }

    @Override
    protected void start() {
        if (!isFirst) {
            return;
        }
        views = createContentViews();
        LinearLayout infoContainer = (LinearLayout) currentView.findViewById(R.id.home_container);
        infoContainer.addView(createChooseHistory());
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

    private View createChildItem() {
        View childView = inflater.inflate(R.layout.collapsible_child_item, null);
        childView.findViewById(R.id.home_page_child_item_parent_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity templateActivity = currentActivity.getActivityGroup();
                templateActivity.startActivityById(ShowCheckDocumentActivity.class.getName(), null);
            }
        });

        return childView;
    }

    private View createChooseHistory() {
        final View tabHistory = inflater.inflate(R.layout.tab_history, null);
        return tabHistory;
    }

    private View createItem(int index) {
        final View item = inflater.inflate(R.layout.collapsible_item_history, null);
        TextView title = (TextView) item.findViewById(R.id.item_name);
        ImageView icon = (ImageView) item.findViewById(R.id.item_icon);
        String data = getCurrentItemData(index);
        if (!TextUtils.isEmpty(data)) {
            String[] res = data.split("&");
            if (res.length == 2) {
                String titleRes = res[0];
                int iconRes = Integer.parseInt(res[1]);
                title.setText(titleRes);
                icon.setImageResource(iconRes);
            }
        }
        final CollapsibleLinearLayout childContainer = (CollapsibleLinearLayout) item.findViewById(R.id.combo_products_container);
        for (int i = 0; i < 5; i++) {
            View childView = createChildItem();
            childContainer.addView(childView);
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
