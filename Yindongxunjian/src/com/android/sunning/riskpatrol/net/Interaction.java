package com.android.sunning.riskpatrol.net;


import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.exception.BException;

/**
 * Created by sunning on 14-9-28.
 * 抽象Http请求与响应
 * 抽象交互的行为
 */
public interface Interaction {

    public void request(URLEnCodeRequestParams requestParams) ;

    public void requestByGet(URLEnCodeRequestParams requestParams) ;

    public void response(BaseEntity entity) ;

    public void success(BaseEntity baseEntity) ;

    public void fail(BException exception) ;

    public void upload(URLEnCodeRequestParams requestParams) ;

    public void cancel() ;

}
