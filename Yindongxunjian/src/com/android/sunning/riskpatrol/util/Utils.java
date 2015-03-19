package com.android.sunning.riskpatrol.util;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import com.android.sunning.riskpatrol.Const;
import com.lidroid.xutils.BitmapUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sunning on 14-9-28.
 */
public class Utils {

    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }


    public static int[] getScreenHeightAndWidth(Activity context) {
        int[] size = new int[2];
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int W = dm.widthPixels;
        int H = dm.heightPixels;
        size[0] = W;
        size[1] = H;
        return size;
    }

    /**
     * 根据key从SharedPreferences取对应的value
     *
     * @param key
     * @param context
     */
    public static String getPara(String key, Context context) {
        SharedPreferences settings = context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
        String value = settings.getString(key, "");
        return value;
    }

    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    public static String getVersionName(Context context) {
        try {
            String pkName = context.getPackageName();
            return context.getPackageManager().getPackageInfo(pkName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getTrafficData(long total) {
        String dataText = "0";
        DecimalFormat df = new DecimalFormat("###.00");
        if (total == -1) {
            return dataText;
        } else if (total < 1024) {
            dataText += total + "b";
        } else if (total < 1024 * 1024) {
            dataText = df.format(total / 1024f) + "k";
        } else if (total < 1024 * 1024 * 1024) {
            dataText = df.format(total / 1024f / 1024f) + "m";
        } else if (total < 1024 * 1024 * 1024 * 1024) {
            dataText = df.format(total / 1024f / 1024f / 1024f) + "g";
        }
        return dataText;
    }

    public static Bitmap setScreenImg(ImageView img, String path) {
        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
        if (bitmap != null) {
            int width = img.getWidth();
            int height = img.getHeight();

            bitmap = Bitmap.createScaledBitmap(bitmap, width > 0 ? width : 720, height > 0 ? height : 360, false);
            img.setImageBitmap(bitmap);
        }
        return bitmap;
    }

    public static Bitmap getScreenImg(String path) {
        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
        return bitmap;
    }


    public static void loadImg(Context context, View view, String path) {
        new BitmapUtils(context).display(view, path);
    }

    private static String mapToQueryString(HashMap<Object, Object> params) {
        Object[] array = params.keySet().toArray();

        Arrays.sort(array);
        String str = "";
        for (int i = 0; i < array.length; i++) {
            String key = array[i].toString();
            try {
                if (i != array.length - 1) {

                    str += key + "=" + URLEncoder.encode((String) params.get(key), "UTF-8") + "&";

                } else {
                    str += key + "=" + URLEncoder.encode((String) params.get(key), "UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String getHDImage(String imagePath) {
        if (imagePath == null || !imagePath.contains("."))
            return "";
        int position = imagePath.lastIndexOf(".");
        String suffix = imagePath.substring(position);
        String path = imagePath.substring(0, position);
        String hd = path + "_640_360";
        return hd + suffix;
    }

    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }

    public static void sendSMS(Context context, String smsBody) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("sms_body", smsBody);
        context.startActivity(intent);
    }

    /**
     * 计算两个日期型的时间相差多少时间
     *
     * @param millisecond 需要计算的时间
     * @return
     */
    public static String formatDate(long millisecond) {
//        Date startTime = new Date(timeStamp2Date(millisecond));
//        Date nowDate = Calendar.getInstance().getTime();
//        if (startTime == null || nowDate == null) {
//            return null;
//        }
//        long timeLong = nowDate.getTime() - timeStamp2Date(millisecond);
//        timeLong = Math.abs(timeLong) ;
//        if (timeLong < 60 * 1000)
//            return timeLong / 1000 + "秒前";
//        else if (timeLong < 60 * 60 * 1000) {
//            timeLong = timeLong / 1000 / 60;
//            return timeLong + "分钟前";
//        } else if (timeLong < 60 * 60 * 24 * 1000) {
//            timeLong = timeLong / 60 / 60 / 1000;
//            return timeLong + "小时前";
//        } else if (timeLong < 60 * 60 * 24 * 1000 * 7) {
//            timeLong = timeLong / 1000 / 60 / 60 / 24;
//            return timeLong + "天前";
//        } else if (timeLong < 60 * 60 * 24 * 1000 * 7 * 4) {
//            timeLong = timeLong / 1000 / 60 / 60 / 24 / 7;
//            return timeLong + "周前";
//        } else {
//            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
//            return sdf.format(startTime);
//        }

        Date startTime = new Date(timeStamp2Date(millisecond));
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
        return sdf.format(startTime);
    }

    public static String formatDateYYMMDD(long millisecond) {
        Date startTime = new Date(timeStamp2Date(millisecond));
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
        return sdf.format(startTime);
    }

    public static String formatDateAtMMDD(long millisecond) {
        Date startTime = new Date(timeStamp2Date(millisecond));
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(startTime);
    }

    public static long timeStamp2Date(long timestampString) {
        Long timestamp = timestampString * 1000;
        return timestamp;
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    /**
     * 网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 验证手机号码
     *
     * @param str
     * @return
     */
    public static boolean checkUserMobile(String str) {
        Pattern p = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 验证是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57)
                return false;
        }
        return true;
    }

    public static String getName(String... names) {
        if (names == null) {
            return "";
        }
        for (int i = 0; i < names.length; i++) {
            if (!TextUtils.isEmpty(names[i])) {
                return names[i];
            }
        }
        return "";
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static String getFileName(String path) {
        if (!TextUtils.isEmpty(path)) {
            int position = path.lastIndexOf("/") + 1;
            return path.subSequence(position, path.length()).toString();
        }
        return "";
    }

    public void openOrCloseInput(Context context) {
        InputMethodManager m = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hideKeyBoard(Activity context) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showKeyBoard(Activity context) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInputFromInputMethod(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static String combinationParamsWithCache(String uri, String timestamp) {
        if (!TextUtils.isEmpty(uri)) {
            return uri + "?" + "timestamp=" + timestamp;
        }
        return "";
    }

    public static String getLocalIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        if (ipAddress == 0) return null;
        return ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "."
                + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));
    }

    public static void readHtmlFormAssets(WebView webView, String path) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.loadUrl(path);
    }

    /**
     * 获取网络类型
     *
     * @return cityName
     */
    static String getNetWorkType(Context context) {
        String type = "wifi";
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);// 获取系统的连接服务
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();// 获取网络的连接情况
        if (activeNetInfo == null) {
            type = "未开启网络";
            return type;
        }
        if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            type = "2g/3g";
            int typeCode = activeNetInfo.getSubtype();
            switch (typeCode) {
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    type = "移动3G";
                    break;
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    type = "联通3G";
                    break;
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    type = "联通2G";
                    break;
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    type = "移动2G";
                    break;
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    type = "电信2G";
                    break;
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    type = "电信3G";
                    break;

            }
        }
        return type;
    }

    public static boolean isRadioRootActivity(String clas) {
        for (String item : Const.bottomMenuRootClass) {
            if (item.equals(clas)) {
                return true;
            }
        }
        return false;
    }

    public static boolean destroy(String id, LocalActivityManager activityManager) {
        if (activityManager != null) {
            activityManager.destroyActivity(id, true);
            try {
                final Field mActivitiesField = LocalActivityManager.class.getDeclaredField("mActivities");
                if (mActivitiesField != null) {
                    mActivitiesField.setAccessible(true);
                    @SuppressWarnings("unchecked")
                    final Map<String, Object> mActivities = (Map<String, Object>) mActivitiesField.get(activityManager);
                    if (mActivities != null) {
                        mActivities.remove(id);
                    }
                    final Field mActivityArrayField = LocalActivityManager.class.getDeclaredField("mActivityArray");
                    if (mActivityArrayField != null) {
                        mActivityArrayField.setAccessible(true);
                        @SuppressWarnings("unchecked")
                        final ArrayList<Object> mActivityArray = (ArrayList<Object>) mActivityArrayField.get(activityManager);
                        if (mActivityArray != null) {
                            for (Object record : mActivityArray) {
                                final Field idField = record.getClass().getDeclaredField("id");
                                if (idField != null) {
                                    idField.setAccessible(true);
                                    final String _id = (String) idField.get(record);
                                    if (id.equals(_id)) {
                                        mActivityArray.remove(record);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
