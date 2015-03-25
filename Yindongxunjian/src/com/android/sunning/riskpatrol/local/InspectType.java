package com.android.sunning.riskpatrol.local;

/**
 * Created by sunning on 15/2/28.
 */
public enum InspectType {
    Day(0) , MONTH(1) , WEEK(2) ,DEVOTE(3) , GROUP(4) ;

    private int typeValue ;

    InspectType(int typeValue) {
        this.typeValue = typeValue;
    }

    public int getTypeValue() {
        return typeValue;
    }
}
