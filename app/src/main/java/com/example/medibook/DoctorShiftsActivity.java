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

    List<DoctorShift> doctorShiftList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_shift_day);

        DoctorShiftsList productsAdapter = new DoctorShiftsList(DoctorShiftsActivity.this, doctorShiftList);
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
        DoctorShift shift = new DoctorShift(editTextDate.getText().toString(), editTextStartTime.getText().toString(), editTextEndTime.getText().toString());
    }


}
