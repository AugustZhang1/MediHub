package com.example.medibook;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class AppointmentInboxViewHolder extends RecyclerView.ViewHolder {
    RelativeLayout relativeLayout;
    TextView shiftIdView, startTimeView, endTimeView, patientUidView,statusView;
    Button acceptBtn, rejectBtn;

    public AppointmentInboxViewHolder(View itemView) {
        super(itemView);
        relativeLayout = itemView.findViewById(R.id.appointmentInbox);
        shiftIdView = itemView.findViewById(R.id.appointmentShiftId);
        startTimeView = itemView.findViewById(R.id.appointmentStartTime);
        endTimeView = itemView.findViewById(R.id.appointmentEndTime);
        patientUidView = itemView.findViewById(R.id.appointmentPatientUid);
        statusView = itemView.findViewById(R.id.appointmentStatus);
        acceptBtn = itemView.findViewById(R.id.appointmentAcceptBtn);
        rejectBtn = itemView.findViewById(R.id.appointmentRejectBtn);



    }
}
