package com.android.sunning.riskpatrol.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.android.sunning.riskpatrol.Const;

/**
 * Created by sunning on 14-8-3.
 */
public abstract class RequestInfo {

    protected String ApiName ;

    protected Interaction interaction;

    protected URLEnCodeRequestParams requestParams ;


    public static String encoder(String content,String charsetName){
        try {
            return URLEncoder.encode(content, charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String encoder(String content){
        return encoder(content,"UTF-8");
    }

    public RequestInfo(String apiName, Interaction interaction) {
        ApiName = apiName;
        this.interaction = interaction;
        requestParams = new URLEnCodeRequestParams() ;
        requestParams.interfaceName = apiName ;
    }

    protected void createApiNameParams(){
        requestParams.addQueryStringParameter("method",ApiName);
    }

    public void execute(){
        params() ;
        if(Const.REQUEST_TYPE_IS_POST){
            interaction.request(requestParams);
        }else{
            interaction.requestByGet(requestParams);
        }
    }

    public void executePost(){
        params() ;
        interaction.request(requestParams);
    }

    public void upload(){
        params() ;
        interaction.upload(requestParams) ;
    }

    private void params(){
//        createApiNameParams();
        addParams() ;
//        addPublicParams() ;
    }

    protected abstract void addParams() ;

    protected void addPublicParams() {
        requestParams.addQueryStringParameter("app_id", Const.APP_ID) ;
        requestParams.addQueryStringParameter("time", String.valueOf(System.currentTimeMillis())) ;
    }

}
