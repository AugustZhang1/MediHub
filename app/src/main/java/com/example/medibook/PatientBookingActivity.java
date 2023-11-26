package com.example.medibook;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PatientBookingActivity extends AppCompatActivity {

    String[] specialty = {"family medicine", "internal medicine", "pediatrics", "obstetrics", "gynecology"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_booking);

        autoCompleteTextView = findViewById(R.id.autoCompleteText);
        adapterItems = new ArrayAdapter<String>(this, R.layout.activity_patient_booking_dropdown, specialty);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(PatientBookingActivity.this,"Specialty"+item, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
