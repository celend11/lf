
package com.android.sunning.riskpatrol.entity.generate.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.android.sunning.riskpatrol.entity.BaseEntity;
public class Login extends BaseEntity{

    private String PWD;
    private String Name;
    private String UID;
    private String Time;
    private List<Site> Sites = new ArrayList<Site>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The PWD
     */
    public String getPWD() {
        return PWD;
    }

    /**
     * 
     * @param PWD
     *     The PWD
     */
    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

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
        return UID;
    }

    /**
     * 
     * @param ID
     *     The ID
     */
    public void setID(String ID) {
        this.UID = ID;
    }

    /**
     * 
     * @return
     *     The Time
     */
    public String getTime() {
        return Time;
    }

    /**
     * 
     * @param Time
     *     The Time
     */
    public void setTime(String Time) {
        this.Time = Time;
    }

    /**
     * 
     * @return
     *     The Sites
     */
    public List<Site> getSites() {
        return Sites;
    }

    /**
     * 
     * @param Sites
     *     The Sites
     */
    public void setSites(List<Site> Sites) {
        this.Sites = Sites;
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
        return new HashCodeBuilder().append(PWD).append(Name).append(UID).append(Time).append(Sites).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Login) == false) {
            return false;
        }
        Login rhs = ((Login) other);
        return new EqualsBuilder().append(PWD, rhs.PWD).append(Name, rhs.Name).append(UID, rhs.UID).append(Time, rhs.Time).append(Sites, rhs.Sites).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
