package com.android.sunning.riskpatrol.entity;

import java.util.List;

/**
 * Created by sunning on 15/2/13.
 */
public class E_AcceptCheckArea extends BaseEntity{
    public String areaName ;
    public String areaID ;
    public boolean isSelect ;
    public List<E_AreaPatrolItems> patrolItemsList ;
}
