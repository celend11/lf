package com.android.sunning.riskpatrol.activity;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.yindongxunjian.R;
import com.android.sunning.riskpatrol.RiskPatrolApplication;
import com.lidroid.xutils.util.LogUtils;
//是一个ActivityGroup类，为了实现下导航所以其他的界面都是放入framelayout的里面的fragment
public class MainActivity extends ActivityGroup {

    protected final int GRAY = 0;
    protected final int LIGHT = 1;
    public static final int FORWARD = 0;
    public static final int BACK = 1;
    private FrameLayout container = null;
    public int currentMenuItemId;
//    继承与application的一个类
    private RiskPatrolApplication rIApplication;
//    相当于arraylist与hashmap,但是相比较来说SparseArray执行效率较高一些
    private SparseArray<String> imageMap = new SparseArray<String>();
    //	private ImageView lastView;
//    是整型的一个封装类，相比于Integer而言，具有线程安全性,Atomic即原子量类，就是指整型里面的最小单位
    private AtomicInteger ai;
    public ImageView lastView;

    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {

        @Override
        public void onAnimationStart(Animation arg0) {
            ai.incrementAndGet();// 相当与i++
        }

        @Override
        public void onAnimationRepeat(Animation arg0) {

        }

        @Override
        public void onAnimationEnd(Animation arg0) {
            ai.decrementAndGet();// 相当与i--
        }
    };
  
    private void initImageMap() {
        imageMap.put(R.id.radio_one, R.drawable.tab_home + "," + R.drawable.tab_home_press);
        imageMap.put(R.id.radio_two, R.drawable.tab_my_collect + "," + R.drawable.tab_my_collect_press);
        imageMap.put(R.id.radio_three, R.drawable.tab_setup + "," + R.drawable.tab_setup_press);
    }

    /**
     * Called when the activity is first created.
     * 当activity第一次被创建是调用
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        ai = new AtomicInteger();
        initImageMap();
        lastView = (ImageView) findViewById(R.id.radio_one);
        container = (FrameLayout) findViewById(R.id.body);
        rIApplication = ((RiskPatrolApplication) this.getApplication());
        rIApplication.getSession().put(RiskPatrolApplication.MAIN_ACTIVITY, this);
        startActivityById(HomeActivity.class.getName(), null);
    }
    /**
     * 在SparseArray<String> 中 根据关键字查找相应的图标
     */
    private int getImageResourceByName(int key, int type) {
        String group = imageMap.get(key);
        String[] ids = group.split(",");
        return Integer.parseInt(ids[type]);
    }
    /**
     * ImageView设置图片
     */
    private void setImageView(ImageView image, int resourseId) {
        if (image == null) {
            return;
        }
        Drawable d = getResources().getDrawable(resourseId);
        image.setImageDrawable(d);
    }
    /**
     * 通过判断v，当v与lastview不一样时，图片改变，背景为灰色
     * @param v
     */
    private void changeBg(ImageView v) {
        setImageView(v, getImageResourceByName(v.getId(), LIGHT));
        v.setImageResource(getImageResourceByName(v.getId(), LIGHT));
        if (lastView != null && lastView != v) {
            setImageView(lastView, getImageResourceByName(lastView.getId(), GRAY));
        }
        lastView = v;
    }

    public void clickBottom(View view) {
        int currentAnimCount = ai.get();
        if (currentAnimCount > 0) {
            return;
        }
        int checkedId = view.getId();
//        如果当前的view id与点击的radiobutton的id一致，点击时不会返回最
        int lastCheckedId = lastView.getId();
        if (checkedId == lastCheckedId) {
            return;
        }

        changeBg((ImageView) view);
        switch (checkedId) {
            case R.id.radio_one:
                startActivityById(HomeActivity.class.getName(), null);
                break;
            case R.id.radio_two:
                startActivityById(MyCollectActivity.class.getName(), null);
                break;
            case R.id.radio_three:
                startActivityById(SetupActivity.class.getName(), null);
                break;
        }
    }

    public void setBody(final View view, final int position) {
        container.post(new Runnable() {
            @Override
            public void run() {
//            	设置动画
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, position == FORWARD ? R.anim.out_right_to_left : R.anim.out_left_to_right);
                anim.setAnimationListener(animationListener);
                Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, position == FORWARD ? R.anim.in_right_to_left : R.anim.in_left_to_right);
                anim1.setAnimationListener(animationListener);
                View body = container.getChildAt(0);
                if (body != null) {
                    container.getChildAt(0).startAnimation(anim);
                    container.removeAllViews();
                }
                container.addView(view);
                view.startAnimation(anim1);
            }
        });

    }


    /* (non-Javadoc) 
     * @param event
     * @return 
     * @see android.app.Activity#dispatchKeyEvent(android.view.KeyEvent) 
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        int action = event.getAction();
        if (keyCode == KeyEvent.KEYCODE_MENU) {
        	Activity current = getLocalActivityManager().getCurrentActivity();
            if (current != null) {
                current.openOptionsMenu();
            }
        } else if (keyCode == KeyEvent.KEYCODE_BACK && action == KeyEvent.ACTION_DOWN) {
            BaseActivity base = BaseActivity.currentActivity;
            base.performBackPressed();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    public void startActivityById(String clas, Map<String, String> para) {
        try {
            setUpIntent(clas, para);
        } catch (ClassNotFoundException e) {
            LogUtils.e(e.getMessage());
        }
    }

    private void setUpIntent(String clas, Map<String, String> para) throws ClassNotFoundException {
        Class<?> targetClas = Class.forName(clas);
        Activity activity = getLocalActivityManager().getActivity(clas);
        if (activity == null) {
            Intent intent = new Intent(this, targetClas);
            if (para != null && !para.isEmpty()) {
                Iterator<String> it = para.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    String value = para.get(key);
                    intent.putExtra(key, value);
                }
            }
            Window window = getLocalActivityManager().startActivity(clas, intent);
            setBody(window.getDecorView(), FORWARD);
            return;
        }
        BaseActivity.currentActivity = (BaseActivity) activity;
        setBody(activity.getWindow().getDecorView());
        BaseActivity.currentActivity.changeStack();
    }

    private void setBody(View view) {
        View oldView = container.getChildAt(0);
        if (oldView != null) {
            container.removeAllViews();
        }
        container.addView(view);
    }


//    private void startActivityById(String id,Class<?> clas){
//		Activity activity = getLocalActivityManager().getActivity(id);
//		if(activity!=null){
//			BaseActivity.currentActivity = (BaseActivity)activity;
//			setBody(activity.getWindow().getDecorView(),FORWARD);
//		}
//		else{
//		    setBody(getLocalActivityManager().startActivity(id, new Intent(this, clas)).getDecorView(),FORWARD);
//		}
//	}
}
