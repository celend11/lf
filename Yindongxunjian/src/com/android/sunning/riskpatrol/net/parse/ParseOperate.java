package com.android.sunning.riskpatrol.net.parse;


import android.text.TextUtils;
import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.entity.BaseEntity;
import org.json.JSONObject;

/**
 * Created by sunning on 14-8-3.
 */
public abstract class ParseOperate {
    public int code ;
    public String message ;

    public BaseEntity createBeanByJson(JSONObject json) {
        code = json.optInt("Status") ;
        if(!TextUtils.isEmpty(json.optString("Error"))){
            message = json.optString("Error") ;
        }else{
            message = "" ;
        }
        if(code == Const.SUCCESS){
            ParseInfo info = parseMethod() ;
            JSONObject root = json.optJSONObject("Data") ;
            if(root == null){
                return info.parseJSON(json) ;
            }
            return info.parseJSON(root) ;
        }else{
            //TODO Error逻辑
        }
        return new BaseEntity() ;
    }

    public abstract ParseInfo parseMethod() ;
}
