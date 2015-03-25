
package com.android.sunning.riskpatrol.entity.generate.summary;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;

public class MonthFinishedDatum implements SummaryItemDatum{

    @Expose
    private String ReportID;
    @Expose
    private String InspectNum;
    @Expose
    private String SiteName;
    @Expose
    private String CreateTime;

    /**
     * 
     * @return
     *     The ReportID
     */
    public String getReportID() {
        return ReportID;
    }

    /**
     * 
     * @param ReportID
     *     The ReportID
     */
    public void setReportID(String ReportID) {
        this.ReportID = ReportID;
    }

    /**
     * 
     * @return
     *     The InspectNum
     */
    public String getInspectNum() {
        return InspectNum;
    }

    /**
     * 
     * @param InspectNum
     *     The InspectNum
     */
    public void setInspectNum(String InspectNum) {
        this.InspectNum = InspectNum;
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
     *     The CreateTime
     */
    public String getCreateTime() {
        return CreateTime;
    }

    /**
     * 
     * @param CreateTime
     *     The CreateTime
     */
    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ReportID).append(InspectNum).append(SiteName).append(CreateTime).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MonthFinishedDatum) == false) {
            return false;
        }
        MonthFinishedDatum rhs = ((MonthFinishedDatum) other);
        return new EqualsBuilder().append(ReportID, rhs.ReportID).append(InspectNum, rhs.InspectNum).append(SiteName, rhs.SiteName).append(CreateTime, rhs.CreateTime).isEquals();
    }

    @Override
    public String ReportID() {
        return getReportID();
    }

    @Override
    public String InspectNum() {
        return getInspectNum();
    }

    @Override
    public String SiteName() {
        return getSiteName();
    }

    @Override
    public String CreateTime() {
        return getCreateTime();
    }

}
