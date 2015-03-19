package com.android.sunning.riskpatrol.entity;

/**
 * Created by sunning on 15/2/8.
 */

import java.io.Serializable;

/**
 * Created by sunning on 14-8-4.
 * 所有Bean的基类，前缀B为B开头字母
 */
public class BaseEntity implements Serializable {
    public int id ;
    public int code = -1;
    public String message ;
}
