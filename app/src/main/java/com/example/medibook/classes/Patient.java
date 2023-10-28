package com.example.medibook.classes;

public class Patient extends User
{
    private String healthCardNumber;

    public Patient(String firstName, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address,String status,String healthCardNumber)
    {
        super(firstName, lastName, emailAddress, accountPassword, phoneNumber, address,status);
        this.healthCardNumber = healthCardNumber;

    }

    public String getHealthCardNumber() {
        return healthCardNumber;
    }


}