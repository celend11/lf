package com.android.sunning.riskpatrol.net.parse;

import org.json.JSONObject;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.HomeEntity;
import com.android.sunning.riskpatrol.util.JSONUtils;
import com.lidroid.xutils.util.LogUtils;


/**
 * Created by sunning on 15/2/17.
 */
public class MainParse implements ParseInfo{
    @Override
    public BaseEntity parseJSON(JSONObject jsonObject) {
        HomeEntity homeEntity = JSONUtils.fromJson(jsonObject.toString(),HomeEntity.class) ;
        LogUtils.e(homeEntity.toString()+"======");
        return homeEntity ;
    }
}
