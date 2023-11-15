package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorInterface extends AppCompatActivity {

    private Button logOffBtn;

    private Button viewShiftsBtn;

    private Button appointmentListBtn;

    private Button appointmentAcceptedListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_interface);
        createViews();

        logOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mAuth.signOut();
                Intent intent = new Intent(DoctorInterface.this,MainActivity.class);
                startActivity(intent);
            }
        });
        viewShiftsBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                MainActivity.mAuth.signOut();
                Intent intent = new Intent(DoctorInterface.this, DoctorShiftsActivity.class);
                startActivity(intent);
            }
        });

        appointmentListBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                MainActivity.mAuth.signOut();
                Intent intent = new Intent(DoctorInterface.this, AppointmentInbox.class);
                startActivity(intent);
            }
        });

        appointmentAcceptedListBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                MainActivity.mAuth.signOut();
                Intent intent = new Intent(DoctorInterface.this, AppointmentAcceptedList.class);
                startActivity(intent);
            }
        });



}



    public void createViews(
    ){
        logOffBtn = findViewById(R.id.logOutAsDoctor);
        viewShiftsBtn = findViewById(R.id.viewShiftsBtn);

        appointmentListBtn = findViewById(R.id.appointmentListButton);

        appointmentAcceptedListBtn = findViewById(R.id.appointmentAcceptedListButton);


    }

}