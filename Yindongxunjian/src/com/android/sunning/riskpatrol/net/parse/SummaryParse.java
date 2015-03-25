package com.android.sunning.riskpatrol.net.parse;

import org.json.JSONObject;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.summary.MySummary;
import com.android.sunning.riskpatrol.util.JSONUtils;

/**
 * Created by sunning on 15/3/3.
 */
public class SummaryParse implements ParseInfo{

    @Override
    public BaseEntity parseJSON(JSONObject jsonObject) {
        MySummary mySummary = JSONUtils.fromJson(jsonObject.toString(),MySummary.class) ;
        return mySummary;
    }

}
