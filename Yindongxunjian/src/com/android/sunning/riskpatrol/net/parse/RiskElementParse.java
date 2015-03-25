package com.android.sunning.riskpatrol.net.parse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.RiskElement;
import com.android.sunning.riskpatrol.entity.generate.RiskElements;

/**
 * Created by sunning on 15/2/26.
 */
public class RiskElementParse implements ParseInfo{

    @Override
    public BaseEntity parseJSON(JSONObject jsonObject) {
        RiskElements riskElements = new RiskElements() ;
        riskElements.setUpdateTime(jsonObject.optString("UpdateTime")) ;
        RiskElement riskElement ;
        JSONArray element = jsonObject.optJSONArray("RiskElements") ;
        if(element != null){
            int length = element.length() ;
            for(int i = 0 ; i < length ; i++){
                JSONObject inner = element.optJSONObject(i) ;
                riskElement = new RiskElement() ;
                riskElement.setEName(inner.optString("EName")) ;
                riskElement.setFengBu(inner.optString("FengBu")) ;
                riskElement.setFengXiang(inner.optString("FengXiang")) ;
                riskElement.setID(inner.optString("ID")) ;
                riskElements.getRiskElements().add(riskElement) ;
            }
        }
//        RiskElements riskElements = JSONUtils.fromJson(jsonObject.toString(),RiskElements.class) ;
        return riskElements ;
    }

}
