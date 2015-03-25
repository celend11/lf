package com.android.sunning.riskpatrol.net.parse;

import org.json.JSONObject;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.util.JSONUtils;

/**
 * Created by sunning on 15/3/3.
 */
public class DetailParse implements ParseInfo{
    @Override
    public BaseEntity parseJSON(JSONObject jsonObject) {
        Datum datum = JSONUtils.fromJson(jsonObject.toString(),Datum.class) ;
        return datum;
    }
}
