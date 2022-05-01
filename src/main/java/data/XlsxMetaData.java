package data;

import org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart;

import java.util.Date;

public class XlsxMetaData extends MetaData {
    private String category;
    private String contentStatus;
    private String contentType;
    private Date created;
    private String description;
    private String identifier;
    private String lastModifiedByUser;
    private Date lastPrinted;
    private Date modified;
    private String revision;
    private PackagePropertiesPart underlyingProperties;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setLastModifiedByUser(String lastModifiedByUser) {
        this.lastModifiedByUser = lastModifiedByUser;
    }

    public void setLastPrinted(Date lastPrinted) {
        this.lastPrinted = lastPrinted;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public void setUnderlyingProperties(PackagePropertiesPart underlyingProperties) {
        this.underlyingProperties = underlyingProperties;
    }

    public String getCategory() {
        return category;
    }

    public String getContentStatus() {
        return contentStatus;
    }

    public String getContentType() {
        return contentType;
    }

    public Date getCreated() {
        return created;
    }

    public String getDescription() {
        return description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getLastModifiedByUser() {
        return lastModifiedByUser;
    }

    public Date getLastPrinted() {
        return lastPrinted;
    }

    public Date getModified() {
        return modified;
    }

    public String getRevision() {
        return revision;
    }

    public PackagePropertiesPart getUnderlyingProperties() {
        return underlyingProperties;
    }
}
