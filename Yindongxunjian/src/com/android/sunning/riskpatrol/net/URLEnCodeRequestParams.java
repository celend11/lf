package com.android.sunning.riskpatrol.net;

import android.text.TextUtils;
import com.lidroid.xutils.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by sunning on 14-8-1.
 */
public class URLEnCodeRequestParams extends RequestParams{

    public String interfaceName ;

    public URLEnCodeRequestParams() {
        super("UTF-8");
    }

    @Override
    public void addQueryStringParameter(String name, String value) {
        if(!name.equals("sign")&&!TextUtils.isEmpty(value)){
            try {
                value = URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        super.addQueryStringParameter(name, value);
    }


}
