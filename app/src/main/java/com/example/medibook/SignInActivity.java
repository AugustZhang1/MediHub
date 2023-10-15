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
import com.example.medibook.classes.Patient;
import com.google.android.material.snackbar.Snackbar;


public class SignInActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private TextView txtEmail, txtPassword;

    private Button signIn,clickBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        createViews();


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSignIn(); // check correct format before sign in
            }
        });

        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });





    }

    public void createViews() {

        editEmail = findViewById(R.id.emailAddress);
        editPassword = findViewById(R.id.editTextTextPassword);

        txtEmail = findViewById(R.id.textWarnEmail);
        txtPassword = findViewById(R.id.textWarnPassword);

        signIn = findViewById(R.id.SignInButton);
        clickBack = findViewById(R.id.SignInButtonClickBackward);


    }


    public void initSignIn() {
        if(validateData() != null) {

            snackBar();



        }


    }


    public User validateData() {



        if ((editEmail.getText().toString().equals("") )|| (!editEmail.getText().toString().contains("@")) || (editPassword.getText().toString().equals(""))) {

            txtEmail.setVisibility(View.VISIBLE);
            txtPassword.setVisibility(View.VISIBLE);
            return null;
        }
        else{
            for (User i: MainActivity.userList){
                if ((i.getEmail().toString().equals(editEmail.getText().toString()))&&(i.getPassword().toString().equals(editPassword.getText().toString()))){
                    return i;
                }
            }
        }

        return null;
    }


    public void snackBar() {

        txtEmail.setVisibility(View.GONE);
        txtPassword.setVisibility(View.GONE);




        View rootLayout = findViewById(R.id.signInLayout);
        Snackbar.make(rootLayout, "Registered successfully", Snackbar.LENGTH_SHORT)
                .setAction("Dismiss", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(validateData().getClass() == Doctor.class){
                                    Intent intent = new Intent(SignInActivity.this, DoctorInterface.class);
                                    startActivity(intent);
                                }
                                else {

                                    Intent intent = new Intent(SignInActivity.this, PatientInterface.class);
                                    startActivity(intent);
                                }

                            }
                        }
                ).show();







    }



}


