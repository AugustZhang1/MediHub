package com.example.medibook.classes;

public class Doctor extends User
{

    private String employeeNumber;
    private String specialties;

    public Doctor(String firstName, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address, boolean adminStatus, boolean userAuth ,String employeeNumber, String specialties)
    {
        super(firstName, lastName, emailAddress, accountPassword, phoneNumber, address,adminStatus,userAuth);
        this.employeeNumber = employeeNumber;
        this.specialties = specialties;
    }

    public String getEmployeeNumber(){
        return employeeNumber;
    }
    public String getSpecialties(){
        return specialties;
    }


}
