package com.example.medibook;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ArrayAdapter;
import java.util.List;
import com.example.medibook.DoctorShift;
import com.example.medibook.R;

public class DoctorShiftsList extends ArrayAdapter<DoctorShift> {

    private Activity context;

    List<DoctorShift> doctorShift;

    public DoctorShiftsList(Activity context, List<DoctorShift> doctorShift){
        super(context, R.layout.activity_doctor_shift_day);
        this.context = context;
        this.doctorShift = doctorShift;
    }




}
