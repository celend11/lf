package com.android.sunning.riskpatrol.entity.generate;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Transient;

public class RiskElement {

    @Transient
    public int index;

    @Transient
    public boolean isSelect;

    @Id
    private int id ;

    @Expose
    private String RID;
    @Expose
    private String FengBu;
    @Expose
    private String FengXiang;
    @Expose
    private String EName;

    @Expose
    private Attachements attachements ;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The ID
     */
    public String getID() {
        return RID;
    }

    /**
     * @param ID The ID
     */
    public void setID(String ID) {
        this.RID = ID;
    }

    /**
     * @return The FengBu
     */
    public String getFengBu() {
        return FengBu;
    }

    /**
     * @param FengBu The FengBu
     */
    public void setFengBu(String FengBu) {
        this.FengBu = FengBu;
    }

    /**
     * @return The FengXiang
     */
    public String getFengXiang() {
        return FengXiang;
    }

    /**
     * @param FengXiang The FengXiang
     */
    public void setFengXiang(String FengXiang) {
        this.FengXiang = FengXiang;
    }

    /**
     * @return The EName
     */
    public String getEName() {
        return EName;
    }

    /**
     * @param EName The EName
     */
    public void setEName(String EName) {
        this.EName = EName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(RID).append(FengBu).append(FengXiang).append(EName).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RiskElement) == false) {
            return false;
        }
        RiskElement rhs = ((RiskElement) other);
        return new EqualsBuilder().append(RID, rhs.RID).append(FengBu, rhs.FengBu).append(FengXiang, rhs.FengXiang).append(EName, rhs.EName).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

    public Attachements getAttachements() {
        return attachements;
    }

    public void setAttachements(Attachements attachements) {
        this.attachements = attachements;
    }
}