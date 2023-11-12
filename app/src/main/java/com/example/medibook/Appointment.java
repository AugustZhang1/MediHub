package com.example.medibook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Appointment {
    private String doctorShiftId;
    private String patientUid;
    private Date startTime;
    private Date endTime;
    private String status; //"New", "Accepted", "Rejected", "Cancelled"
    private static final String DATE_FORMAT = "dd/mm/yyyy 'at' hh:mm:ss";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public Appointment() {

    }

    public Appointment(String appointmentId, String patientId, Date startTime, Date endTime, String status) {
        this.doctorShiftId = appointmentId;
        this.patientUid = patientUid;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getters and Setters
    public String getDoctorShiftId() {
        return doctorShiftId;
    }

    public void setDoctorShiftId(String appointmentId) {
        this.doctorShiftId = appointmentId;
    }

    public String getPatientUid() {
        return patientUid;
    }

    public void setPatientUid(String patientId) {
        this.patientUid = patientUid;
    }
    private String formatDateTime(Date date){
        if (date == null){return "Not set";}
        return dateFormat.format(date);
    }

    public String getStartTime() {
        return formatDateTime(this.startTime);
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return formatDateTime(this.endTime);
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
