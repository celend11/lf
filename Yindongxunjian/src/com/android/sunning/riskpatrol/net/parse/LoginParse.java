package com.android.sunning.riskpatrol.net.parse;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.android.sunning.riskpatrol.entity.generate.login.Login;
import com.android.sunning.riskpatrol.entity.generate.login.Site;

/**
 * Created by sunning on 15/2/16.
 */
public class LoginParse implements ParseInfo{

    @Override
    public BaseEntity parseJSON(JSONObject jsonObject) {
        Login login = new Login() ;
        List<Site> sitesList ;
        login.setID(jsonObject.optString("ID")) ;
        login.setPWD(jsonObject.optString("PWD")) ;
        login.setName(jsonObject.optString("Name")) ;
        JSONArray sitesArray = jsonObject.optJSONArray("Sites") ;
        if(sitesArray != null){
            int length = sitesArray.length() ;
            Site sites ;
            sitesList = new ArrayList<Site>();
            for(int i = 0 ; i < length ; i++){
                JSONObject sitesJSON = sitesArray.optJSONObject(i) ;
                sites = new Site() ;
                sites.setCompanyName(sitesJSON.optString("CompanyName")) ;
                sites.setID(sitesJSON.optString("ID")) ;
                sites.setName(sitesJSON.optString("Name"))  ;
                sites.setStatus(sitesJSON.optInt("Status")) ;
                sitesList.add(sites) ;
            }
            login.setSites(sitesList);
        }
        return login;
    }

}
