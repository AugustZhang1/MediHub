package com.example.medibook.classes;

public class Administrator extends User
{
    public Administrator(String firstName,  String lastName, String emailAddress, String accountPassword, String phoneNumber, String address, String status )
    {
        super(firstName, lastName, emailAddress, accountPassword, phoneNumber, address,status);
    }
}
