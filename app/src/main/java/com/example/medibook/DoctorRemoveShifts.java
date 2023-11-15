package com.example.medibook;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class DoctorRemoveShifts extends AppCompatActivity {
    public Button removeShiftsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_remove_shifts);


        createViews();

    }


    public void createViews(){
        removeShiftsButton = findViewById(R.id.buttonDeleteProduct);


    }
}
