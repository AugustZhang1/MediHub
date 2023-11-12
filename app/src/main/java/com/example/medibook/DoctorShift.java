package com.example.medibook;

public class DoctorShift {
    private String year;
    private String month;
    private String dayOfMonth;

    private String doctorID;

    public DoctorShift() {

    }
    public DoctorShift(String year,String month,String dayOfMonth,String doctorID) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.doctorID = doctorID;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public String getDoctorID() { return doctorID; }

    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
}
