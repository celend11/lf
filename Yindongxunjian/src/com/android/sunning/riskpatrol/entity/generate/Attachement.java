
package com.android.sunning.riskpatrol.entity.generate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attachement {

    private List<Attachments> Attachements = new ArrayList<Attachments>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Attachements
     */
    public List<Attachments> getAttachements() {
        return Attachements;
    }

    /**
     * 
     * @param Attachements
     *     The Attachements
     */
    public void setAttachements(List<Attachments> Attachements) {
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
        if ((other instanceof Attachement) == false) {
            return false;
        }
        Attachement rhs = ((Attachement) other);
        return new EqualsBuilder().append(Attachements, rhs.Attachements).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
