package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.medibook.classes.User;
import com.example.medibook.classes.Patient;
import com.google.android.material.snackbar.Snackbar;

public class PatientRegisterActivity extends AppCompatActivity {


    public static User patient;
    private EditText editFirstName, editLastName, editEmail, editPassword, editPhoneNumber, editAddress, editHealthCard;
    private TextView txtFirstName, txtLastName, txtEmail, txtPassword, txtPhoneNumber, txtAddress, txtHealthCard;

    private Button registerPatient,clickBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        createViews();


        registerPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister(); // check correct format before register
            }
        });

        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientRegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });





    }


    public void createViews() {
        editFirstName = findViewById(R.id.firstName);
        editLastName = findViewById(R.id.lastName);
        editEmail = findViewById(R.id.emailAddress);
        editPassword = findViewById(R.id.editTextTextPassword);
        editPhoneNumber = findViewById(R.id.phoneNumber);
        editAddress = findViewById(R.id.address);
        editHealthCard = findViewById(R.id.healthCard);

        txtFirstName = findViewById(R.id.textWarnFirstName);
        txtLastName = findViewById(R.id.textWarnLastName);
        txtEmail = findViewById(R.id.textWarnEmail);
        txtPassword = findViewById(R.id.textWarnPassword);
        txtPhoneNumber = findViewById(R.id.textWarnPhoneNumber);
        txtAddress = findViewById(R.id.textWarnAddress);
        txtHealthCard = findViewById(R.id.textWarnHealth);

        registerPatient = findViewById(R.id.registerPatientButton);
        clickBack = findViewById(R.id.registerPatientButtonClickBackward);


    }


    public void initRegister() {
        if(validateData()) {

            storeUser();


            snackBar();













        }


    }


    public boolean validateData() {

        if (editFirstName.getText().toString().equals("")) {
            txtFirstName.setVisibility(View.VISIBLE);
            return false;
        }

        if (editLastName.getText().toString().equals("")) {
            txtLastName.setVisibility(View.VISIBLE);
            return false;
        }

        if (editEmail.getText().toString().equals("") || !editEmail.getText().toString().contains("@")) {
            txtEmail.setVisibility(View.VISIBLE);
            return false;
        }
        if (editPassword.getText().toString().equals("")) {
            txtPassword.setVisibility(View.VISIBLE);
            return false;
        }
        if (editPhoneNumber.getText().toString().equals("")) {
            txtPhoneNumber.setVisibility(View.VISIBLE);
            return false;
        }
        if (editAddress.getText().toString().equals("")) {
            txtAddress.setVisibility(View.VISIBLE);
            return false;
        }

        if (editHealthCard.getText().toString().equals("")) {
            txtHealthCard.setVisibility(View.VISIBLE);
            return false;

        }
        return true;
    }


    public void snackBar() {
        txtFirstName.setVisibility(View.GONE);
        txtLastName.setVisibility(View.GONE);
        txtEmail.setVisibility(View.GONE);
        txtPassword.setVisibility(View.GONE);
        txtPhoneNumber.setVisibility(View.GONE);
        txtAddress.setVisibility(View.GONE);
        txtHealthCard.setVisibility(View.GONE);



        View rootLayout = findViewById(R.id.patientLayout);
        Snackbar.make(rootLayout, "Registered successfully", Snackbar.LENGTH_SHORT)
                .setAction("Dismiss", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(PatientRegisterActivity.this, PatientInterface.class);
                                startActivity(intent);

                            }
                        }
                ).show();







    }


    public void storeUser(){
        Patient patient = new Patient(editFirstName.getText().toString(),editLastName.getText().toString(),editEmail.getText().toString(), editPassword.getText().toString(),editPhoneNumber.getText().toString(),editAddress.getText().toString(),editHealthCard.getText().toString());


        MainActivity.userList.add(patient);//




    }
}

