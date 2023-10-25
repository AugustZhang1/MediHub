package com.example.medibook.classes;

public class Administrator extends User
{


    public Administrator(String firstName,  String lastName, String emailAddress, String accountPassword, String phoneNumber, String address, boolean adminStatus, boolean userAuth)
    {
        super(firstName, lastName, emailAddress, accountPassword, phoneNumber, address, adminStatus,userAuth);

    }



}
