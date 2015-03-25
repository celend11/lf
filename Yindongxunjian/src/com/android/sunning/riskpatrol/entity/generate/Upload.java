package com.android.sunning.riskpatrol.entity.generate;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.android.sunning.riskpatrol.entity.BaseEntity;
import com.google.gson.annotations.Expose;

public class Upload extends BaseEntity{

    @Expose
    private Boolean success;
    @Expose
    private String fileName;
    @Expose
    private String fileKey;
    @Expose
    private String error;
    @Expose
    private Integer size;

    public String aId ;

    @Expose
    private String url;

    /**
     * @return The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return The fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName The fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return The fileKey
     */
    public String getFileKey() {
        return fileKey;
    }

    /**
     * @param fileKey The fileKey
     */
    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    /**
     * @return The error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error The error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return The size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size The size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(success).append(fileName).append(fileKey).append(error).append(size).append(id).append(url).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Upload) == false) {
            return false;
        }
        Upload rhs = ((Upload) other);
        return new EqualsBuilder().append(success, rhs.success).append(fileName, rhs.fileName).append(fileKey, rhs.fileKey).append(error, rhs.error).append(size, rhs.size).append(id, rhs.id).append(url, rhs.url).isEquals();
    }

}
