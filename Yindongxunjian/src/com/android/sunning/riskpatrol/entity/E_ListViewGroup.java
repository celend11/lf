package com.android.sunning.riskpatrol.entity;

import java.util.List;

/**
 * Created by sunning on 15/2/14.
 */
public class E_ListViewGroup extends BaseEntity{

    public String parentId ;
    public String parentTag ;

    public List<E_ListViewChild> childItem ;

    public static class E_ListViewChild{
        public String parentId ;
        public String parentTag ;
        public String childId ;
        public String childTag ;
        public String itemName ;
        public String itemId ;
    }

}
