package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.medibook.MainActivity.shiftRef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.time.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.TimeZone;

public class PatientPastAppointmentActivity extends AppCompatActivity {
    ListView listViewPastAppointment;

    List<Appointment> appointmentList;

    PatientPastAppointmentList pastAdapter;

    Button patientPastAppointmentButton1;

    Button buttonRate;

    FirebaseUser current = MainActivity.mAuth.getCurrentUser();


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_past_appointment);

        MainActivity.appointmentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = snapshot.getValue(Appointment.class);
                    // Check if the appointment is in the past
                    if (DatePassed(appointment.getDate(), appointment.getStartTime()) && current.getUid().equals(snapshot.child("patientUid").getValue(String.class))) {
                        appointmentList.add(appointment);
                    }
                }


                pastAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        patientPastAppointmentButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientPastAppointmentActivity.this, PatientInterface.class);
                startActivity(intent);

            }
        });

        createViews();



    }

    private void createViews(){
        listViewPastAppointment = findViewById(R.id.listViewPast);
        patientPastAppointmentButton1 = findViewById(R.id.patientPastAppointmentButton1);
        buttonRate = findViewById(R.id.buttonRate);
        appointmentList = new ArrayList<>();
        pastAdapter = new PatientPastAppointmentList(PatientPastAppointmentActivity.this,appointmentList);
        listViewPastAppointment.setAdapter(pastAdapter);






    }

    private Boolean DatePassed(String date, String startTime){
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4)) - 1;
        int year = Integer.parseInt(date.substring(4, 8));

        Calendar currentDate = Calendar.getInstance(TimeZone.getTimeZone("America/Toronto")); // Adjust to your specific time zone
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);

        Calendar enteredDate = new GregorianCalendar(TimeZone.getTimeZone("America/Toronto")); // Adjust to your specific time zone
        enteredDate.set(Calendar.YEAR, year);
        enteredDate.set(Calendar.MONTH, month);
        enteredDate.set(Calendar.DAY_OF_MONTH, day);
        enteredDate.set(Calendar.HOUR_OF_DAY, 0);
        enteredDate.set(Calendar.MINUTE, 0);
        enteredDate.set(Calendar.SECOND, 0);
        enteredDate.set(Calendar.MILLISECOND, 0);

        if (enteredDate.after(currentDate) || enteredDate.equals(currentDate)) {
            return true;
        } else {

            return false;
        }
    }


}
