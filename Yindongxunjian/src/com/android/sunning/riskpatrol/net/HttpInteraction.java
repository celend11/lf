package com.android.sunning.riskpatrol.net;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Message;
import android.text.TextUtils;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.exception.BException;
import com.android.sunning.riskpatrol.net.parse.ChooseParse;
import com.android.sunning.riskpatrol.system.HandlerCallBackListener;
import com.android.sunning.riskpatrol.system.HandlerManager;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by sunning on 14-9-28.
 * 虚实现请求与响应
 */
public abstract class HttpInteraction implements Interaction, HandlerCallBackListener {

    protected HandlerManager.SunnyHandler handler;
    private String apiName ;

    public HttpInteraction() {
        handler = HandlerManager.getInstance().getHandler(this) ;
    }

    @Override
    public void request(URLEnCodeRequestParams requestParams) {
        apiName = requestParams.interfaceName ;
        HttpManager.request(requestParams, handler , apiName) ;
    }

    @Override
    public void requestByGet(URLEnCodeRequestParams requestParams) {
        apiName = requestParams.interfaceName ;
        HttpManager.requestByGet(requestParams,handler ,apiName) ;
    }


    @Override
    public void upload(URLEnCodeRequestParams requestParams) {
        apiName = requestParams.interfaceName ;
        HttpManager.imgUpload(requestParams, handler) ;
    }

    @Override
    public void cancel() {
        HttpManager.httpHandler.stop();
        HttpManager.httpHandler.cancel(true);
        if(!HttpManager.httpHandler.isStopped()) {
            HttpManager.httpHandler.stop();
            HttpManager.httpHandler.cancel(true);
        }
    }

    @Override
    public void success(BaseEntity baseEntity) {

    }

    @Override
    public void fail(BException exception) {

    }

    @Override
    public void obtainMsg(Message msg) {
        if(msg.what == Const.SUCCESS){
            ResponseInfo<String> responseInfo =  (ResponseInfo)msg.obj ;
            setDataToResponse(responseInfo);
        }else if(msg.what == Const.FAIL){
            BException exception = (BException) msg.obj;
            fail(exception) ;
        }else if(msg.what == Const.UPLOAD_SUCCESS){
            String responseInfo =  (String)msg.obj ;
            setDataToResponse(responseInfo);
            LogUtils.e("上传成功。") ;
        }else if(msg.what == Const.UPLOAD_FAIL){
            BException exception = (BException) msg.obj;
            fail(exception) ;
            LogUtils.e("上传失败。") ;
        }
    }

    private void setDataToResponse(ResponseInfo<String> responseInfo) {
        try {
            ChooseParse parse = new ChooseParse(apiName) ;
            JSONObject rootJSON = new JSONObject(responseInfo.result) ;
            BaseEntity bean = parse.createBeanByJson(rootJSON) ;
            String message = TextUtils.isEmpty(parse.message)?"":parse.message;
            int code = parse.code ;
            if(code == Const.SUCCESS){
                bean.message = message ;
                bean.code = code ;
                if(bean != null){
                    response(bean) ;
                    return ;
                }
            }else if(code==Const.FAIL){
            	  bean.message = message ;
                  bean.code = code ;
                  if(bean != null){
                      response(bean) ;
                      return ;
                  }
            }
            fail(new BException(message,code)) ;
        } catch (JSONException e) {
            e.printStackTrace();
            fail(new BException(e.getMessage(), Const.ErrorCode.JSON_EXCEPTION)) ;
        }
    }
    private void setDataToResponse(String responseInfo) {
        ChooseParse parse = new ChooseParse(apiName) ;
        try {
            JSONObject rootJSON = new JSONObject(responseInfo) ;
            BaseEntity bean = parse.createBeanByJson(rootJSON) ;
            if(bean != null){
                bean.message = TextUtils.isEmpty(parse.message)?"":parse.message ;
                bean.code = parse.code ;
                response(bean) ;
                return ;
            }
//                requestFail(new BException(parse.code,parse.message)) ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}