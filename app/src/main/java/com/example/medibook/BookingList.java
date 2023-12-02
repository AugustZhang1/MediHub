package com.example.medibook;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BookingList extends ArrayAdapter<Booking> {

    private Activity context;
    private List<Booking> bookings;

    public BookingList(Activity context, List<Booking> bookings) {
        super(context, R.layout.activity_patient_booking_text, bookings);
        this.context = context;
        this.bookings = bookings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.activity_patient_booking_text, null);
        }

        // Assuming you have TextViews in your list item layout
        TextView dateTextView = view.findViewById(R.id.editDateB);
        TextView startTimeTextView = view.findViewById(R.id.editStartTimeB);
        TextView endTimeTextView = view.findViewById(R.id.editEndTimeB);
        Button bookItem = view.findViewById(R.id.buttonSelectBooking);

        // Get the DoctorShift object for this position
        Booking bookingItem = bookings.get(position);

        bookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = dateTextView.getText().toString();
                String startTime = startTimeTextView.getText().toString();
                String endTime = endTimeTextView.getText().toString();
                Log.d("DoctorShiftsActivity", "Booking Appointment");
                Appointment a = new Appointment();



            }
        });

        // Set the data to the TextViews
        dateTextView.setText(bookingItem.getDate());
        startTimeTextView.setText(bookingItem.getStartTime());
        endTimeTextView.setText(bookingItem.getEndTime());

        return view;
    }
}
