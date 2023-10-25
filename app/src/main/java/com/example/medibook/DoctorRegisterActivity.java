package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.medibook.classes.Doctor;
import com.example.medibook.classes.User;
import com.google.android.material.snackbar.Snackbar;

public class DoctorRegisterActivity extends AppCompatActivity {
    public static User Doctor;
    private EditText editFirstName, editLastName, editEmail, editPassword, editPhoneNumber, editAddress, editHealthEmployeeNumber, editSpecialties;
    private TextView txtFirstName, txtLastName, txtEmail, txtPassword, txtPhoneNumber, txtAddress, txtEmployeeNumber, txtSpecialties;

    private Button registerDoctor,clickBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);


        createViews();


        registerDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister(); // check correct format before register
            }
        });




        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorRegisterActivity.this, MainActivity.class);
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
        editHealthEmployeeNumber = findViewById(R.id.employeeNumber);
        editSpecialties = findViewById(R.id.specialties);

        txtFirstName = findViewById(R.id.textWarnFirstName);
        txtLastName = findViewById(R.id.textWarnLastName);
        txtEmail = findViewById(R.id.textWarnEmail);
        txtPassword = findViewById(R.id.textWarnPassword);
        txtPhoneNumber = findViewById(R.id.textWarnPhoneNumber);
        txtAddress = findViewById(R.id.textWarnAddress);
        txtEmployeeNumber = findViewById(R.id.textWarnEmployeeNumber);
        txtSpecialties = findViewById(R.id.textWarnSpecialties);


        registerDoctor = findViewById(R.id.registerPatientButton);
        clickBack = findViewById(R.id.registerDoctorButtonClickBackward);


    }


    public void initRegister() {
        if (validateData()) {

            storeUser();


            snackBar();


        }


    }


    public boolean validateData() {
        boolean val = true;
        if (editFirstName.getText().toString().equals("")) {
            txtFirstName.setVisibility(View.VISIBLE);
            val = false;
        }
        else
            txtFirstName.setVisibility(View.GONE);

        if (editLastName.getText().toString().equals("")) {
            txtLastName.setVisibility(View.VISIBLE);
            val = false;
        }
        else
            txtLastName.setVisibility(View.GONE);

        if (editEmail.getText().toString().equals("") || !editEmail.getText().toString().contains("@")) {
            txtEmail.setVisibility(View.VISIBLE);
            val = false;
        }
        else
            txtEmail.setVisibility(View.GONE);

        if (editPassword.getText().toString().equals("")) {
            txtPassword.setVisibility(View.VISIBLE);
            val = false;
        }
        else
            txtPassword.setVisibility(View.GONE);

        if (editPhoneNumber.getText().toString().equals("")) {
            txtPhoneNumber.setVisibility(View.VISIBLE);
            val = false;
        }
        else
            txtPhoneNumber.setVisibility(View.GONE);

        if (editAddress.getText().toString().equals("")) {
            txtAddress.setVisibility(View.VISIBLE);
            val = false;
        }
        else
            txtAddress.setVisibility(View.GONE);

        if (editHealthEmployeeNumber.getText().toString().equals("")) {
            txtEmployeeNumber.setVisibility(View.VISIBLE);
            val = false;
        }
        else
            txtEmployeeNumber.setVisibility(View.GONE);

        if (editSpecialties.getText().toString().equals("")) {
            txtSpecialties.setVisibility(View.VISIBLE);
            val = false;
        }
        else
            txtSpecialties.setVisibility(View.GONE);

        return val;
    }


    public void snackBar() {
        txtFirstName.setVisibility(View.GONE);
        txtLastName.setVisibility(View.GONE);
        txtEmail.setVisibility(View.GONE);
        txtPassword.setVisibility(View.GONE);
        txtPhoneNumber.setVisibility(View.GONE);
        txtAddress.setVisibility(View.GONE);
        txtEmployeeNumber.setVisibility(View.GONE);
        txtSpecialties.setVisibility(View.GONE);


        View rootLayout = findViewById(R.id.patientLayout);
        Snackbar.make(rootLayout, "Registered successfully", Snackbar.LENGTH_SHORT).show();
        Intent intent = new Intent(DoctorRegisterActivity.this, DoctorInterface.class);
        startActivity(intent);
    }


    public void storeUser() {
        Doctor doctor = new Doctor(editFirstName.getText().toString(), editLastName.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString(), editPhoneNumber.getText().toString(), editAddress.getText().toString(), true,false,editHealthEmployeeNumber.getText().toString(),editSpecialties.getText().toString());


        MainActivity.userList.add(doctor);
    }
}