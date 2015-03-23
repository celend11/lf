package com.android.sunning.riskpatrol.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.sunning.riskpatrol.custom.CollapsibleLinearLayout;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/13.
 * 受检项目
 */
public class AcceptCheckOfProjectActivity extends BaseActivity{

    private List<View> views ;
    private LayoutInflater inflater ;


    @Override
    protected void findView() {

    }

    @Override
    protected void findEvent() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.accept_project_layout) ;
        super.onCreate(savedInstanceState) ;
        inflater = LayoutInflater.from(this) ;
        setTitle("受检项目") ;
        initView() ;
    }

    private void initView() {
        views = createContentViews() ;
        LinearLayout infoContainer = (LinearLayout) currentActivity.findViewById(R.id.home_container);
        if(views != null && views.size() > 0) {
            for (View view : views) {
                infoContainer.addView(view);
                View dividerView = new View(currentActivity);
                dividerView.setBackgroundColor(Color.parseColor("#f8f8f8"));
            }
        }
    }

    private List<View> createContentViews() {
        List<View> contentViews = new ArrayList<View>();
//        int realIndex = 0;
//        if (comboInfo != null && comboInfo.size() != 0) {
        for (int index = 0; index < 5 ; index++) {
//                ProductDetailVO combo = comboInfo.get(index);
//                if(combo.comboProducts!=null){
//                    if( combo.comboProducts.size() != 0 && combo.status == 0 ){ // 不展示status为4
//                        if(combo.comboProducts.size() > 1 || (combo.comboProducts.get(0)).tcdDetailCount > 1){  //size>1  过滤商品只有主品的套餐
            View childView = createItem(index) ;
            if(childView != null) {
                contentViews.add(childView);
                childView.setOnClickListener(this);
            }
//                            realIndex ++;
//                        }
//                    }
//                }
//            }
        }
        return contentViews;

    }

    private View createItem(int index) {
        //@formatter:off
        final View item = inflater.inflate(R.layout.accept_check_project_layout, null);
//        TextView nickNameView = (TextView) comboItem.findViewById(R.id.combo_nick_name);
//        TextView nameView = (TextView) comboItem.findViewById(R.id.combo_name);
        final ImageView arrowImage = (ImageView) item.findViewById(R.id.accept_check_leader_up_arrow);
//        final View toggleBtn = comboItem.findViewById(R.id.combo_title_container);
        final CollapsibleLinearLayout childContainer = (CollapsibleLinearLayout)item. findViewById(R.id.accept_check_project_container);
        childContainer.setToggleView(arrowImage);
        // create child product views
//        if (combo.comboProducts != null && combo.comboProducts.size() > 0) {
        for (int i = 0 ; i < 5 ; i ++) {
            //if( childBean.tcdDetailStatus == 1 ){ // 判断套餐中的商品是否过滤   移至组装数据ProductJson中判断
            View childView = createChildItem();
            childContainer.addView(childView);

            //}
        }
//        }
        if(index == 0)
            childContainer.expand();
        else
            childContainer.collapse();
        return item ;

    }

    private View createChildItem() {
        View comboChildItem = inflater.inflate(R.layout.accept_check_project_child_item, null);
        return comboChildItem;
    }

    @Override
    public void onClick(View v) {
//        if(comboViews != null && comboViews.size() > 0) {
        for (View view : views) {
            CollapsibleLinearLayout collapsibleLinearLayout = (CollapsibleLinearLayout) view.findViewById(R.id.accept_check_project_container);
            if (collapsibleLinearLayout != null && v == view) {
                collapsibleLinearLayout.toggle();
            } else {
                if (collapsibleLinearLayout != null && collapsibleLinearLayout.isExpanded()) {
                    collapsibleLinearLayout.collapse();
                }
            }

//            }
        }
    }

}
