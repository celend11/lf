
package com.android.sunning.riskpatrol.entity.generate;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.android.sunning.riskpatrol.entity.AttachmentType;
import com.google.gson.annotations.Expose;

public class Attachment {

    @Expose
    private String FileName;
    @Expose
    private String FileOldName;
    @Expose
    private String Size;
    @Expose
    private String FileUrl;

    private File file ;

    public AttachmentType attachmentType ;

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
        if ((other instanceof Attachment) == false) {
            return false;
        }
        Attachment rhs = ((Attachment) other);
        return new EqualsBuilder().append(FileName, rhs.FileName).append(FileOldName, rhs.FileOldName).append(Size, rhs.Size).append(FileUrl, rhs.FileUrl).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
