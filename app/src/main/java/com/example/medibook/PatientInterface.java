package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class PatientInterface extends AppCompatActivity {
    private Button logOffBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_interface);


        createViews();

        logOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientInterface.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }



    public void createViews(
    ){
        logOffBtn = findViewById(R.id.logOutAsPatient);


    }
}