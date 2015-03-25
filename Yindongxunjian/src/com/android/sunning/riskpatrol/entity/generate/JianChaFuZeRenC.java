
package com.android.sunning.riskpatrol.entity.generate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;

public class JianChaFuZeRenC {

    @Expose
    private String JianChaFuZeRenID;
    @Expose
    private String JianChaFuZeRen;

    /**
     * 
     * @return
     *     The JianChaFuZeRenID
     */
    public String getJianChaFuZeRenID() {
        return JianChaFuZeRenID;
    }

    /**
     * 
     * @param JianChaFuZeRenID
     *     The JianChaFuZeRenID
     */
    public void setJianChaFuZeRenID(String JianChaFuZeRenID) {
        this.JianChaFuZeRenID = JianChaFuZeRenID;
    }

    /**
     * 
     * @return
     *     The JianChaFuZeRen
     */
    public String getJianChaFuZeRen() {
        return JianChaFuZeRen;
    }

    /**
     * 
     * @param JianChaFuZeRen
     *     The JianChaFuZeRen
     */
    public void setJianChaFuZeRen(String JianChaFuZeRen) {
        this.JianChaFuZeRen = JianChaFuZeRen;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(JianChaFuZeRenID).append(JianChaFuZeRen).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof JianChaFuZeRenC) == false) {
            return false;
        }
        JianChaFuZeRenC rhs = ((JianChaFuZeRenC) other);
        return new EqualsBuilder().append(JianChaFuZeRenID, rhs.JianChaFuZeRenID).append(JianChaFuZeRen, rhs.JianChaFuZeRen).isEquals();
    }

}
