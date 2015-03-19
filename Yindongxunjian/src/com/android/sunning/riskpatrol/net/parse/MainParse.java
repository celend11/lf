package com.android.sunning.riskpatrol.net.parse;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.Area;
import com.android.sunning.riskpatrol.entity.generate.Company;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.entity.generate.HomeEntity;
import com.android.sunning.riskpatrol.entity.generate.JianChaFuZeRenC;
import com.android.sunning.riskpatrol.entity.generate.PatrolItem;
import com.android.sunning.riskpatrol.entity.generate.ShouJianFuZeRen;
import com.android.sunning.riskpatrol.entity.generate.Site;
import com.android.sunning.riskpatrol.util.JSONUtils;
import com.google.gson.JsonArray;

/**
 * Created by sunning on 15/2/17.
 */
public class MainParse implements ParseInfo{
    @Override
    public BaseEntity parseJSON(JSONObject jsonObject) {
//        HomeEntity homeEntity = JSONUtils.fromJson(jsonObject.toString(),HomeEntity.class) ;
//        LogUtils.e(homeEntity.toString()+"======");
          HomeEntity homeEntity=new HomeEntity();
          JSONArray datasArray=jsonObject.optJSONArray("Data");
          if(datasArray != null){
              int length = datasArray.length() ;
              List<Datum> datalist = new ArrayList<Datum>();
              for(int i = 0 ; i < length ; i++){
                  JSONObject datumJSON = datasArray.optJSONObject(i) ;
                  Datum datum = new Datum() ;
                  datum.setNormalID(datumJSON.optString("NormalID"));
                  JianChaFuZeRenC jianchafuzerenc=new JianChaFuZeRenC();
                  JSONObject zerencJSON=datumJSON.optJSONObject("JianChaFuZeRenC");
                  jianchafuzerenc.setJianChaFuZeRenID(zerencJSON.optString("JianChaFuZeRenID"));
                  jianchafuzerenc.setJianChaFuZeRen(zerencJSON.optString("JianChaFuZeRen"));
                  datum.setJianChaFuZeRenC(jianchafuzerenc);
                  datum.setInspectType(datumJSON.optInt("InspectType"));
                  datum.setInspectWay(datumJSON.optInt("InspectWay"));
                  datum.setInspectWayTxt(datumJSON.optString("InspectWayTxt"));
                  JSONObject sitesJSON=datumJSON.optJSONObject("Site");
                  Site site=new Site();
                  site.setSiteID(sitesJSON.optString("SiteID"));
                  site.setSiteName(sitesJSON.optString("SiteName"));
                  datum.setSite(site);
                  JSONObject JanChaXiangMuJSON=datumJSON.optJSONObject("JianChaXiangMu");
                  JSONArray AreasArray=JanChaXiangMuJSON.optJSONArray("Areas");
                  int lenth2=AreasArray.length();
                  List<Area> areas=new ArrayList<Area>();
                  for(int j=0;j<lenth2;j++){
                	 Area area=new Area();
                	 JSONObject areaJSON=AreasArray.optJSONObject(j);
                	 area.setAreaID(areaJSON.optString("AreaID"));
                	 area.setAreaName(areaJSON.optString("AreaName"));
                	 JSONArray PatrolItemsArray=areaJSON.optJSONArray("PatrolItems");
                	 int lenth3=PatrolItemsArray.length();
                	 List<PatrolItem> patrolItems=new ArrayList<PatrolItem>();
                	 for(int z=0;z<lenth3;z++){
                		 PatrolItem patrolItem=new PatrolItem();
                		 JSONObject patrolItemJSON=PatrolItemsArray.optJSONObject(z);
                		 patrolItem.setItemID(patrolItemJSON.optString("ItemID"));
                		 patrolItem.setFengBu(patrolItemJSON.optString("FengBu"));
                		 patrolItem.setItemName(patrolItemJSON.optString("ItemName"));
                		 patrolItem.setCanDelete(patrolItemJSON.optBoolean("ItemName"));
                		 patrolItems.add(patrolItem);
                	 }
                	 area.setPatrolItems(patrolItems);
                	 areas.add(area);
                  }
                  datum.setInspectNum(datumJSON.optString("InspectNum"));
                  JSONObject CompanyJSON=datumJSON.optJSONObject("Company");
                  Company company=new Company();
                  company.setCompanyID(CompanyJSON.optString("CompanyID"));
                  company.setCompanyName(CompanyJSON.optString("CompanyName"));
                  datum.setCompany(company);
                  datum.setInspectTime(datumJSON.optString("InspectTime"));
                  datum.setJianChaFuZeRen(datumJSON.optString("JianChaFuZeRen"));
                  JSONObject ShouJianFuZeRenJSON=datumJSON.optJSONObject("ShouJianFuZeRen");
                  ShouJianFuZeRen shouJianFuZeRen=new ShouJianFuZeRen();
                  shouJianFuZeRen.setShouJianFuZeRenID(ShouJianFuZeRenJSON.optString("ShouJianFuZeRenID"));
                  shouJianFuZeRen.setShouJianFuZeRenName(ShouJianFuZeRenJSON.optString("ShouJianFuZeRenName"));
                  datum.setShouJianFuZeRen(shouJianFuZeRen);
                  datum.setCanJiaRenYuanInput(datumJSON.optString("CanJiaRenYuanInput"));
                  datum.setBeiZhu(datumJSON.optString("BeiZhu"));
                  datum.setCreateTime(datumJSON.optString("CreateTime"));
                  datum.setCreatorID(datumJSON.optString("CreatorID"));
                  datum.setCreatorName(datumJSON.optString("CreatorName"));
                  datum.setStatus(datumJSON.optInt("Status"));
                  datum.setCheckListStatusTxt(datumJSON.optString("CheckListStatusTxt"));
                  datalist.add(datum);
              }
              homeEntity.setData(datalist);
          }
        return homeEntity ;
    } 
}
