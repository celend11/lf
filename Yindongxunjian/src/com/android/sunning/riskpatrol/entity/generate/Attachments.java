
package com.android.sunning.riskpatrol.entity.generate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

public class Attachments {

    private String FileName;
    private String FileOldName;
    private String Size;
    private String FileUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The FileName
     */
    public String getFileName() {
        return FileName;
    }

    /**
     * 
     * @param FileName
     *     The FileName
     */
    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    /**
     * 
     * @return
     *     The FileOldName
     */
    public String getFileOldName() {
        return FileOldName;
    }

    /**
     * 
     * @param FileOldName
     *     The FileOldName
     */
    public void setFileOldName(String FileOldName) {
        this.FileOldName = FileOldName;
    }

    /**
     * 
     * @return
     *     The Size
     */
    public String getSize() {
        return Size;
    }

    /**
     * 
     * @param Size
     *     The Size
     */
    public void setSize(String Size) {
        this.Size = Size;
    }

    /**
     * 
     * @return
     *     The FileUrl
     */
    public String getFileUrl() {
        return FileUrl;
    }

    /**
     * 
     * @param FileUrl
     *     The FileUrl
     */
    public void setFileUrl(String FileUrl) {
        this.FileUrl = FileUrl;
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
        return new HashCodeBuilder().append(FileName).append(FileOldName).append(Size).append(FileUrl).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Attachments) == false) {
            return false;
        }
        Attachments rhs = ((Attachments) other);
        return new EqualsBuilder().append(FileName, rhs.FileName).append(FileOldName, rhs.FileOldName).append(Size, rhs.Size).append(FileUrl, rhs.FileUrl).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
