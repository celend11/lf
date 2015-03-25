
package com.android.sunning.riskpatrol.entity.generate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;

public class Area {

    @Expose
    private String AreaName;
    @Expose
    private String AreaID;
    @Expose
    private List<PatrolItem> PatrolItems = new ArrayList<PatrolItem>();
    @Expose
    private List<CusPatrolItem> CusPatrolItems = new ArrayList<CusPatrolItem>();

    public boolean isSelect ;

    /**
     * 
     * @return
     *     The AreaName
     */
    public String getAreaName() {
        return AreaName;
    }

    /**
     * 
     * @param AreaName
     *     The AreaName
     */
    public void setAreaName(String AreaName) {
        this.AreaName = AreaName;
    }

    /**
     * 
     * @return
     *     The AreaID
     */
    public String getAreaID() {
        return AreaID;
    }

    /**
     * 
     * @param AreaID
     *     The AreaID
     */
    public void setAreaID(String AreaID) {
        this.AreaID = AreaID;
    }

    /**
     * 
     * @return
     *     The PatrolItems
     */
    public List<PatrolItem> getPatrolItems() {
        return PatrolItems;
    }

    /**
     * 
     * @param PatrolItems
     *     The PatrolItems
     */
    public void setPatrolItems(List<PatrolItem> PatrolItems) {
        this.PatrolItems = PatrolItems;
    }

    /**
     * 
     * @return
     *     The CusPatrolItems
     */
    public List<CusPatrolItem> getCusPatrolItems() {
        return CusPatrolItems;
    }

    /**
     * 
     * @param CusPatrolItems
     *     The CusPatrolItems
     */
    public void setCusPatrolItems(List<CusPatrolItem> CusPatrolItems) {
        this.CusPatrolItems = CusPatrolItems;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(AreaName).append(AreaID).append(PatrolItems).append(CusPatrolItems).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Area) == false) {
            return false;
        }
        Area rhs = ((Area) other);
        return new EqualsBuilder().append(AreaName, rhs.AreaName).append(AreaID, rhs.AreaID).append(PatrolItems, rhs.PatrolItems).append(CusPatrolItems, rhs.CusPatrolItems).isEquals();
    }

}
