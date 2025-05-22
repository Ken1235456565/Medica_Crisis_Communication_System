package Model.WorkQueue;

import Model.Personnel.Employee;
import Model.Patient.Patient;
import java.util.Date;

/**
 * Appointment class for clinical scheduling
 */
public class Appointment extends WorkRequest { // Extends WorkRequest
    private String appointmentId;
    private Patient patient;
    private Employee doctor;
    private Date appointmentDate;
    private String appointmentTime;
    private int durationMinutes;
    private String appointmentType; // Regular, Follow-up, Emergency
    private String notes;
    private String location;

    private static int counter = 1;

    // Default constructor
    public Appointment() {
        this.appointmentId = generateAppointmentId();
        this.status = "Scheduled";
        this.durationMinutes = 30; // Default duration
    }

    // Constructor with basic info
    public Appointment(Patient patient, Employee doctor, Date appointmentDate, String appointmentTime) {
        this();
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;

        // Set WorkRequest properties
        this.setMessage("Appointment for " + patient.getName()); // Basic message
        this.setSender(null); // Who scheduled the appointment (e.g., receptionist)
        this.setReceiver(doctor);
    }

    // Constructor with detailed info
    public Appointment(Patient patient, Employee doctor,
                      Date appointmentDate, String appointmentTime,
                      int durationMinutes, String appointmentType,
                      String notes, String location) {
        this(patient, doctor, appointmentDate, appointmentTime); // Call basic constructor
        this.durationMinutes = durationMinutes;
        this.appointmentType = appointmentType;
        this.notes = notes;
        this.location = location;
    }

    // Generate unique appointment ID
    private String generateAppointmentId() {
        return "APT" + counter++;
    }

    // Getters and setters

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Employee getDoctor() {
        return doctor;
    }

    public void setDoctor(Employee doctor) {
        this.doctor = doctor;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Cancel appointment
    public void cancelAppointment(String cancellationReason) {
        this.setStatus("Cancelled");
        this.notes = (this.notes != null ? this.notes + "\n" : "") +
                     "Cancelled: " + cancellationReason;
        this.setResolveDate(new Date()); // Set resolve date on cancellation
    }

    // Mark as completed
    public void completeAppointment(String followUpNotes) {
        this.setStatus("Completed");
        this.notes = (this.notes != null ? this.notes + "\n" : "") +
                     "Follow-up: " + followUpNotes;
        this.setResolveDate(new Date()); // Set resolve date on completion
    }

    // Mark as no-show
    public void markNoShow() {
        this.setStatus("No-Show");
        this.setResolveDate(new Date()); // Set resolve date on no-show
    }

    // Reschedule appointment
    public void rescheduleAppointment(Date newDate, String newTime) {
        this.setAppointmentDate(newDate);
        this.setAppointmentTime(newTime);
        this.setStatus("Scheduled");
        this.notes = (this.notes != null ? this.notes + "\n" : "") +
                     "Rescheduled to " + newDate + " at " + newTime;
    }

    // Check if conflict with another appointment
    public boolean conflictsWith(Appointment other) {
        if (!appointmentDate.equals(other.getAppointmentDate())) {
            return false;
        }

        // Parse times (assuming format like "14:30")
        String[] thisTimeParts = appointmentTime.split(":");
        String[] otherTimeParts = other.getAppointmentTime().split(":");

        if (thisTimeParts.length != 2 || otherTimeParts.length != 2) {
            return false; // Invalid time format
        }

        int thisHour = Integer.parseInt(thisTimeParts[0]);
        int thisMinute = Integer.parseInt(thisTimeParts[1]);
        int otherHour = Integer.parseInt(otherTimeParts[0]);
        int otherMinute = Integer.parseInt(otherTimeParts[1]);

        // Convert to minutes since midnight
        int thisStartMinutes = thisHour * 60 + thisMinute;
        int thisEndMinutes = thisStartMinutes + this.durationMinutes;

        int otherStartMinutes = otherHour * 60 + otherMinute;
        int otherEndMinutes = otherStartMinutes + other.getDurationMinutes();

        // Check for overlap
        return (thisStartMinutes < otherEndMinutes && thisEndMinutes > otherStartMinutes);
    }

    @Override
    public String toString() {
        return appointmentId + ": " + (patient != null ? patient.getName() : "No Patient") +
               " with " + (doctor != null ? doctor.getName() : "No Doctor") +
               " on " + appointmentDate + " at " + appointmentTime +
               " [" + getStatus() + "]";  // Use getStatus() from WorkRequest
    }
}