package data;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Set;

public class MetaData {
    private String keywords;
    private String author;
    private String creator;
    private String producer;
    private String subject;
    private String title;
    private String trapped;
    private Calendar creationDate;
    private Calendar ModificationDate;
    private Set<String> metadataKeys;

    public String getKeywords() {
        return keywords;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreator() {
        return creator;
    }

    public String getProducer() {
        return producer;
    }

    public String getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
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

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTitle(String title) {
        this.title = title;
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
