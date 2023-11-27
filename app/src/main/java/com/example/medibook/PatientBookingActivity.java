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
                displayInformation(item);
            }
        });
    }

//    doctor need specialty store in the shifts in firebase, need mauth to find the registered doctor then get the specialty----

    private void displayInformation(String selectedItem) {
        // Use a switch statement or if-else conditions to handle different cases
        switch (selectedItem) {
            case "family medicine":
                // Display information for family medicine
                Toast.makeText(PatientBookingActivity.this, "Selected: Family Medicine", Toast.LENGTH_SHORT).show();
                break;
            case "internal medicine":
                // Display information for internal medicine
                Toast.makeText(PatientBookingActivity.this, "Selected: Internal Medicine", Toast.LENGTH_SHORT).show();
                break;
            case "pediatrics":
                // Display information for pediatrics
                Toast.makeText(PatientBookingActivity.this, "Selected: Pediatrics", Toast.LENGTH_SHORT).show();
                break;
            case "obstetrics":
                // Display information for obstetrics
                Toast.makeText(PatientBookingActivity.this, "Selected: Obstetrics", Toast.LENGTH_SHORT).show();
                break;
            case "gynecology":
                // Display information for gynecology
                Toast.makeText(PatientBookingActivity.this, "Selected: Gynecology", Toast.LENGTH_SHORT).show();
                break;
            default:
                // Handle the default case or unknown specialty
                Toast.makeText(PatientBookingActivity.this, "Unknown Specialty", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
