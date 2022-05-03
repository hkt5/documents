package data;

import org.apache.pdfbox.cos.COSDictionary;
import java.util.Calendar;
import java.util.Set;

public class PdfMetaData extends MetaData {
    private String author;
    private COSDictionary cOSObject;
    private Calendar creationDate;
    private Set<String> metadataKeys;
    private Calendar modificationDate;
    private String producer;
    private String trapped;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCOSObject(COSDictionary cOSObject) {
        this.cOSObject = cOSObject;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public void setMetadataKeys(Set<String> metadataKeys) {
        this.metadataKeys = metadataKeys;
    }

    public void setModificationDate(Calendar modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setTrapped(String trapped) {
        this.trapped = trapped;
    }

    public String getAuthor() {
        return author;
    }

    public COSDictionary getCOSObject() {
        return cOSObject;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public Set<String> getMetadataKeys() {
        return metadataKeys;
    }

    public Calendar getModificationDate() {
        return modificationDate;
    }

    public String getProducer() {
        return producer;
    }

    public String getTrapped() {
        return trapped;
    }
}
