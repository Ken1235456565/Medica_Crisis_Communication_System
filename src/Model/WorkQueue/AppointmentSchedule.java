/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.WorkQueue;


import Model.Employee.Employee;
import Model.Patient.Patient;
import Model.WorkQueue.Appointment;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentSchedule {

    private Map<Date, List<Appointment>> scheduleMap;

    public AppointmentSchedule() {
        this.scheduleMap = new HashMap<>();
    }

    // Getters and setters
    public Map<Date, List<Appointment>> getScheduleMap() {
        return scheduleMap;
    }

    public void setScheduleMap(Map<Date, List<Appointment>> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

    // Schedule appointment
    public boolean scheduleAppointment(Appointment appointment) {
        Date date = appointment.getAppointmentDate();
        List<Appointment> appointmentsOnDate = scheduleMap.getOrDefault(date, new ArrayList<>());

        // Check for conflicts
        for (Appointment existingAppointment : appointmentsOnDate) {
            if (existingAppointment.conflictsWith(appointment)) {
                return false; // Conflict found
            }
        }

        appointmentsOnDate.add(appointment);
        scheduleMap.put(date, appointmentsOnDate);
        return true;
    }

    // Cancel appointment
    public boolean cancelAppointment(Appointment appointment, String reason) {
        Date date = appointment.getAppointmentDate();
        List<Appointment> appointmentsOnDate = scheduleMap.get(date);

        if (appointmentsOnDate != null && appointmentsOnDate.remove(appointment)) {
            appointment.cancelAppointment(reason);
            return true;
        }
        return false;
    }

    // Find appointments for a patient
    public List<Appointment> findPatientAppointments(Patient patient) {
        List<Appointment> results = new ArrayList<>();
        for (List<Appointment> appointmentsOnDate : scheduleMap.values()) {
            for (Appointment appointment : appointmentsOnDate) {
                if (appointment.getPatient() == patient) {
                    results.add(appointment);
                }
            }
        }
        return results;
    }

    // Find appointments for a doctor
    public List<Appointment> findDoctorAppointments(Employee doctor) {
        List<Appointment> results = new ArrayList<>();
        for (List<Appointment> appointmentsOnDate : scheduleMap.values()) {
            for (Appointment appointment : appointmentsOnDate) {
                if (appointment.getDoctor() == doctor) {
                    results.add(appointment);
                }
            }
        }
        return results;
    }
}