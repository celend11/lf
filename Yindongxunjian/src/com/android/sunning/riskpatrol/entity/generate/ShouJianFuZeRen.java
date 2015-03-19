
package com.android.sunning.riskpatrol.entity.generate;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ShouJianFuZeRen {

    @Expose
    private String ShouJianFuZeRenID;
    @Expose
    private String ShouJianFuZeRenName;

    /**
     * 
     * @return
     *     The ShouJianFuZeRenID
     */
    public String getShouJianFuZeRenID() {
        return ShouJianFuZeRenID;
    }

    /**
     * 
     * @param ShouJianFuZeRenID
     *     The ShouJianFuZeRenID
     */
    public void setShouJianFuZeRenID(String ShouJianFuZeRenID) {
        this.ShouJianFuZeRenID = ShouJianFuZeRenID;
    }

    /**
     * 
     * @return
     *     The ShouJianFuZeRenName
     */
    public String getShouJianFuZeRenName() {
        return ShouJianFuZeRenName;
    }

    /**
     * 
     * @param ShouJianFuZeRenName
     *     The ShouJianFuZeRenName
     */
    public void setShouJianFuZeRenName(String ShouJianFuZeRenName) {
        this.ShouJianFuZeRenName = ShouJianFuZeRenName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ShouJianFuZeRenID).append(ShouJianFuZeRenName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShouJianFuZeRen) == false) {
            return false;
        }
        ShouJianFuZeRen rhs = ((ShouJianFuZeRen) other);
        return new EqualsBuilder().append(ShouJianFuZeRenID, rhs.ShouJianFuZeRenID).append(ShouJianFuZeRenName, rhs.ShouJianFuZeRenName).isEquals();
    }

}
