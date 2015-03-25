package com.android.sunning.riskpatrol;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import android.os.Environment;

import com.android.sunning.riskpatrol.activity.BaseActivity;
import com.android.sunning.riskpatrol.activity.MainActivity;
import com.android.sunning.riskpatrol.system.ActivityStackOrderManager;
import com.android.sunning.riskpatrol.system.CrashHandler;
import com.android.sunning.riskpatrol.system.HandlerManager;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by sunning on 15/2/7.
 */
public class RiskPatrolApplication extends Application{

    public static final String MAIN_ACTIVITY ="mainActivity";

    public String mImagePath ;

    public int mImageType = -1;

    public int unUploadCount ;

    private RiskPatrolApplication application;

    private Map<String,Object> session = new HashMap<String, Object>();

    public Map<String,Object> getSession(){
        return session;
    }


    /**
     * 完全退出
     *
     * */
    @SuppressWarnings("deprecation")
    public void exit(BaseActivity activity){
        HandlerManager.getInstance().destroyHandler();
        MainActivity mf = (MainActivity)session.get(MAIN_ACTIVITY);
        mf.finish();
        mf.getLocalActivityManager().dispatchDestroy(true);
        session.clear();
        application = (RiskPatrolApplication) activity.getApplication() ;
        application.getSession().clear() ;
        ActivityStackOrderManager.getASOMInstance().destroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String[] paths = new String[]{Const.Path.IMAGE_DIR, Const.Path.APP_DIR, Const.Path.CAMERA,Const.Path.CACHE_DIR,Const.Path.FILE_LOCAL,Const.Path.FILE_SYNCHRONIZATION ,Const.Path.RECORD_LOCAL};
            File dir;
            for (int i = 0; i < paths.length; i++) {
                dir = new File(paths[i]);
                if (!dir.exists() && !dir.mkdirs()) {
                    dir.mkdir();
                }
            }
        }

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(2 * 1024 * 1024)
                .discCache(new UnlimitedDiscCache(new File(Const.Path.CACHE_DIR)))//自定义缓存路径
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new WeakMemoryCache())
                .threadPoolSize(3)//线程池内加载的数量

                .build();
        ImageLoader.getInstance().init(config) ;

    }
}
