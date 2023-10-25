package com.example.medibook.classes;

public class Administrator extends User
{

    private boolean adminStatus;
    private boolean userAuth;
    public Administrator(String firstName,  String lastName, String emailAddress, String accountPassword, String phoneNumber, String address, boolean adminStatus, boolean userAuth)
    {
        super(firstName, lastName, emailAddress, accountPassword, phoneNumber, address);
        this.adminStatus = adminStatus;
        this.userAuth = userAuth;
    }

    public boolean getAdminStatus(){
        return adminStatus;
    }

    public boolean getUserAuth(){
        return userAuth;
    }

}
