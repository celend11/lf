package com.android.sunning.riskpatrol.net.parse;


import com.android.sunning.riskpatrol.Const;

/**
 * Created by sunning on 14-10-14.
 */
public class ChooseParse extends ParseOperate{

    private String apiName ;

    public ChooseParse(String apiName) {
        this.apiName = apiName;
    }

    @Override
    public ParseInfo parseMethod() {
        ParseInfo parseInfo = null ;
        if(apiName.equals(Const.InterfaceName.LOGIN)){
            parseInfo = new LoginParse() ;
        }else if(apiName.equals(Const.InterfaceName.GET_NORMAL_CHECK_LIST)){
            parseInfo = new MainParse() ;
        }else if(apiName.equals(Const.InterfaceName.GET_SITE_USERS)){
            parseInfo = new SiteUserParse() ;
        }else if(apiName.equals(Const.InterfaceName.GET_SITE_DATAS)){
            parseInfo = new SiteDataParse() ;
        }else if(apiName.equals(Const.InterfaceName.GET_MY_SUMMARY)){
            parseInfo = new SummaryParse() ;
        }else if(apiName.equals(Const.InterfaceName.GET_DETAIL)){
            parseInfo = new DetailParse() ;
        }else if(apiName.equals(Const.InterfaceName.GET_RISK_ELEMENT_DATAS)){
            parseInfo = new RiskElementParse() ;
        }else if(apiName.equals(Const.InterfaceName.UPLOAD_ATTACH)){
            parseInfo = new UploadParse() ;
        }else{

        }
        return parseInfo;
    }
}
