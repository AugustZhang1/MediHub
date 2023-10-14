package com.example.medibook.classes;

public class Doctor extends User
{

    private String employeeNumber;
    private String specialties;

    public Doctor(String firstName, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address, String employeeNumber, String specialties)
    {
        super(firstName, lastName, emailAddress, accountPassword, phoneNumber, address);
        this.employeeNumber = employeeNumber;
        this.specialties = specialties;
    }


}
