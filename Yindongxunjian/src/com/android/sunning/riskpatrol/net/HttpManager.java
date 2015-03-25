package com.android.sunning.riskpatrol.net;

import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Message;
import android.text.TextUtils;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.exception.BException;
import com.android.sunning.riskpatrol.system.HandlerManager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;


/**
 * Created by sunning on 14-09-28.
 * 真实实现请求和响应的相关代码
 */
public final class HttpManager {
    public static HttpHandler httpHandler ;
    public static final int SET_CONNECTION_TIMEOUT = 30 * 1000;
    private static final HttpUtils HTTP_UTILS = new HttpUtils(SET_CONNECTION_TIMEOUT) ;

    static{
        HTTP_UTILS.configResponseTextCharset("UTF-8") ;
    }
    /**
     * 发送请求
     * @param params
     * @param handler
     */
    public static void request(final RequestParams params , final HandlerManager.SunnyHandler handler , String apiName) {
        HTTP_UTILS.send(HttpRequest.HttpMethod.POST, Const.URL + apiName, params, new RequestCallBack<String>() {
            Message msg = handler.obtainMessage() ;
            @Override
            public void onSuccess(ResponseInfo<String> stringResponseInfo) {
                LogUtils.e(stringResponseInfo.result) ;
                msg.what = Const.SUCCESS ;
                msg.obj = stringResponseInfo ;
                handler.sendMessage(msg) ;
            }
            @Override
            public void onFailure(HttpException httpException, String arg1) {
                LogUtils.e("onFailure"+httpException.toString()) ;
                msg.what = Const.FAIL ;
                BException sunnyException = new BException(httpException.getMessage() , httpException.getExceptionCode()) ;
                msg.obj = sunnyException ;
                handler.sendMessage(msg) ;
            }
        }) ;
    }

    /**
     * 发送请求
     * @param params
     * @param handler
     */
    public static void requestByGet(final RequestParams params , final HandlerManager.SunnyHandler handler , String apiName) {
        if(params != null){
            sing(params) ;
        }
        String reqParams = getParams(params , apiName) ;
        LogUtils.e(reqParams+"") ;
        HTTP_UTILS.send(HttpRequest.HttpMethod.GET, reqParams, null , new RequestCallBack<String>() {
            Message msg = handler.obtainMessage() ;
            @Override
            public void onSuccess(ResponseInfo<String> stringResponseInfo) {
                LogUtils.e(stringResponseInfo.result) ;
                msg.what = Const.SUCCESS ;
                msg.obj = stringResponseInfo ;
                handler.sendMessage(msg) ;
            }
            @Override
            public void onFailure(HttpException httpException, String arg1) {
                msg.what = Const.FAIL ;
                BException sunnyException = new BException(httpException.getMessage() , httpException.getExceptionCode()) ;
                msg.obj = sunnyException ;
                handler.sendMessage(msg) ;
            }
        }) ;
    }

    private static void sing(RequestParams params) {
//        String sign = Sign.signature(params) ;
//        params.addQueryStringParameter("sign",sign) ;
    }

    /**
     * 图片上传
     * @param params
     */
    public static void imgUpload(RequestParams params , final HandlerManager.SunnyHandler handler ) {
        if(params != null){
            sing(params) ;
        }
        LogUtils.e(params+"@@@@") ;
//        String reqParams = getParams(params , Const.InterfaceName.CHANGE_HEADER_IMG) ;
        httpHandler =  HTTP_UTILS.send(HttpRequest.HttpMethod.POST , Const.URL + Const.InterfaceName.UPLOAD_ATTACH , params , new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {
                Message msg = handler.obtainMessage() ;
                if(objectResponseInfo != null && objectResponseInfo.result != null){
                    try {
                        JSONObject json = new JSONObject(objectResponseInfo.result.toString()) ;
                        boolean isSuccess = json.optBoolean("success",false) ;
                        msg.obj = objectResponseInfo.result.toString() ;
                        if(isSuccess) {
                            msg.what = Const.UPLOAD_SUCCESS ;
                        }else{
                            msg.what = Const.UPLOAD_FAIL ;
                        }
                        LogUtils.e("msg.obj"+msg.obj+"=======") ;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(msg) ;
                }
                LogUtils.e("onSuccess"+objectResponseInfo.reasonPhrase.toString());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                BException exception = new BException(s,e.getExceptionCode()) ;
                handler.sendMessage(handler.obtainMessage(Const.UPLOAD_FAIL,0,0,exception)) ;
                LogUtils.e("上传失败了，但是我也不知道因为神马～！～！～！～onFailure"+s +"======="+e.toString()) ;
            }

            @Override
            public void onStart() {
                LogUtils.e("上传开始..................") ;
                super.onStart() ;
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                LogUtils.e("上传中。。..................") ;
                if(current <= total && total != -1 && isUploading){
                    handler.sendEmptyMessage(Const.Message.UPLOAD_IMAGE_ING) ;
                    LogUtils.e("total"+total+"current" +current );
                }
            }
        }) ;
    }

    /**
     * 根据Map中参数拼凑字符串
     * @param params 参数Map
     * @return String URL
     */
    private static String getParams(RequestParams params , String apiName){
        StringBuffer sBuffer = new StringBuffer(Const.URL) ;
        if(!TextUtils.isEmpty(apiName)){
            sBuffer.append(apiName) ;
        }

        List<NameValuePair> list = params.getQueryStringParams() ;
        for(NameValuePair paramsNV : list){
            sBuffer.append(paramsNV.getName()).append("=").append(paramsNV.getValue()).append("&") ;

        }
        if(sBuffer.toString().lastIndexOf("&") == sBuffer.length()-1){
            sBuffer.deleteCharAt(sBuffer.length() - 1) ;
        }
        return sBuffer.toString() ;
    }

    private static void sendMessage(int what , Object data , HandlerManager.SunnyHandler handler){
        Message msg = handler.obtainMessage() ;
        msg.what = what ;
        msg.obj = data ;
        handler.sendMessage(msg) ;
    }
}