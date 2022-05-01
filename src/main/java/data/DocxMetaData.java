package data;

import java.util.Calendar;
import java.util.Set;

public class DocxMetaData extends MetaData {

    private String author;
    private String producer;
    private String trapped;
    private Calendar creationDate;
    private Calendar ModificationDate;
    private Set<String> metadataKeys;

    public String getAuthor() {
        return author;
    }

    public String getProducer() {
        return producer;
    }

    public String getTrapped() {
        return trapped;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public Calendar getModificationDate() {
        return ModificationDate;
    }

    public Set<String> getMetadataKeys() {
        return metadataKeys;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setTrapped(String trapped) {
        this.trapped = trapped;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public void setModificationDate(Calendar modificationDate) {
        ModificationDate = modificationDate;
    }

    public void setMetadataKeys(Set<String> metadataKeys) {
        this.metadataKeys = metadataKeys;
    }
}
