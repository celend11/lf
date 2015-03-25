
package com.android.sunning.riskpatrol.entity.generate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.google.gson.annotations.Expose;

public class HomeEntity extends BaseEntity {

    @Expose
    private Integer Status;
    @Expose
    private Object Error;
    @Expose
    private List<Datum> Data = new ArrayList<Datum>();

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
     *     The Error
     */
    public Object getError() {
        return Error;
    }

    /**
     * 
     * @param Error
     *     The Error
     */
    public void setError(Object Error) {
        this.Error = Error;
    }

    /**
     * 
     * @return
     *     The Data
     */
    public List<Datum> getData() {
        return Data;
    }

    /**
     * 
     * @param Data
     *     The Data
     */
    public void setData(List<Datum> Data) {
        this.Data = Data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Status).append(Error).append(Data).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HomeEntity) == false) {
            return false;
        }
        HomeEntity rhs = ((HomeEntity) other);
        return new EqualsBuilder().append(Status, rhs.Status).append(Error, rhs.Error).append(Data, rhs.Data).isEquals();
    }

}
