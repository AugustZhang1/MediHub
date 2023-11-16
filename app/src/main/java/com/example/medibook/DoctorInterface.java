package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class DoctorInterface extends AppCompatActivity {

    private Button logOffBtn;

    private Button viewShiftsBtn;

    private Button appointmentListBtn;

    private Button appointmentAcceptedListBtn;

    private ToggleButton autoAcceptButton;

    public static boolean autoAccept = false;

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
                Intent intent = new Intent(DoctorInterface.this, DoctorShiftsActivity.class);
                startActivity(intent);
            }
        });

        appointmentListBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(DoctorInterface.this, AppointmentInbox.class);
                startActivity(intent);
            }
        });

        appointmentAcceptedListBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(DoctorInterface.this, AppointmentAcceptedList.class);
                startActivity(intent);
            }
        });

        autoAcceptButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    autoAcceptButton.setText("On");
                    autoAccept = true;
                }
                else{
                    autoAcceptButton.setText("Off");
                    autoAccept = false;
                }
            }
        });

}



    public void createViews(
    ){
        logOffBtn = findViewById(R.id.logOutAsDoctor);
        viewShiftsBtn = findViewById(R.id.viewShiftsBtn);
        appointmentListBtn = findViewById(R.id.appointmentListButton);
        appointmentAcceptedListBtn = findViewById(R.id.appointmentAcceptedListButton);
        autoAcceptButton = findViewById(R.id.autoAcceptButton);

    }

}