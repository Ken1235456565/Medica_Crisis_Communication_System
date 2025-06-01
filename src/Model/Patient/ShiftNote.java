/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Patient;

/**
 *
 * @author tiankaining
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a nurse's shift note for patients.
 * Combines ID linkage and UI-friendly date string with internal full metadata handling.
 * @author tiank
 */
public class ShiftNote {
    private String noteId;
    private String nurseId; // Link to employee record
    private String nurseName;
    private String contactEmail;
    private String date; // String format for display (e.g., "2025-05-30")
    private Date shiftDate; // Internal use
    private String notes;
    private String shift; // "Day", "Evening", "Night"
    private Date createdDate;
    private String status; // "Active", "Archived", "Draft"

    private static int counter = 1;

    // ---------------- Constructors ----------------

    // Primary constructor with Date
    public ShiftNote(String nurseId, String nurseName, String contactEmail, Date shiftDate,
                     String notes, String shift) {
        this.noteId = "SN" + counter++;
        this.nurseId = nurseId;
        this.nurseName = nurseName;
        this.contactEmail = contactEmail;
        this.shiftDate = shiftDate;
        this.date = formatDate(shiftDate);
        this.notes = notes;
        this.shift = shift;
        this.createdDate = new Date();
        this.status = "Active";
    }

    // Constructor with date string (UI-friendly)
    public ShiftNote(String nurseId, String nurseName, String contactEmail, String date, String notes) {
        this.noteId = "SN" + counter++;
        this.nurseId = nurseId;
        this.nurseName = nurseName;
        this.contactEmail = contactEmail;
        this.date = date;
        this.notes = notes;
        this.shift = "Day";
        this.createdDate = new Date();
        this.status = "Active";
        try {
            this.shiftDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            this.shiftDate = new Date(); // fallback to current
        }
    }

    // ---------------- Getters ----------------

    public String getNoteId() {
        return noteId;
    }

    public String getNurseId() {
        return nurseId;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDate() {
        return date;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
        this.date = formatDate(shiftDate);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ---------------- Status & Management ----------------

    public void updateNote(String nurseName, String contactEmail, String notes) {
        this.nurseName = nurseName;
        this.contactEmail = contactEmail;
        this.notes = notes;
        this.createdDate = new Date();
    }

    public void archiveNote() {
        this.status = "Archived";
    }

    public void markAsDraft() {
        this.status = "Draft";
    }

    public void activateNote() {
        this.status = "Active";
    }

    // ---------------- Helpers ----------------

    private String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    @Override
    public String toString() {
        return "Shift Note [" + noteId + "] - " + nurseName +
               " (" + shift + " shift) - " + status;
    }
}
