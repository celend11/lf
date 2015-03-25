
package com.android.sunning.riskpatrol.entity.generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Attachements {

    private List<Attachment> Attachements = new ArrayList<Attachment>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Attachements
     */
    public List<Attachment> getAttachements() {
        return Attachements;
    }

    /**
     * 
     * @param Attachements
     *     The Attachements
     */
    public void setAttachements(List<Attachment> Attachements) {
        this.Attachements = Attachements;
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
        return new HashCodeBuilder().append(Attachements).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof com.android.sunning.riskpatrol.entity.generate.Attachements) == false) {
            return false;
        }
        com.android.sunning.riskpatrol.entity.generate.Attachements rhs = ((com.android.sunning.riskpatrol.entity.generate.Attachements) other);
        return new EqualsBuilder().append(Attachements, rhs.Attachements).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
