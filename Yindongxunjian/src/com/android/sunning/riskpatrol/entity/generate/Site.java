
package com.android.sunning.riskpatrol.entity.generate;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Site {

    @Expose
    private String SiteID;
    @Expose
    private String SiteName;
    @Expose
    private Boolean Disabled;
    @Expose
    private Boolean IsAll;

    /**
     * 
     * @return
     *     The SiteID
     */
    public String getSiteID() {
        return SiteID;
    }

    /**
     * 
     * @param SiteID
     *     The SiteID
     */
    public void setSiteID(String SiteID) {
        this.SiteID = SiteID;
    }

    /**
     * 
     * @return
     *     The SiteName
     */
    public String getSiteName() {
        return SiteName;
    }

    /**
     * 
     * @param SiteName
     *     The SiteName
     */
    public void setSiteName(String SiteName) {
        this.SiteName = SiteName;
    }

    /**
     * 
     * @return
     *     The Disabled
     */
    public Boolean getDisabled() {
        return Disabled;
    }

    /**
     * 
     * @param Disabled
     *     The Disabled
     */
    public void setDisabled(Boolean Disabled) {
        this.Disabled = Disabled;
    }

    /**
     * 
     * @return
     *     The IsAll
     */
    public Boolean getIsAll() {
        return IsAll;
    }

    /**
     * 
     * @param IsAll
     *     The IsAll
     */
    public void setIsAll(Boolean IsAll) {
        this.IsAll = IsAll;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(SiteID).append(SiteName).append(Disabled).append(IsAll).toHashCode();
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
        return new EqualsBuilder().append(SiteID, rhs.SiteID).append(SiteName, rhs.SiteName).append(Disabled, rhs.Disabled).append(IsAll, rhs.IsAll).isEquals();
    }

}
