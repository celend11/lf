package com.android.sunning.riskpatrol.net.parse;

import org.json.JSONObject;

import com.android.sunning.riskpatrol.entity.BaseEntity;

/**
 * Created by sunning on 14-8-4.
 */
public interface ParseInfo {
    public BaseEntity parseJSON(JSONObject jsonObject) ;
}
