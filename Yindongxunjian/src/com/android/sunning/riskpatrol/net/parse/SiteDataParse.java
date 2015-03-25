package com.android.sunning.riskpatrol.net.parse;

import org.json.JSONObject;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.Areas;
import com.android.sunning.riskpatrol.util.JSONUtils;

/**
 * Created by sunning on 15/2/25.
 */
public class SiteDataParse implements ParseInfo {
    @Override
    public BaseEntity parseJSON(JSONObject jsonObject) {
//        E_Area areaParent = new E_Area();
//        areaParent.AID = jsonObject.optString("ID");
//        areaParent.updateTime = jsonObject.optString("UpdateTime");
//        List<E_AcceptCheckArea> areaList = null;
//        E_AcceptCheckArea acceptCheckArea ;
//        JSONArray areaListArray = jsonObject.optJSONArray("Areas");
//        if (areaListArray != null) {
//            int length = areaListArray.length();
//            if(length > 0){
//                areaList = new ArrayList<E_AcceptCheckArea>() ;
//                for (int i = 0; i < length; i++) {
//                    JSONObject innerJSON = areaListArray.optJSONObject(i) ;
//                    acceptCheckArea = new E_AcceptCheckArea() ;
//                    acceptCheckArea.areaID = innerJSON.optString("AreaID") ;
//                    acceptCheckArea.areaName = innerJSON.optString("AreaName") ;
//                    JSONArray innerArray = innerJSON.optJSONArray("PatrolItems") ;
//                    if(innerArray != null){
//                        int size = innerArray.length();
//                        List<E_AreaPatrolItems> patrolList = null;
//                        if(size > 0){
//                            patrolList = new ArrayList<E_AreaPatrolItems>() ;
//                            E_AreaPatrolItems patrolItem ;
//                            for (int j = 0; j < size; j++) {
//                                JSONObject patrolJSON = innerArray.optJSONObject(j) ;
//                                patrolItem = new E_AreaPatrolItems() ;
//                                patrolItem.canDelete = patrolJSON.optBoolean("CanDelete") ;
//                                patrolItem.itemId = patrolJSON.optString("ItemID") ;
//                                patrolItem.fengBu = patrolJSON.optString("FengBu") ;
//                                patrolItem.itemName = patrolJSON.optString("ItemName") ;
//                                patrolItem.patrolStatus = patrolJSON.optInt("PatrolStatus") ;
//                                //todo 还没加附件的解析。
////                                patrolItem.attachments = patrolJSON.optString("Attachements") ;
//                                //todo 还没加风险因素的解析。
////                                patrolItem.problemItems = patrolJSON.optString("ProblemItems") ;
//                                patrolList.add(patrolItem) ;
//                            }
//                            acceptCheckArea.patrolItemsList = patrolList ;
//                        }
//                    }
//                    areaList.add(acceptCheckArea) ;
//                }
//                areaParent.areaList = areaList ;
//            }
//        }
        Areas area = JSONUtils.fromJson(jsonObject.toString(),Areas.class) ;
//        LogUtils.e("======"+area);
        return area;
    }
}
