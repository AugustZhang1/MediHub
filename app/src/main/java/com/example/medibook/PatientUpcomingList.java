package com.example.medibook;

import static com.example.medibook.MainActivity.appointmentRef;
import static com.example.medibook.MainActivity.shiftRef;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PatientUpcomingList extends ArrayAdapter<Appointment> {

    private Activity context;
    private List<Appointment> appointment;

    public PatientUpcomingList(Activity context, List<Appointment> appointment) {
        super(context, R.layout.activity_patient_upcoming_text, appointment);
        this.context = context;
        this.appointment = appointment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.activity_patient_upcoming_text, null);
        }

        // Assuming you have TextViews in your list item layout
        TextView dateTextView = view.findViewById(R.id.editDate2);
        TextView startTimeTextView = view.findViewById(R.id.editStartTime2);
        TextView endTimeTextView = view.findViewById(R.id.editEndTime2);
        Button cancel = view.findViewById(R.id.buttonCancelBooking);

        // Get the DoctorShift object for this position
        Appointment appointmentItem = appointment.get(position);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = dateTextView.getText().toString();
                String startTime = startTimeTextView.getText().toString();
                String endTime = endTimeTextView.getText().toString();

                Appointment appointment1 = null;
                for (Appointment e : appointment){
                    if (e.getDate().equals(date) && e.getStartTime().equals(startTime) && e.getEndTime().equals(endTime)){
                        appointment1 = e;

                    }
                }



                if (appointment1 != null) {
                    Appointment finalAppointment = appointment1;
                    shiftRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                                if (productSnapshot.exists() && productSnapshot.child("uid").getValue(String.class).equals(finalAppointment.getUid())) {
                                    productSnapshot.getRef().child("status").setValue("new");


                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle any errors here
                        }
                    });
                    
                    appointmentRef.child(appointment1.getUid()).removeValue();
                    Log.d("DoctorShiftsActivity", "Deleting shift #2");
                    appointment.remove(appointment1);
                    notifyDataSetChanged();



                }
            }
        });



        // Set the data to the TextViews
        dateTextView.setText(appointmentItem.getDate());
        startTimeTextView.setText(appointmentItem.getStartTime());
        endTimeTextView.setText(appointmentItem.getEndTime());




        return view;


    }


}
