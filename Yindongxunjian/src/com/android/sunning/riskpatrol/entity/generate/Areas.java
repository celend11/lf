package com.android.sunning.riskpatrol.entity.generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.android.sunning.riskpatrol.entity.BaseEntity;

public class Areas extends BaseEntity {

    private List<Area> Areas = new ArrayList<Area>();
    private String ID;
    private String UpdateTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The Areas
     */
    public List<Area> getAreas() {
        return Areas;
    }

    /**
     * @param Areas The Areas
     */
    public void setAreas(List<Area> Areas) {
        this.Areas = Areas;
    }

    /**
     * @return The ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID The ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return The UpdateTime
     */
    public String getUpdateTime() {
        return UpdateTime;
    }

    /**
     * @param UpdateTime The UpdateTime
     */
    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
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
        return new HashCodeBuilder().append(Areas).append(ID).append(UpdateTime).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof com.android.sunning.riskpatrol.entity.generate.Areas) == false) {
            return false;
        }
        com.android.sunning.riskpatrol.entity.generate.Areas rhs = ((com.android.sunning.riskpatrol.entity.generate.Areas) other);
        return new EqualsBuilder().append(Areas, rhs.Areas).append(ID, rhs.ID).append(UpdateTime, rhs.UpdateTime).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}