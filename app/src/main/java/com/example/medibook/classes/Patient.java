package com.example.medibook.classes;

public class Patient extends User
{
    private String healthCardNumber;

    public Patient(String firstName, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address, boolean adminStatus, boolean userAuth, String healthCardNumber)
    {
        super(firstName, lastName, emailAddress, accountPassword, phoneNumber, address, adminStatus,userAuth);
        this.healthCardNumber = healthCardNumber;

    }

    public String getHealthCardNumber() {
        return healthCardNumber;
    }


}