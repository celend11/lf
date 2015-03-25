
package com.android.sunning.riskpatrol.entity.generate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;

public class PatrolItem {

    public boolean isSelect ;
    @Expose
    private String ItemID;
    @Expose
    private String FengBu;
    @Expose
    private String ItemName;
    @Expose
    private Boolean CanDelete;
    @Expose
    private Integer PatrolStatus;
    @Expose
    private Object Attachements;
    @Expose
    private RiskElements ProblemItems;

    /**
     * 
     * @return
     *     The ItemID
     */
    public String getItemID() {
        return ItemID;
    }

    /**
     * 
     * @param ItemID
     *     The ItemID
     */
    public void setItemID(String ItemID) {
        this.ItemID = ItemID;
    }

    /**
     * 
     * @return
     *     The FengBu
     */
    public String getFengBu() {
        return FengBu;
    }

    /**
     * 
     * @param FengBu
     *     The FengBu
     */
    public void setFengBu(String FengBu) {
        this.FengBu = FengBu;
    }

    /**
     * 
     * @return
     *     The ItemName
     */
    public String getItemName() {
        return ItemName;
    }

    /**
     * 
     * @param ItemName
     *     The ItemName
     */
    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    /**
     * 
     * @return
     *     The CanDelete
     */
    public Boolean getCanDelete() {
        return CanDelete;
    }

    /**
     * 
     * @param CanDelete
     *     The CanDelete
     */
    public void setCanDelete(Boolean CanDelete) {
        this.CanDelete = CanDelete;
    }

    /**
     * 
     * @return
     *     The PatrolStatus
     */
    public Integer getPatrolStatus() {
        return PatrolStatus;
    }

    /**
     * 
     * @param PatrolStatus
     *     The PatrolStatus
     */
    public void setPatrolStatus(Integer PatrolStatus) {
        this.PatrolStatus = PatrolStatus;
    }

    /**
     * 
     * @return
     *     The Attachements
     */
    public Object getAttachements() {
        return Attachements;
    }

    /**
     * 
     * @param Attachements
     *     The Attachements
     */
    public void setAttachements(Object Attachements) {
        this.Attachements = Attachements;
    }

    /**
     * 
     * @return
     *     The ProblemItems
     */
    public RiskElements getProblemItems() {
        return ProblemItems;
    }

    /**
     * 
     * @param ProblemItems
     *     The ProblemItems
     */
    public void setProblemItems(RiskElements ProblemItems) {
        this.ProblemItems = ProblemItems;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ItemID).append(FengBu).append(ItemName).append(CanDelete).append(PatrolStatus).append(Attachements).append(ProblemItems).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PatrolItem) == false) {
            return false;
        }
        PatrolItem rhs = ((PatrolItem) other);
        return new EqualsBuilder().append(ItemID, rhs.ItemID).append(FengBu, rhs.FengBu).append(ItemName, rhs.ItemName).append(CanDelete, rhs.CanDelete).append(PatrolStatus, rhs.PatrolStatus).append(Attachements, rhs.Attachements).append(ProblemItems, rhs.ProblemItems).isEquals();
    }

}
