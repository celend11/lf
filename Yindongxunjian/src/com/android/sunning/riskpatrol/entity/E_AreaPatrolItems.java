package com.android.sunning.riskpatrol.entity;

import java.util.List;

/**
 * Created by sunning on 15/2/25.
 */
public class E_AreaPatrolItems extends BaseEntity{
    /**
     *  {
     "ItemID": "00000000-0000-0000-0000-000000000000",
     "FengBu": "大型机械",
     "ItemName": "塔式起重机—电气安全(一般)",
     "CanDelete": true,
     "PatrolStatus": 0,
     "Attachements": null,
     "ProblemItems": null
     },
     */
    public String itemId ;
    public String fengBu ;
    public String itemName ;
//    public String attachements ;
    public List<RiskElement> problemItems ;
//    public List<Attachment> attachments ;
    public boolean canDelete ;
    public int patrolStatus ;
    public boolean isSelect;

}
