
package com.android.sunning.riskpatrol.entity.generate;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class JianChaXiangMu {

    @Expose
    private List<Area> Areas = new ArrayList<Area>();

    /**
     * 
     * @return
     *     The Areas
     */
    public List<Area> getAreas() {
        return Areas;
    }

    /**
     * 
     * @param Areas
     *     The Areas
     */
    public void setAreas(List<Area> Areas) {
        this.Areas = Areas;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Areas).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof JianChaXiangMu) == false) {
            return false;
        }
        JianChaXiangMu rhs = ((JianChaXiangMu) other);
        return new EqualsBuilder().append(Areas, rhs.Areas).isEquals();
    }

}
