package com.android.sunning.riskpatrol.entity.generate;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiskElements extends BaseEntity {
    private List<RiskElement> RiskElements = new ArrayList<RiskElement>();
    private String UpdateTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The RiskElements
     */
    public List<RiskElement> getRiskElements() {
        return RiskElements;
    }

    /**
     * @param RiskElements The RiskElements
     */
    public void setRiskElements(List<RiskElement> RiskElements) {
        this.RiskElements = RiskElements;
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
        return new HashCodeBuilder().append(RiskElements).append(UpdateTime).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RiskElements) == false) {
            return false;
        }
        RiskElements rhs = ((RiskElements) other);
        return new EqualsBuilder().append(RiskElements, rhs.RiskElements).append(UpdateTime, rhs.UpdateTime).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
