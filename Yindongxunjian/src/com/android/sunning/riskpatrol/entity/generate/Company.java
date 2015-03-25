
package com.android.sunning.riskpatrol.entity.generate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;

public class Company {

    @Expose
    private String CompanyID;
    @Expose
    private String CompanyName;

    /**
     * 
     * @return
     *     The CompanyID
     */
    public String getCompanyID() {
        return CompanyID;
    }

    /**
     * 
     * @param CompanyID
     *     The CompanyID
     */
    public void setCompanyID(String CompanyID) {
        this.CompanyID = CompanyID;
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(CompanyID).append(CompanyName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Company) == false) {
            return false;
        }
        Company rhs = ((Company) other);
        return new EqualsBuilder().append(CompanyID, rhs.CompanyID).append(CompanyName, rhs.CompanyName).isEquals();
    }

}
