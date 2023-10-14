public class Patient extends User 
{
    private String healthCardNumber;

    public Patient(String firstname, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address String healthCardNumber)
    {
        super(firstname, lastname, emailAddress, accountPassword, phoneNumber, address);
        this.healthCardNumber = healthCardNumber;

    }




}