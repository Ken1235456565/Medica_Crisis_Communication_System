/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Patient;

import java.util.Date;

/**
 *
 * @author tiankaining
 */
public class MedicalEntry {
        private int entryId;
        private static int entryCounter = 1;
        private Date dateCreated;
        private String diagnosis;
        private String treatment;
        
        public MedicalEntry(String diagnosis, String treatment) {
            this.entryId = entryCounter++;
            this.dateCreated = new Date();
            this.diagnosis = diagnosis;
            this.treatment = treatment;
        }
        
        // Getters and setters
        public int getEntryId() {
            return entryId;
        }
        
        public Date getDateCreated() {
            return dateCreated;
        }
        
        public String getDiagnosis() {
            return diagnosis;
        }
        
        public void setDiagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
        }
        
        public String getTreatment() {
            return treatment;
        }
        
        public void setTreatment(String treatment) {
            this.treatment = treatment;
        }
        
        @Override
        public String toString() {
            return "Entry #" + entryId + " (" + dateCreated + "): " + diagnosis;
        }
    }
