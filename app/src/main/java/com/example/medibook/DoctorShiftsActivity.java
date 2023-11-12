package com.example.medibook;

import static com.example.medibook.MainActivity.mAuth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

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

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_shift_day);
        mAuth = FirebaseAuth.getInstance();
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
        productsAdapter = new DoctorShiftsList(DoctorShiftsActivity.this, doctorShiftList);
        listViewShifts.setAdapter(productsAdapter);
    }

    private void addShift() {
        Log.d("DoctorShiftsActivity", "addShift() called");
        mAuth.signInWithEmailAndPassword(AdminInbox.getInboxEmail(),AdminInbox.getInboxPassword());
        String date = editTextDate.getText().toString();
        String startTime = editTextStartTime.getText().toString();
        String endTime = editTextEndTime.getText().toString();

        Log.d("DoctorShiftsActivity", "Date: " + date + ", Start Time: " + startTime + ", End Time: " + endTime);

        // Check if the user is authenticated
        FirebaseUser current = mAuth.getCurrentUser();
        if (current == null) {
            // Handle the case where the user is not authenticated
            Log.d("DoctorShiftsActivity", "User not authenticated");
            Toast.makeText(this, "User not authenticated. Please sign in.", Toast.LENGTH_SHORT).show();
            // Redirect to the sign-in page or take appropriate action
            return;
        }

        if (isShiftConflict(date, startTime, endTime)) {
            Log.d("DoctorShiftsActivity", "Shift conflicts with existing shifts");
            Toast.makeText(this, "Shift conflicts with existing shifts", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("DoctorShiftsActivity", "Adding shift to the list");

            DoctorShift shift = new DoctorShift(date, startTime, endTime, current.getUid());
            doctorShiftList.add(shift);
            productsAdapter.notifyDataSetChanged();
            MainActivity.shiftRef.child(MainActivity.shiftRef.push().getKey()).setValue(shift);
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
