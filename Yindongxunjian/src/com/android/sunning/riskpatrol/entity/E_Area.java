package com.android.sunning.riskpatrol.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunning on 15/2/25.
 */
public class E_Area extends BaseEntity{

    public String AID ;

    public String updateTime ;

    public List<E_AcceptCheckArea> areaList = new ArrayList<E_AcceptCheckArea>() ;
}
