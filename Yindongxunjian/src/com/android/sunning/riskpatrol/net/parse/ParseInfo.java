package com.android.sunning.riskpatrol.net.parse;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import org.json.JSONObject;

/**
 * Created by sunning on 14-8-4.
 */
public interface ParseInfo {
    public BaseEntity parseJSON(JSONObject jsonObject) ;
}
