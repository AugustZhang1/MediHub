package com.example.medibook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.medibook.classes.Administrator;
import com.example.medibook.classes.Doctor;
import com.example.medibook.classes.User;
import com.example.medibook.classes.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SignInActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private TextView txtEmail, txtPassword;
    private Button signIn,clickBack;
    private FirebaseAuth mAuth;



    private Administrator admin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();


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

        if(validateData()) {

            txtEmail.setVisibility(View.GONE);
            txtPassword.setVisibility(View.GONE);
            View rootLayout = findViewById(R.id.signInLayout);
            Snackbar.make(rootLayout, "Logged in successfully", Snackbar.LENGTH_SHORT).show();
            FirebaseUser current = mAuth.getCurrentUser();
            MainActivity.userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists() && current != null) {
                        if (dataSnapshot.hasChild(current.getUid())) {

                            String specialties = dataSnapshot.child(current.getUid()).child("specialties").getValue(String.class);
                            String health = dataSnapshot.child(current.getUid()).child("healthCardNumber").getValue(String.class);
                            Intent intent;
                            if (specialties != null && health == null) {
                                intent = new Intent(SignInActivity.this, DoctorInterface.class);
                            } else if (specialties == null && health != null) {
                                intent = new Intent(SignInActivity.this, PatientInterface.class);
                            } else {
                                intent = new Intent(SignInActivity.this, AdministratorInterface.class);
                            }
                            startActivity(intent);
                        }
                        else { //stuck here
                            MainActivity.registrationRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot2) {
                                    if ((dataSnapshot2.exists()) && (dataSnapshot2.hasChild(current.getUid()))) {
                                        String status = dataSnapshot2.child(current.getUid()).child("status").getValue(String.class);
                                        Intent intent = null;
                                        if (status.equals("pending") ) {
                                            intent = new Intent(SignInActivity.this, UserPendingInterface.class);
                                        } 
                                        else if(status.equals("rejected")) {
                                            intent = new Intent(SignInActivity.this, UserRejectedInterface.class);
                                        }
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Snackbar.make(rootLayout, "Sign in failed", Snackbar.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                    else {
                        Snackbar.make(rootLayout, "Sign in failed", Snackbar.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar.make(rootLayout, "Sign in failed", Snackbar.LENGTH_SHORT).show();
                }

            });

        }

    }


    public boolean validateData() {

        txtEmail.setVisibility(View.GONE);
        txtPassword.setVisibility(View.GONE);

        if ((editEmail.getText().toString().equals("") )|| (!editEmail.getText().toString().contains("@"))) {

            txtEmail.setText("Please enter your email");
            txtEmail.setVisibility(View.VISIBLE);
            if (editPassword.getText().toString().equals(""))
                txtPassword.setVisibility(View.VISIBLE);
            return false;
        } else if (editPassword.getText().toString().equals("")) { //second password check to make the UI more smooth
            txtPassword.setVisibility(View.VISIBLE);
            return false;
        }
        mAuth.signInWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString());
        FirebaseUser current = mAuth.getCurrentUser();
        if(current != null)
            return true;
        txtEmail.setText("Email or Password is incorrect");
        txtEmail.setVisibility(View.VISIBLE);
        return false;
    }


}


