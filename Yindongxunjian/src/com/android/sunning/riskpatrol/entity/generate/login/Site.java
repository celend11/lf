
package com.android.sunning.riskpatrol.entity.generate.login;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

public class Site {
    private int id ;
    private String Name;
    private String SID;
    private Integer Status;
    public boolean isSelect ;
    private String CompanyName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * 
     * @param Name
     *     The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * 
     * @return
     *     The ID
     */
    public String getID() {
        return SID;
    }

    /**
     * 
     * @param ID
     *     The ID
     */
    public void setID(String ID) {
        this.SID = ID;
    }

    /**
     * 
     * @return
     *     The Status
     */
    public Integer getStatus() {
        return Status;
    }

    /**
     * 
     * @param Status
     *     The Status
     */
    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    /**
     * 
     * @return
     *     The CompanyName
     */
    public String getCompanyName() {
        return CompanyName;
    }

    /**
     * 
     * @param CompanyName
     *     The CompanyName
     */
    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
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
        return new HashCodeBuilder().append(Name).append(SID).append(Status).append(CompanyName).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Site) == false) {
            return false;
        }
        Site rhs = ((Site) other);
        return new EqualsBuilder().append(Name, rhs.Name).append(SID, rhs.SID).append(Status, rhs.Status).append(CompanyName, rhs.CompanyName).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
