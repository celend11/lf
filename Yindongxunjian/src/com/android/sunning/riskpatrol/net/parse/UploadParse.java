package com.android.sunning.riskpatrol.net.parse;

import org.json.JSONObject;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.Upload;

/**
 * Created by sunning on 15/3/3.
 */
public class UploadParse implements ParseInfo{

    @Override
    public BaseEntity parseJSON(JSONObject jsonObject) {
        Upload upload = new Upload() ;
        upload.setFileKey(jsonObject.optString("fileKey"))  ;
        upload.setFileName(jsonObject.optString("fileName"))  ;
        upload.setSize(jsonObject.optInt("size")) ;
        upload.aId = jsonObject.optString("id") ;
        upload.setUrl(jsonObject.optString("url")) ;
        upload.setSuccess(jsonObject.optBoolean("success")) ;
        return upload;
    }
}
