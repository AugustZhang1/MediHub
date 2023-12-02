//package com.example.medibook;
//
//import static com.example.medibook.MainActivity.shiftRef;
//
//import android.util.Log;
//import android.widget.Button;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//
//
//public class DoctorRemoveShifts extends AppCompatActivity {
//    public Button removeShiftsButton;
//
//    EditText editTextDate;
//    EditText editTextStartTime;
//    EditText editTextEndTime;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doctor_shift_signup);
//        Log.d("DoctorShiftsActivity", "logcat 1");
//
//
//        createViews();
//
//
//
//    }
//
//
//    public void createViews(){
//        removeShiftsButton = findViewById(R.id.buttonDeleteShift);
//        editTextDate = findViewById(R.id.editDate);
//        editTextEndTime = findViewById(R.id.editEndTime);
//        editTextStartTime = findViewById(R.id.editStartTime);
//        if (removeShiftsButton == null) {
//            Log.e("DoctorRemoveShifts", "removeShiftsButton is null");
//        }
//
//        removeShiftsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("DoctorShiftsActivity", "Deleting shift");
//
//                deleteShift();
//
//            }
//        });
//
//
//    }
//
//    private void deleteShift() {
//        String date = editTextDate.getText().toString();
//        String startTime = editTextStartTime.getText().toString();
//        String endTime = editTextEndTime.getText().toString();
//
//        DoctorShift shift = findShiftByDate(date, startTime, endTime);
//        if (shift != null) {
//            shiftRef.child(shift.getUid()).removeValue();
//
//            DoctorShiftsActivity.getArrayList().remove(shift);
//            DoctorShiftsActivity.getProductsAdapter().notifyDataSetChanged();
//
//
//
//        }
//
//    }
//    public DoctorShift findShiftByDate(String date, String startTime, String endTime){
//        for (DoctorShift e : DoctorShiftsActivity.getArrayList()){
//            if (e.getDate().equals(date) && e.getStartTime().equals(startTime) && e.getEndTime().equals(endTime)){
//                return e;
//            }
//        }
//        return null;
//    }
//}
