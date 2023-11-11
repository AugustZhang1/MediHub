package com.example.medibook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DoctorShiftsActivity extends AppCompatActivity {
    EditText editTextDate;
    EditText editTextStartTime;

    EditText editTextEndTime;
    Button buttonAddShifts;
    ListView listViewShifts;

    DoctorShiftsList productsAdapter;

    List<DoctorShift> doctorShiftList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_shift_day);

        productsAdapter = new DoctorShiftsList(DoctorShiftsActivity.this, doctorShiftList);
        listViewShifts.setAdapter(productsAdapter);


        createViews();


        buttonAddShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addShift();
            }
        });




    }

    private void createViews() {
        editTextDate = findViewById(R.id.editDate);
        editTextEndTime = findViewById(R.id.editEndTime);
        editTextStartTime = findViewById(R.id.editStartTime);
        buttonAddShifts = findViewById(R.id.addButton);
        listViewShifts = findViewById(R.id.listViewProducts);

        doctorShiftList = new ArrayList<>();
    }

    private void addShift() {
        String date = editTextDate.getText().toString();
        String startTime = editTextStartTime.getText().toString();
        String endTime = editTextEndTime.getText().toString();

        // Check for conflicts before adding the shift
        if (isShiftConflict(date, startTime, endTime)) {
            // Display a message indicating the conflict
            Toast.makeText(this, "Shift conflicts with existing shifts", Toast.LENGTH_SHORT).show();
        } else {
            // If no conflict, add the shift to the list
            DoctorShift shift = new DoctorShift(date, startTime, endTime);
            doctorShiftList.add(shift);

            // Notify the adapter that the data set has changed
            productsAdapter.notifyDataSetChanged();
        }
    }

    // Check for shift conflicts
    private boolean isShiftConflict(String newDate, String newStartTime, String newEndTime) {
        for (DoctorShift existingShift : doctorShiftList) { // goes through every single date inputted so far
            String existingDate = existingShift.getDate();
            String existingStartTime = existingShift.getStartTime();
            String existingEndTime = existingShift.getEndTime();

            // Check for conflicts based on your business logic
            if (existingDate.equals(newDate) && doTimeRangesOverlap(existingStartTime, existingEndTime, newStartTime, newEndTime)) {
                return true;  // conflict found
            }
        }
        return false;  // No conflict found, input shift
    }

    // Check if two time ranges overlap
    private boolean doTimeRangesOverlap(String start1, String end1, String start2, String end2) {

        return !((end1.compareTo(start2) <= 0) || (start1.compareTo(end2) >= 0)); // if end time is smaller than start time or equal then false because of conflict
    }




}



