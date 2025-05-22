package Model.WorkQueue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Inner Report class (already defined in your original code)
    public  class Report {
        private String reportId;
        private String title;
        private String type;
        private Date creationDate;
        private Date startDate;
        private Date endDate;
        private Map<String, Object> data;
        private String status; // Draft, Final
        
        private static int counter = 1;
        
        public Report(String title, String type, Date creationDate) {
            this.reportId = "RPT" + counter++;
            this.title = title;
            this.type = type;
            this.creationDate = creationDate;
            this.data = new HashMap<>();
            this.status = "Draft";
        }
        
        public Report(String title, String type, Date creationDate, Date startDate, Date endDate) {
            this(title, type, creationDate);
            this.startDate = startDate;
            this.endDate = endDate;
        }
        
        // Getters and setters
        public String getReportId() {
            return reportId;
        }
        
        public String getTitle() {
            return title;
        }
        
        public void setTitle(String title) {
            this.title = title;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public Date getCreationDate() {
            return creationDate;
        }
        
        public Date getStartDate() {
            return startDate;
        }
        
        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }
        
        public Date getEndDate() {
            return endDate;
        }
        
        public void setEndDate(Date endDate) { // Corrected method name
            this.endDate = endDate;
        }
        
        public Map<String, Object> getData() {
            return data;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        // Add data to report
        public void addData(String key, Object value) {
            data.put(key, value);
        }
        
        // Finalize report
        public void finalizeReport() {
            this.status = "Final";
        }
        
        @Override
        public String toString() {
            return title + " (" + type + ") [" + status + "]";
        }
    }