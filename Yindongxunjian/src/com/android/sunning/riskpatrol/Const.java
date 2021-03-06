package com.android.sunning.riskpatrol;

import android.os.Environment;

import com.android.sunning.riskpatrol.activity.HomeActivity;
import com.android.sunning.riskpatrol.activity.MyCollectActivity;
import com.android.sunning.riskpatrol.activity.SetupActivity;

/**
 * Created by sunning on 14-7-31.
 */
public class Const {
    public static final String URL = "http://cqjg-test.telsafe.com.cn/";
//    public static final String URL = "http://192.168.90.51:8081/";
    public static final String APP_ID = "nhf2iv20n9vev3k4bqrp5i";
    public static final String SECRET_KEY = "33cth9t1dt0i9h9eoaba40g2ciorg6dz3hxjzrpc3csw9qcev7fx22alrcfc96fjexs47nd9e24v85n71b7vn5jmg5atmlm2kl8n0k03r6bks17vj4h90eljgueiec0h";

    public static final int MAX_UPLOAD_IMG_COUNT = 5 ;
    public static final boolean REQUEST_TYPE_IS_POST = false ;

    public static final String WX_APP_ID = "wx6ee9e32cb7ff0bd5";
    public static String APP_SECRET = "558f2065acfb9655e8e2f362b13ba89c";




    public int DBVersion = -1;

    /** 数据库下载地址 */
    public String dbUrl;

    /** 当前版本 */
    public static final int CUR_VERSION = 1;

    public static final int AUTO_SAVE_TIME = 10 * 1000 ;




    public static final String bottomMenuRootClass[] = {
            HomeActivity.class.getSimpleName(), MyCollectActivity.class.getSimpleName(),
            SetupActivity.class.getSimpleName()};


    public static final int UPLOAD_ON_START = -1;
    public static final int UPLOAD_SUCCESS = -2;
    public static final int UPLOAD_FAIL = -3;
    public static final int UPLOAD_LOADING = -4;

    public static final int START = 0;
    public static final int SUCCESS = 1;
    public static final int HTTP_SUCCESS = 200;
    public static final int FAIL = -1;
    public static final int PAGE_OFFSET = 20;

    private static String version = "1.0.0";


    public static class BroadcastReceiver{
        public final static String ADD_PROJECT = "com.draw.view.receiver" ;
        public final static String ADD_RISK_ELEMENT = "com.draw.view.risk.elememt" ;
    }
    public static class KEY{
        public final static String CURRENT_CHECK_POINT = "current_check_point" ;

        public final static String CURRENT_RESULT_DETAIL = "current_result_detail" ;
        public final static String CHECK_POINT_TITLE = "check_point_title" ;
        public final static String CHECK_POINT_TYPE = "check_point_type" ;

        public final static String CHECK_POINT_TYPE_NEW = "check_point_type_new" ;
        public final static String CHECK_POINT_TYPE_WEB = "check_point_type_new_web" ;

        public final static String COLLECT_LIST_FLAG = "collect_list_flag" ;

        public final static String COLLECT_LIST_TYPE = "collect_list_type" ;

        public final static String COLLECT_LIST_IS_FINISH = "collect_list_is_finish" ;

        public final static String COLLECT_LIST_TITLE = "collect_list_title" ;

        public final static String CURRENT_SHOW_DATUM = "current_show_datum" ;

        public final static String CURRENT_EDIT_DATUM = "current_edit_datum" ;

        public final static String FILE_PATH = "file_path" ;

        public final static String URI = "uri" ;

        public final static String UN_UPLOAD_COUNT = "un_upload_count" ;

    }
    public static class Message{
        public static final int UPLOAD_IMAGE_ING = 0x009903;
        public static final int OPEN = 0x009904;
        public static final int CLOSE = 0x009905;
    }


    /**
     * 手机客户端版本号
     *
     * @param version
     */
    public static void setVersion(String version) {
        Const.version = version;
    }
    /**
     * 手机客户端版本号
     *
     */
    public static String getVersion() {
        return version;
    }

    private static String channelId = "";

    /***
     * 版本渠道号
     * @param channelId
     */
    public static void setChannelId(String channelId) {
        Const.channelId = channelId;
    }
    /***
     * 版本渠道号
     */
    public static String getChannelId() {
        return channelId;
    }

    private static String serVersion = "1.0.0";

    /**
     * 服务器接口版本号 默认为1.0
     * @param serVersion
     */
    public static void setSerVersion(String serVersion) {
        Const.serVersion = serVersion;
    }

    /**
     * 服务器接口版本号 默认为1.0
     */
    public static String getSerVersion() {
        return serVersion;
    }


    private static String screenWidth = "720";

    /**
     * 屏幕的宽
     * @return
     */
    public static String getScreenWidth() {
        return screenWidth;
    }

    /***
     * 屏幕的宽
     * @param screenWidth
     */
    public static void setScreenWidth(String screenWidth) {
        Const.screenWidth = screenWidth;
    }

    private static String screenHeight = "1080";

    /**
     * 返回屏幕的高
     * @return
     */
    public static String getScreenHeight() {
        return screenHeight;
    }

    /**
     * 屏幕的高
     * @param screenHeight
     */
    public static void setScreenHeight(String screenHeight) {
        Const.screenHeight = screenHeight;
    }


    /**
     * 应用的目录
     */
    public static final class Path {

        public static final String APP_DIR = Environment.getExternalStorageDirectory() + "/RiskPatrol/";

        public static final String IMAGE_DIR = APP_DIR + "image/";

        public static final String CAMERA = IMAGE_DIR + "Camera/";

        public static final String LOG_DIR = APP_DIR + "Log/";

        public static final String CACHE_DIR = APP_DIR + "cache/";

        public static final String CAMERA_TEMP = "temp/";

        public static final String FILE_LOCAL = APP_DIR + "local/";

        public static final String RECORD_LOCAL = APP_DIR + "record/";

        public static final String FILE_SYNCHRONIZATION = APP_DIR + "synchronization/";

    }

    /**
     * 接口名称
     */
    public static class InterfaceName {
        public static final String LOGIN = "Android/Login?" ;//登录
        public static final String GET_NORMAL_CHECK_LIST = "Android/GetNormalCheckList?" ;//获取日月周的未完成表单
        public static final String GET_SITE_USERS = "Android/GetSiteUsers?" ;//获取受检责任人数据
        public static final String GET_SITE_DATAS = "Android/GetSiteDatas?" ;//获取详细工点数据
        public static final String GET_RISK_ELEMENT_DATAS = "Android/GetRiskElementDatas?" ;//获取风险因素
        public static final String SUBMIT_CHECK_LIST = "Android/UploadNormalCheckList?" ;//获取风险因素
        public static final String GET_MY_SUMMARY = "Android/GetMySummary?" ;//获取汇总信息
        public static final String GET_DETAIL = "Android/GetDetail?" ;//获取汇总详细信息
        public static final String UPLOAD_ATTACH = "File/Upload/" ;
    }

    public static class CollectType{
        public static final int DAY = 0 ;
        public static final int WEEK = 1 ;
        public static final int MONTH = 2 ;
        public static final int DEVOTE = 3 ;
        public static final int GROUP = 4 ;
        public static final int MONTH_COLLECT = 5 ;
    }

    public static class ActivityResult{
    }

    public static class SharedPreferencesParam{

        public static final String COMMON_SETUP = "common_setup" ;
    }


    public static class ErrorCode{
        public static int PERMISSION = 10000 ;//无权限
        public static int METHOD_NONEXISTENT = 10001 ;//方法不存在
        public static int METHOD_CLOSE = 10002 ;//方法关闭
        public static int CLASS_NONEXISTENT = 10003 ;//类不存在
        public static int INFO_NONEXISTENT = 10004 ;//信息不存在
        public static int OTHER_ERROR = 10005 ;//其他内部错误

        //---------------------华丽的分割线 下边是客户端错误-----------------------------------
        public static int JSON_EXCEPTION = 10006 ;//JSON异常
        public static int TIME_OUT_EXCEPTION = 10007 ;//JSON异常
    }

}
