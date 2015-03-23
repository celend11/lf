package com.android.sunning.riskpatrol.fragment;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sunning.riskpatrol.Const;
import com.example.yindongxunjian.R;
import com.android.sunning.riskpatrol.activity.CreateCheckPointActivity;
import com.android.sunning.riskpatrol.activity.CreateProperCheckPointActivity;
import com.android.sunning.riskpatrol.activity.MainActivity;
import com.android.sunning.riskpatrol.activity.ShowCheckDocumentActivity;
import com.android.sunning.riskpatrol.custom.CollapsibleLinearLayout;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.util.Utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunning on 15/2/27.
 * 首页历史任务fragment
 */
public class HomeHistoryFragment extends BaseFragment{

    private List<View> views ;
    private TextView text_finsh;
    private TextView text_unfinsh;
    private EditText edt_start,edt_end;
    private long second1,second2;
    @Override
    protected int onCreateViewByLayoutId() {
        return R.layout.home_content;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendRequest() ;
    }
/**
 *     	以get方式发送请求，通过传递usID和time，从服务器端获取所需数据（获取日月周的未完成表单）
 */
    private void sendRequest() {
        HttpInteraction httpInteraction = new HttpInteraction() {
            @Override
            public void response(BaseEntity entity) {
            	
            }
        } ;
        RequestInfo requestInfo = new RequestInfo(Const.InterfaceName.GET_NORMAL_CHECK_LIST , httpInteraction) {
            @Override
            protected void addParams() {
                requestParams.addQueryStringParameter("usID","4900c2da-7ec4-4fb5-b2ae-c32a28ba5b0a");
                requestParams.addQueryStringParameter("time","2015-02-10");
            }
        } ;
        requestInfo.execute();
    }

    @Override
    protected void start() {
//        for(int i= 0 ; i < 5 ; i++){
//            contentViews.addView(createComboItem(i)) ;
//        }
        if(!isFirst){
            return;
        }
        views = createContentViews() ;
        LinearLayout infoContainer = (LinearLayout) currentActivity.findViewById(R.id.home_container);
        infoContainer.addView(createChooseHistory()) ;
        if(views != null && views.size() > 0) {
            for (View comboView : views) {
                // add divider view
                View dividerView = new View(currentActivity);
                dividerView.setBackgroundColor(Color.parseColor("#eeeeee"));
                infoContainer.addView(dividerView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.dip2px(currentActivity, 10)));
                infoContainer.addView(comboView);
            }
        }
        isFirst = false ;
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

    @Override
    public void findView() {
    }

    private View createChildItem() {
        //@formatter:off
        View childView = inflater.inflate(R.layout.collapsible_child_item, null);
        childView.findViewById(R.id.home_page_child_item_parent_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity templateActivity = currentActivity.getActivityGroup() ;
                templateActivity.startActivityById(ShowCheckDocumentActivity.class.getName(), null) ;
            }
        });

//        ImageView childImageView = (ImageView) comboChildItem.findViewById(R.id.combo_product_iamge);
//        TextView childNameView = (TextView) comboChildItem.findViewById(R.id.combo_product_name);
//        TextView childPriceView = (TextView) comboChildItem.findViewById(R.id.combo_product_price);
//        TextView childQuantityView = (TextView) comboChildItem.findViewById(R.id.combo_product_quantity);
//
//
//        imageLoaderUtil.loadImage(childBean.mainImg5, childImageView, ImageLoaderUtil.PIC_TYPE_SELF_ADAPTER);
//        childNameView.setText(childBean.productName);
//        childPriceView.setText("￥" + Util.getDecimalPoint(childBean.originalPrice));
//        childQuantityView.setText("x" + childBean.tcdDetailCount);
//        comboChildItem.setTag(childBean);
//        comboChildItem.setOnClickListener(productClickListener);
        //@formatter:on
        return childView;
    }

    private View createChooseHistory(){
        final View tabHistory = inflater.inflate(R.layout.tab_history, null) ;
        edt_start=(EditText) tabHistory.findViewById(R.id.start_time);
        edt_end=(EditText) tabHistory.findViewById(R.id.end_time);
        edt_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DatePickerDialog dialog=new DatePickerDialog(getActivity(), new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						edt_start.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
						second1=year*(monthOfYear+1)*dayOfMonth*24*60*60*1000;
					}
				}, 2015, 3, 18);
				dialog.show();
				
			}
		});
        edt_end.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DatePickerDialog dialog=new DatePickerDialog(getActivity(), new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						edt_end.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);	
						second2=year*(monthOfYear+1)*dayOfMonth*24*60*60*1000;
						if(second2<second1){
							Toast.makeText(getActivity(), "结束时间不能小于开始时间", Toast.LENGTH_LONG).show();
						}
						if((second2-second1)>2592000*1000){
							Toast.makeText(getActivity(), second2+"", Toast.LENGTH_LONG).show();
						}
					}
				}, 2015, 3, 18);
				dialog.show();

			}
		});
        return tabHistory ;
    }

    private View createItem(int index) {
        final View item = inflater.inflate(R.layout.collapsible_item_history, null);
        TextView title = (TextView) item.findViewById(R.id.item_name);
        ImageView icon = (ImageView) item.findViewById(R.id.item_icon);
        text_finsh=(TextView) item.findViewById(R.id.finsh);
        text_unfinsh=(TextView) item.findViewById(R.id.unfinsh);
    	text_finsh.setOnClickListener(this);
    	text_unfinsh.setOnClickListener(this);
        String data = getCurrentItemData(index) ;
        if(!TextUtils.isEmpty(data)){
            String[] res = data.split("&") ;
            if(res.length == 2){
                String titleRes = res[0] ;
                int iconRes = Integer.parseInt(res[1]) ;
                title.setText(titleRes) ;
                icon.setImageResource(iconRes) ;
            }
        }
//        TextView nickNameView = (TextView) comboItem.findViewById(R.id.combo_nick_name);
//        TextView nameView = (TextView) comboItem.findViewById(R.id.combo_name);
//        final ImageView arrowImage = (ImageView) comboItem.findViewById(R.id.combo_arrow_image);
//        final View toggleBtn = comboItem.findViewById(R.id.combo_title_container);
        final CollapsibleLinearLayout childContainer = (CollapsibleLinearLayout)item. findViewById(R.id.combo_products_container);
//        childContainer.setToggleView(arrowImage);
        // create child product views
//        if (combo.comboProducts != null && combo.comboProducts.size() > 0) {
            for (int i = 0 ; i < 0 ; i ++) {
                //if( childBean.tcdDetailStatus == 1 ){ // 判断套餐中的商品是否过滤   移至组装数据ProductJson中判断
                View childView = createChildItem();
                childContainer.addView(childView);
                //}
            }
//        }
//        View view = createComboBottomBar(combo);
//        if(null!=view) {
//            childContainer.addView(view);
//        }else{
//            return null;
//        }
        if(index == 0)
//        	日检查默认展开
            childContainer.expand();
        else
            childContainer.collapse();

        return item;
    }

    @Override
    public void onClick(View v) {
//        if(comboViews != null && comboViews.size() > 0) {
            for (View view : views) {
                CollapsibleLinearLayout collapsibleLinearLayout = (CollapsibleLinearLayout) view.findViewById(R.id.combo_products_container);
              
                if (collapsibleLinearLayout != null && v == view.findViewById(R.id.finsh)) {
                    Toast.makeText(getActivity(), "已完成", Toast.LENGTH_LONG).show();
                } else {
                    if (collapsibleLinearLayout != null && v ==view.findViewById(R.id.unfinsh)) {
                    	Toast.makeText(getActivity(), ((TextView)view.findViewById(R.id.item_name)).getText()+"", Toast.LENGTH_LONG).show();
                    }
                } 

//            }
        }
    }

    public String getCurrentItemData(int index){
        String returnData = null;
        switch (index) {
            case Const.CollectType.DAY:
                returnData = currentActivity.getString(R.string.check_day) +"&"+R.drawable.home_icon_day ;
                break;
            case Const.CollectType.WEEK:
                returnData = currentActivity.getString(R.string.check_week)+"&"+R.drawable.home_icon_week ;
                break;
            case Const.CollectType.MONTH:
                returnData = currentActivity.getString(R.string.check_month)+"&"+R.drawable.home_icon_mouth ;
                break;
            case Const.CollectType.DEVOTE:
                returnData = currentActivity.getString(R.string.check_devote)+"&"+R.drawable.home_icon_proper ;
                break;
            case Const.CollectType.GROUP:
                returnData = currentActivity.getString(R.string.check_group)+"&"+R.drawable.home_icon_group ;
                break;
        }
        return returnData ;
    }
}
