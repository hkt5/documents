package data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FileDiffJsonData implements Serializable {
    private Date compareDate;
    private List<FileDifference> listFileDifference;
    private Map<String, Object> mapOfDiffInMetaData;

    public static class Builder {
        private Date compareDate;
        private List<FileDifference> listFileDifference;
        private Map<String, Object> mapOfDiffInMetaData;

        public Builder compareDate(Date compareDate) {
            this.compareDate = compareDate;
            return this;
        }

        public Builder listFileDifference(List<FileDifference> listFileDifference) {
            this.listFileDifference = listFileDifference;
            return this;
        }

        public Builder mapOfDiffInMetaData(Map<String, Object> mapOfDiffInMetaData) {
            this.mapOfDiffInMetaData = mapOfDiffInMetaData;
            return this;
        }

        public FileDiffJsonData build() {
            FileDiffJsonData fileDiffJsonData = new FileDiffJsonData();
            fileDiffJsonData.compareDate = this.compareDate;
            fileDiffJsonData.listFileDifference = this.listFileDifference;
            fileDiffJsonData.mapOfDiffInMetaData = this.mapOfDiffInMetaData;
            return fileDiffJsonData;
        }
    }

    public Date getCompareDate() {
        return compareDate;
    }

    public void setCompareDate(Date compareDate) {
        this.compareDate = compareDate;
    }

    public List<FileDifference> getListFileDifference() {
        return listFileDifference;
    }

    public Map<String, Object> getMapOfDiffInMetaData() {
        return mapOfDiffInMetaData;
    }

    public void setListFileDifference(List<FileDifference> listFileDifference) {
        this.listFileDifference = listFileDifference;
    }

    public void setMapOfDiffInMetaData(Map<String, Object> mapOfDiffInMetaData) {
        this.mapOfDiffInMetaData = mapOfDiffInMetaData;
    }
}
