package com.android.sunning.riskpatrol.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.R;
import com.android.sunning.riskpatrol.activity.CreateCheckPointActivity;
import com.android.sunning.riskpatrol.activity.CreateProperCheckPointActivity;
import com.android.sunning.riskpatrol.activity.MainActivity;
import com.android.sunning.riskpatrol.activity.ShowCheckDocumentActivity;
import com.android.sunning.riskpatrol.custom.CollapsibleLinearLayout;
import com.android.sunning.riskpatrol.db.DBHelper;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.entity.generate.HomeEntity;
import com.android.sunning.riskpatrol.entity.generate.login.Site;
import com.android.sunning.riskpatrol.net.HttpInteraction;
import com.android.sunning.riskpatrol.net.RequestInfo;
import com.android.sunning.riskpatrol.util.Utils;

/**
 * Created by sunning on 15/2/11.
 * 首页我的任务fragment
 */
public class HomeTaskFragment extends BaseFragment{

    private List<View> views ;
    private DBHelper dbhelper;
    private List<Site> list;
    @Override
    protected int onCreateViewByLayoutId() {
        return R.layout.home_content;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toast.makeText(getActivity(),login.getSites().toString(), Toast.LENGTH_LONG).show();
        dbhelper=DBHelper.getDbHelperInstance(getActivity());
        list=dbhelper.querySites();
       /* if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
        String name=Environment.getExternalStorageDirectory()+"/liangfu/";
        File dir=new File(name);
        if(!dir.exists())
        dir.mkdir();//如果文件夹不存在，创建文件夹
        String path=name+"hello";
        File file = new File(path);
        if(!file.exists()){
        	try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		String r=Uri.fromFile(file).toString();
        }*/
        sendRequest() ;
    }
    /**
     *     	以get方式发送请求，通过传递usID和time，从服务器端获取所需数据（获取日月周的未完成表单）
     */
    private void sendRequest() {
        HttpInteraction httpInteraction = new HttpInteraction() {
            @Override
            public void response(BaseEntity entity) {
            	HomeEntity  homeEntity = (HomeEntity) entity ;
            	dbhelper.saveHomeEntity(homeEntity);
//            	List<Datum> dam=homeEntity.getData();
//            	dbhelper.saveDatum(dam);

//            	Log.i(getTag(),dbhelper.queryDatum().toString());
//            	Toast.makeText(getActivity(),dbhelper.queryDatum().toString(),Toast.LENGTH_LONG).show();
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
        if(views != null && views.size() > 0) {
            for (View comboView : views) {
                // add divider view
                View dividerView = new View(currentActivity);
                dividerView.setBackgroundColor(Color.parseColor("#eeeeee"));
                infoContainer.addView(dividerView, new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, Utils.dip2px(currentActivity, 10)));
                infoContainer.addView(comboView);
            }
        }
        isFirst = false;
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

    private View createChildItem(int index) {
        //@formatter:off
        View childView = inflater.inflate(R.layout.collapsible_child_item, null);
//        childView.findViewById(R.id.home_page_child_item_parent_id).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity templateActivity = currentActivity.getActivityGroup() ;
//                templateActivity.startActivityById(ShowCheckDocumentActivity.class.getName(), null) ;
//            }
//        });
        childView.findViewById(R.id.home_page_child_item_parent_id).setOnLongClickListener(new OnLongClickListener() {
			
			
			public boolean onLongClick(View v) {
				MainActivity templateActivity = currentActivity.getActivityGroup() ;
                templateActivity.startActivityById(ShowCheckDocumentActivity.class.getName(), null) ;
				return false; 
			}
		});
//        ImageView childImageView = (ImageView) comboChildItem.findViewById(R.id.combo_product_iamge);
        TextView childNameView = (TextView) childView.findViewById(R.id.text_comp);
//        TextView childPriceView = (TextView) comboChildItem.findViewById(R.id.combo_product_price);
//        TextView childQuantityView = (TextView) comboChildItem.findViewById(R.id.combo_product_quantity);
        childNameView.setText(list.get(index).getCompanyName());
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


    private View createItem(int index) {
        final View item = inflater.inflate(R.layout.collapsible_item, null);
        TextView title = (TextView) item.findViewById(R.id.item_name);
        ImageView icon = (ImageView) item.findViewById(R.id.item_icon);
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
        ImageView createTask = (ImageView) item.findViewById(R.id.home_icon_create_task) ;
        if(index == Const.CollectType.DEVOTE){
            createTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity templateActivity = currentActivity.getActivityGroup() ;
                    templateActivity.startActivityById(CreateProperCheckPointActivity.class.getName(), null) ;
                }
            });
        }else{
            createTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity templateActivity = currentActivity.getActivityGroup() ;
                    Map<String,String> params = new HashMap<String, String>() ;
                    params.put(Const.KEY.CHECK_POINT_TITLE,"创建日检查单") ;
                    params.put(Const.KEY.CHECK_POINT_TYPE_NEW,Const.KEY.CHECK_POINT_TYPE) ;
                    templateActivity.startActivityById(CreateCheckPointActivity.class.getName(), null) ;
                }
            });
        }
//        TextView nickNameView = (TextView) comboItem.findViewById(R.id.combo_nick_name);
//        TextView nameView = (TextView) comboItem.findViewById(R.id.combo_name);
//        final ImageView arrowImage = (ImageView) comboItem.findViewById(R.id.combo_arrow_image);
//        final View toggleBtn = comboItem.findViewById(R.id.combo_title_container);
        final CollapsibleLinearLayout childContainer = (CollapsibleLinearLayout)item. findViewById(R.id.combo_products_container);
//        childContainer.setToggleView(arrowImage);
        // create child product views
//        if (combo.comboProducts != null && combo.comboProducts.size() > 0) {
//       		login=dbhelper.queryCurrentLogin();
            for (int i = 0 ; i < list.size() ; i ++) {
                //if( childBean.tcdDetailStatus == 1 ){ // 判断套餐中的商品是否过滤   移至组装数据ProductJson中判断
                View childView = createChildItem(i);
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
