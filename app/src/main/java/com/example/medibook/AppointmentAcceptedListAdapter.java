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

public class AppointmentAcceptedListAdapter extends RecyclerView.Adapter<AppointmentAcceptedListViewHolder> {
    private List<Appointment> appointments;
    private Context context;

    public AppointmentAcceptedListAdapter(List<Appointment> appointments, Context context) {
        this.appointments = appointments;
        this.context = context;
    }

    @NonNull
    @Override
    public AppointmentAcceptedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppointmentAcceptedListViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_appointment_accepted_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAcceptedListViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);

        holder.shiftIdView.setText(appointment.getDoctorShiftId());
        holder.startTimeView.setText(appointment.getStartTime());
        holder.endTimeView.setText(appointment.getEndTime());
        holder.patientUidView.setText(appointment.getPatientUid());
        if ("new".equals(appointment.getStatus())) {
            holder.statusView.setText("Not Accepted Yet"); //if status is new it will show not accepted yet
        } else {
            holder.statusView.setText(appointment.getStatus());
        }

        holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAppointmentStatus(appointment.getPatientUid(), "Canceled");
            }
        });

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    private void updateAppointmentStatus(String patientUid, String newStatus) {
        DatabaseReference appointmentRef = FirebaseDatabase.getInstance().getReference("appointments").child(patientUid);
        appointmentRef.child("status").setValue(newStatus);

    }
}

