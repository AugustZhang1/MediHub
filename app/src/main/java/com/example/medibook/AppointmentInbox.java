package com.example.medibook;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AppointmentInbox extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppointmentInboxAdapter adapter;
    private List<Appointment> appointmentList;
    private Button acceptAllBtn; // Button to accept all appointments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointmentInbox);

        recyclerView = findViewById(R.id.recyclerAppointmentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        acceptAllBtn = findViewById(R.id.appointmentAcceptAllBtn);

        appointmentList = new ArrayList<>();
        adapter = new AppointmentInboxAdapter(appointmentList, this);
        recyclerView.setAdapter(adapter);

        fetchAppointments();

        acceptAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptAllAppointments();
            }
        });
    }


    private void fetchAppointments() {
        appointmentsRef = FirebaseDatabase.getInstance().getReference("appointments");
        appointmentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                appointmentList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = snapshot.getValue(Appointment.class);
                    String status = snapshot.child("status").getValue(String.class);
                    if ("New".equals(status)) { //only add when status is new
                        appointmentList.add(appointment);
                    }


                }
                adapter.notifyDataSetChanged();

            }

            public void onCancelled(DatabaseError databaseError) {
                // Handle errors.
            }
        });
    }
    private void acceptAllAppointments() {
        DatabaseReference appointmentsRef = FirebaseDatabase.getInstance().getReference("appointments");
        appointmentsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String status = snapshot.child("status").getValue(String.class);
                    if (!"Rejected".equals(status) && !"Cancelled".equals(status)) {// Check if the current status is neither 'Rejected' nor 'Cancelled'
                        snapshot.getRef().child("status").setValue("Accepted");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }







}

