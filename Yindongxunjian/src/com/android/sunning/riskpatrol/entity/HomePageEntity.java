package com.android.sunning.riskpatrol.entity;

/**
 * Created by sunning on 15/2/28.
 * 首页数据需要的bean
 */
public class HomePageEntity extends BaseEntity{

    /**
     * 是否是本地数据
     */
    public boolean isLocal ;

    /**
     * 检查项名称
     */
    public String patrolItemName ;

    /**
     * 检查单唯一编号
     */
    public String inspectNum ;
    /*
     * 检查单编号
     */
    public String inspectNumFormat ;

    /**
     * 是否领导派发
     */
    public boolean isDispatch ;

    /**
     * 派发者姓名 CreatorName 就是创建单子人的名称
     */
    public String creatorName ;

    /**
     * 创建者ID
     */
    public String creatorID ;

    /**
     * 创建时间
     */
    public String createDate ;

}
