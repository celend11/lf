package com.android.sunning.riskpatrol.entity;

/**
 * Created by sunning on 15/2/26.
 * 风险因素
 */
public class RiskElement {
    public int index ;
    public String ID ;
    public String FengBu ;
    public String FengXiang ;
    public String EName ;
    public boolean isSelect ;

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFengBu(String fengBu) {
        FengBu = fengBu;
    }

    public void setFengXiang(String fengXiang) {
        FengXiang = fengXiang;
    }

    public void setEName(String EName) {
        this.EName = EName;
    }
}
