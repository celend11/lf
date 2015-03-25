
package com.android.sunning.riskpatrol.entity.generate.summary;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.google.gson.annotations.Expose;

public class MySummary extends BaseEntity{

    @Expose
    private Integer DayFinishedCount;
    @Expose
    private Integer DayUnFinishedCount;
    @Expose
    private List<DayFinishedDatum> DayFinishedData = new ArrayList<DayFinishedDatum>();
    @Expose
    private List<DayUnFinishedDatum> DayUnFinishedData = new ArrayList<DayUnFinishedDatum>();
    @Expose
    private Integer MonthFinishedCount;
    @Expose
    private Integer MonthUnFinishedCount;
    @Expose
    private List<MonthFinishedDatum> MonthFinishedData = new ArrayList<MonthFinishedDatum>();
    @Expose
    private List<MonthUnFinishedDatum> MonthUnFinishedData = new ArrayList<MonthUnFinishedDatum>();
    @Expose
    private List<WeekFinishedDatum> WeekFinishedData = new ArrayList<WeekFinishedDatum>();
    @Expose
    private List<WeekUnFinishedDatum> WeekUnFinishedData = new ArrayList<WeekUnFinishedDatum>();
    @Expose
    private Integer WeekFinishedCount;
    @Expose
    private Integer WeekUnFinishedCount;
    @Expose
    private Integer ZXFinishedCount;
    @Expose
    private Integer ZXUnFinishedCount;
    @Expose
    private List<ZXUnFinishedDatum> ZXFinishedData = new ArrayList<ZXUnFinishedDatum>();
    @Expose
    private List<ZXUnFinishedDatum> ZXUnFinishedData = new ArrayList<ZXUnFinishedDatum>();
    @Expose
    private Integer JTFinishedCount;
    @Expose
    private Integer JTUnFinishedCount;
    @Expose
    private List<JTFinishedDatum> JTFinishedData = new ArrayList<JTFinishedDatum>();
    @Expose
    private List<JTUnFinishedDatum> JTUnFinishedData = new ArrayList<JTUnFinishedDatum>();

    /**
     * 
     * @return
     *     The DayFinishedCount
     */
    public Integer getDayFinishedCount() {
        return DayFinishedCount;
    }

    /**
     * 
     * @param DayFinishedCount
     *     The DayFinishedCount
     */
    public void setDayFinishedCount(Integer DayFinishedCount) {
        this.DayFinishedCount = DayFinishedCount;
    }

    /**
     * 
     * @return
     *     The DayUnFinishedCount
     */
    public Integer getDayUnFinishedCount() {
        return DayUnFinishedCount;
    }

    /**
     * 
     * @param DayUnFinishedCount
     *     The DayUnFinishedCount
     */
    public void setDayUnFinishedCount(Integer DayUnFinishedCount) {
        this.DayUnFinishedCount = DayUnFinishedCount;
    }

    /**
     * 
     * @return
     *     The DayFinishedData
     */
    public List<DayFinishedDatum> getDayFinishedData() {
        return DayFinishedData;
    }

    /**
     * 
     * @param DayFinishedData
     *     The DayFinishedData
     */
    public void setDayFinishedData(List<DayFinishedDatum> DayFinishedData) {
        this.DayFinishedData = DayFinishedData;
    }

    /**
     * 
     * @return
     *     The DayUnFinishedData
     */
    public List<DayUnFinishedDatum> getDayUnFinishedData() {
        return DayUnFinishedData;
    }

    /**
     * 
     * @param DayUnFinishedData
     *     The DayUnFinishedData
     */
    public void setDayUnFinishedData(List<DayUnFinishedDatum> DayUnFinishedData) {
        this.DayUnFinishedData = DayUnFinishedData;
    }

    /**
     * 
     * @return
     *     The MonthFinishedCount
     */
    public Integer getMonthFinishedCount() {
        return MonthFinishedCount;
    }

    /**
     * 
     * @param MonthFinishedCount
     *     The MonthFinishedCount
     */
    public void setMonthFinishedCount(Integer MonthFinishedCount) {
        this.MonthFinishedCount = MonthFinishedCount;
    }

    /**
     * 
     * @return
     *     The MonthUnFinishedCount
     */
    public Integer getMonthUnFinishedCount() {
        return MonthUnFinishedCount;
    }

    /**
     * 
     * @param MonthUnFinishedCount
     *     The MonthUnFinishedCount
     */
    public void setMonthUnFinishedCount(Integer MonthUnFinishedCount) {
        this.MonthUnFinishedCount = MonthUnFinishedCount;
    }

    /**
     * 
     * @return
     *     The MonthFinishedData
     */
    public List<MonthFinishedDatum> getMonthFinishedData() {
        return MonthFinishedData;
    }

    /**
     * 
     * @param MonthFinishedData
     *     The MonthFinishedData
     */
    public void setMonthFinishedData(List<MonthFinishedDatum> MonthFinishedData) {
        this.MonthFinishedData = MonthFinishedData;
    }

    /**
     * 
     * @return
     *     The MonthUnFinishedData
     */
    public List<MonthUnFinishedDatum> getMonthUnFinishedData() {
        return MonthUnFinishedData;
    }

    /**
     * 
     * @param MonthUnFinishedData
     *     The MonthUnFinishedData
     */
    public void setMonthUnFinishedData(List<MonthUnFinishedDatum> MonthUnFinishedData) {
        this.MonthUnFinishedData = MonthUnFinishedData;
    }

    /**
     * 
     * @return
     *     The WeekFinishedData
     */
    public List<WeekFinishedDatum> getWeekFinishedData() {
        return WeekFinishedData;
    }

    /**
     * 
     * @param WeekFinishedData
     *     The WeekFinishedData
     */
    public void setWeekFinishedData(List<WeekFinishedDatum> WeekFinishedData) {
        this.WeekFinishedData = WeekFinishedData;
    }

    /**
     * 
     * @return
     *     The WeekUnFinishedData
     */
    public List<WeekUnFinishedDatum> getWeekUnFinishedData() {
        return WeekUnFinishedData;
    }

    /**
     * 
     * @param WeekUnFinishedData
     *     The WeekUnFinishedData
     */
    public void setWeekUnFinishedData(List<WeekUnFinishedDatum> WeekUnFinishedData) {
        this.WeekUnFinishedData = WeekUnFinishedData;
    }

    /**
     * 
     * @return
     *     The WeekFinishedCount
     */
    public Integer getWeekFinishedCount() {
        return WeekFinishedCount;
    }

    /**
     * 
     * @param WeekFinishedCount
     *     The WeekFinishedCount
     */
    public void setWeekFinishedCount(Integer WeekFinishedCount) {
        this.WeekFinishedCount = WeekFinishedCount;
    }

    /**
     * 
     * @return
     *     The WeekUnFinishedCount
     */
    public Integer getWeekUnFinishedCount() {
        return WeekUnFinishedCount;
    }

    /**
     * 
     * @param WeekUnFinishedCount
     *     The WeekUnFinishedCount
     */
    public void setWeekUnFinishedCount(Integer WeekUnFinishedCount) {
        this.WeekUnFinishedCount = WeekUnFinishedCount;
    }

    /**
     * 
     * @return
     *     The ZXFinishedCount
     */
    public Integer getZXFinishedCount() {
        return ZXFinishedCount;
    }

    /**
     * 
     * @param ZXFinishedCount
     *     The ZXFinishedCount
     */
    public void setZXFinishedCount(Integer ZXFinishedCount) {
        this.ZXFinishedCount = ZXFinishedCount;
    }

    /**
     * 
     * @return
     *     The ZXUnFinishedCount
     */
    public Integer getZXUnFinishedCount() {
        return ZXUnFinishedCount;
    }

    /**
     * 
     * @param ZXUnFinishedCount
     *     The ZXUnFinishedCount
     */
    public void setZXUnFinishedCount(Integer ZXUnFinishedCount) {
        this.ZXUnFinishedCount = ZXUnFinishedCount;
    }

    /**
     * 
     * @return
     *     The ZXFinishedData
     */
    public List<ZXUnFinishedDatum> getZXFinishedData() {
        return ZXFinishedData;
    }

    /**
     * 
     * @param ZXFinishedData
     *     The ZXFinishedData
     */
    public void setZXFinishedData(List<ZXUnFinishedDatum> ZXFinishedData) {
        this.ZXFinishedData = ZXFinishedData;
    }

    /**
     * 
     * @return
     *     The ZXUnFinishedData
     */
    public List<ZXUnFinishedDatum> getZXUnFinishedData() {
        return ZXUnFinishedData;
    }

    /**
     * 
     * @param ZXUnFinishedData
     *     The ZXUnFinishedData
     */
    public void setZXUnFinishedData(List<ZXUnFinishedDatum> ZXUnFinishedData) {
        this.ZXUnFinishedData = ZXUnFinishedData;
    }

    /**
     * 
     * @return
     *     The JTFinishedCount
     */
    public Integer getJTFinishedCount() {
        return JTFinishedCount;
    }

    /**
     * 
     * @param JTFinishedCount
     *     The JTFinishedCount
     */
    public void setJTFinishedCount(Integer JTFinishedCount) {
        this.JTFinishedCount = JTFinishedCount;
    }

    /**
     * 
     * @return
     *     The JTUnFinishedCount
     */
    public Integer getJTUnFinishedCount() {
        return JTUnFinishedCount;
    }

    /**
     * 
     * @param JTUnFinishedCount
     *     The JTUnFinishedCount
     */
    public void setJTUnFinishedCount(Integer JTUnFinishedCount) {
        this.JTUnFinishedCount = JTUnFinishedCount;
    }

    /**
     * 
     * @return
     *     The JTFinishedData
     */
    public List<JTFinishedDatum> getJTFinishedData() {
        return JTFinishedData;
    }

    /**
     * 
     * @param JTFinishedData
     *     The JTFinishedData
     */
    public void setJTFinishedData(List<JTFinishedDatum> JTFinishedData) {
        this.JTFinishedData = JTFinishedData;
    }

    /**
     * 
     * @return
     *     The JTUnFinishedData
     */
    public List<JTUnFinishedDatum> getJTUnFinishedData() {
        return JTUnFinishedData;
    }

    /**
     * 
     * @param JTUnFinishedData
     *     The JTUnFinishedData
     */
    public void setJTUnFinishedData(List<JTUnFinishedDatum> JTUnFinishedData) {
        this.JTUnFinishedData = JTUnFinishedData;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(DayFinishedCount).append(DayUnFinishedCount).append(DayFinishedData).append(DayUnFinishedData).append(MonthFinishedCount).append(MonthUnFinishedCount).append(MonthFinishedData).append(MonthUnFinishedData).append(WeekFinishedData).append(WeekUnFinishedData).append(WeekFinishedCount).append(WeekUnFinishedCount).append(ZXFinishedCount).append(ZXUnFinishedCount).append(ZXFinishedData).append(ZXUnFinishedData).append(JTFinishedCount).append(JTUnFinishedCount).append(JTFinishedData).append(JTUnFinishedData).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MySummary) == false) {
            return false;
        }
        MySummary rhs = ((MySummary) other);
        return new EqualsBuilder().append(DayFinishedCount, rhs.DayFinishedCount).append(DayUnFinishedCount, rhs.DayUnFinishedCount).append(DayFinishedData, rhs.DayFinishedData).append(DayUnFinishedData, rhs.DayUnFinishedData).append(MonthFinishedCount, rhs.MonthFinishedCount).append(MonthUnFinishedCount, rhs.MonthUnFinishedCount).append(MonthFinishedData, rhs.MonthFinishedData).append(MonthUnFinishedData, rhs.MonthUnFinishedData).append(WeekFinishedData, rhs.WeekFinishedData).append(WeekUnFinishedData, rhs.WeekUnFinishedData).append(WeekFinishedCount, rhs.WeekFinishedCount).append(WeekUnFinishedCount, rhs.WeekUnFinishedCount).append(ZXFinishedCount, rhs.ZXFinishedCount).append(ZXUnFinishedCount, rhs.ZXUnFinishedCount).append(ZXFinishedData, rhs.ZXFinishedData).append(ZXUnFinishedData, rhs.ZXUnFinishedData).append(JTFinishedCount, rhs.JTFinishedCount).append(JTUnFinishedCount, rhs.JTUnFinishedCount).append(JTFinishedData, rhs.JTFinishedData).append(JTUnFinishedData, rhs.JTUnFinishedData).isEquals();
    }

}
