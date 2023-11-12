package com.example.medibook;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class AppointmentAcceptedListViewHolder extends RecyclerView.ViewHolder {
    RelativeLayout relativeLayout;
    TextView shiftIdView, startTimeView, endTimeView, patientUidView, statusView;
    Button cancelBtn;

    public AppointmentAcceptedListViewHolder(View itemView) {
        super(itemView);
        relativeLayout = itemView.findViewById(R.id.acceptedAppointment);
        shiftIdView = itemView.findViewById(R.id.acceptedShiftId);
        startTimeView = itemView.findViewById(R.id.acceptedStartTime);
        endTimeView = itemView.findViewById(R.id.acceptedEndTime);
        patientUidView = itemView.findViewById(R.id.acceptedPatientUid);
        statusView = itemView.findViewById(R.id.acceptedStatus);
        cancelBtn = itemView.findViewById(R.id.appointmentCancelBtn);

    }
}
