package com.example.medibook;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DoctorShiftsList extends ArrayAdapter<DoctorShift> {

    private Activity context;
    private List<DoctorShift> doctorShift;

    public DoctorShiftsList(Activity context, List<DoctorShift> doctorShift) {
        super(context, R.layout.activity_doctor_shift_signup, doctorShift);
        this.context = context;
        this.doctorShift = doctorShift;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.activity_doctor_shift_signup, null);
        }

        // Assuming you have TextViews in your list item layout
        TextView dateTextView = view.findViewById(R.id.editDate);
        TextView startTimeTextView = view.findViewById(R.id.editStartTime);
        TextView endTimeTextView = view.findViewById(R.id.editEndTime);

        // Get the DoctorShift object for this position
        DoctorShift doctorShiftItem = doctorShift.get(position);

        // Set the data to the TextViews
        dateTextView.setText(doctorShiftItem.getDate());
        startTimeTextView.setText(doctorShiftItem.getStartTime());
        endTimeTextView.setText(doctorShiftItem.getEndTime());

        return view;
    }
}
