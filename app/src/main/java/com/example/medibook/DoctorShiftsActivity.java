package com.example.medibook;

import static com.example.medibook.MainActivity.mAuth;

import static com.example.medibook.MainActivity.shiftRef;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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

    // Use the same mAuth instance from MainActivity
    private FirebaseDatabase database = MainActivity.registrationRef.getDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_shift_day);

        shiftRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                doctorShiftList.clear();

                if (snapshot.exists()) {
                    for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                        DoctorShift shift = productSnapshot.getValue(DoctorShift.class);
                        shift.setId(productSnapshot.getKey());
                        doctorShiftList.add(shift);
                    }
                }
                productsAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle any errors here
            }
        });

        createViews();

        buttonAddShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doctorShiftList.add(new DoctorShift("2023-11-13", "09:00", "17:00"));
                productsAdapter.notifyDataSetChanged();
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
        productsAdapter = new DoctorShiftsList(DoctorShiftsActivity.this, doctorShiftList);
        listViewShifts.setAdapter(productsAdapter);
    }

    private void addShift() {

        Log.d("DoctorShiftsActivity", "addShift() called");

        String date = editTextDate.getText().toString();
        String startTime = editTextStartTime.getText().toString();
        String endTime = editTextEndTime.getText().toString();

        Log.d("DoctorShiftsActivity", "Date: " + date + ", Start Time: " + startTime + ", End Time: " + endTime);



        if (isShiftConflict(date, startTime, endTime)) {
            Log.d("DoctorShiftsActivity", "Shift conflicts with existing shifts");
            Toast.makeText(this, "Shift conflicts with existing shifts", Toast.LENGTH_LONG).show();
        } else {
            Log.d("DoctorShiftsActivity", "Adding shift to the list");

            DoctorShift shift = new DoctorShift(date, startTime, endTime);
            MainActivity.shiftRef.child(MainActivity.shiftRef.push().getKey()).setValue(shift);
            int sizeBefore = doctorShiftList.size();
            doctorShiftList.add(shift);
            productsAdapter.notifyDataSetChanged();
            int sizeAfter = doctorShiftList.size();
            Log.d("DoctorShiftsActivity", "Size before: " + sizeBefore + ", Size after: " + sizeAfter);
        }
    }

    private boolean isShiftConflict(String newDate, String newStartTime, String newEndTime) {
        DoctorShift newShift = new DoctorShift(newDate, newStartTime, newEndTime);
        for (DoctorShift existingShift : doctorShiftList) {
            if (existingShift.equals(newShift) && doTimeRangesOverlap(existingShift.getStartTime(), existingShift.getEndTime(), newStartTime, newEndTime) && Integer.parseInt(newEndTime) - Integer.parseInt(newStartTime) < 30) {
                return true;  // conflict found
            }
        }
        return false;  // No conflict found, input shift
    }

    private boolean doTimeRangesOverlap(String start1, String end1, String start2, String end2) {
        Log.d("DoctorShiftsActivity", "Comparing time ranges: " + start1 + " - " + end1 + " with " + start2 + " - " + end2);

        boolean overlap = !((end1.compareTo(start2) <= 0) || (start1.compareTo(end2) >= 0));

        Log.d("DoctorShiftsActivity", "Overlap result: " + overlap);

        return overlap;
    }
}
