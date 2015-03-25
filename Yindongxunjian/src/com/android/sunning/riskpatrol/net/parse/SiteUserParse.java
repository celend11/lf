package com.android.sunning.riskpatrol.net.parse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.E_AcceptCheckLeader;
import com.android.sunning.riskpatrol.entity.E_FuZeRen;

/**
 * Created by sunning on 15/2/25.
 */
public class SiteUserParse implements ParseInfo{

    @Override
    public BaseEntity parseJSON(JSONObject jsonObject) {
        E_FuZeRen E_fuZeRen = new E_FuZeRen() ;
        E_AcceptCheckLeader fuZeRen ;
        JSONArray jsonArray = jsonObject.optJSONArray("Data") ;
        if(jsonArray != null){
            int length = jsonArray.length() ;
            for(int i = 0 ; i< length ; i++ ){
                /**
                 * "UserID": "7a39c421-656f-460b-ab9d-63b56d10b3e6",
                 "RealName": "刘克伟",
                 "PosName": "指挥长"
                 */
                JSONObject innerJson = jsonArray.optJSONObject(i) ;
                fuZeRen = new E_AcceptCheckLeader() ;
                fuZeRen.leaderUnit = innerJson.optString("PosName") ;
                fuZeRen.leaderID = innerJson.optString("UserID") ;
                fuZeRen.leaderName = innerJson.optString("RealName") ;
                E_fuZeRen.fuZeRenList.add(fuZeRen) ;
            }
        }
        return E_fuZeRen;
    }
}
