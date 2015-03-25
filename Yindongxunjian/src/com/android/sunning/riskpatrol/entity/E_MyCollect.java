package com.android.sunning.riskpatrol.entity;

import java.util.List;

import com.android.sunning.riskpatrol.entity.generate.summary.SummaryItemDatum;

/**
 * Created by sunning on 15/2/13.
 */
public class E_MyCollect extends BaseEntity{

    public String id ;
    public String finished ;
    public List<? extends SummaryItemDatum> finishList ;
    public List<? extends SummaryItemDatum> unFinishList ;
    public String unfinished ;
    public int type ;
    public String title ;

}
