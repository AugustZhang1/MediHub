package com.example.medibook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AppointmentInboxAdapter extends RecyclerView.Adapter<AppointmentInboxViewHolder> {
    private List<Appointment> appointments;
    private Context context;

    public AppointmentInboxAdapter(List<Appointment> appointments, Context context) {
        this.appointments = appointments;
        this.context = context;
    }

    @NonNull
    @Override
    public AppointmentInboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppointmentInboxViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_recycler_appointmentinfo,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentInboxViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);

        holder.shiftIdView.setText(appointment.getDoctorShiftId());
        holder.startTimeView.setText(appointment.getStartTime());
        holder.endTimeView.setText(appointment.getEndTime());
        holder.patientUidView.setText(appointment.getPatientUid());
        if ("New".equals(appointment.getStatus())) {
            holder.statusView.setText("Not Accepted Yet"); //if status is new it will show not accepted yet
        } else {
            holder.statusView.setText(appointment.getStatus());
        }

        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAppointmentStatus(appointment.getPatientUid(), "Accepted");
            }
        });

        holder.rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAppointmentStatus(appointment.getPatientUid(), "Rejected");
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    private void updateAppointmentStatus(String patientUid, String newStatus) {
        DatabaseReference appointmentRef = FirebaseDatabase.getInstance().getReference("appointments").child(patientUid).child("status");
        appointmentRef.setValue(newStatus);


    }

}



