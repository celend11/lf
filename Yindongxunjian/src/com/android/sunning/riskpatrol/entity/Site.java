package com.android.sunning.riskpatrol.entity;

/**
 * Created by sunning on 15/2/21.
 */
public class Site extends BaseEntity {

    /**
     * "ID": "b194b004-c0ce-4fa0-b21c-77c716af2c0d",
     * "Name": "test1",
     * "Status": 1,
     * "CompanyName": "人力资源服务产业园项目"
     */

    public String UID;
    public String sitesID;
    public String sitesName;
    public int status;
    public String companyName;

    public boolean disabled;
    public boolean isAll;

    public int isSelect = -1 ;

}
