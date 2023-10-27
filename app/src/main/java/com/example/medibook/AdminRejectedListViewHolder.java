package com.example.medibook;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class AdminRejectedListViewHolder extends RecyclerView.ViewHolder {

    TextView firstNameView,LastnameView,emailView,phoneNumberView,addressView,healthCardView;

    public AdminRejectedListViewHolder(View itemView){
        super(itemView);
        firstNameView = itemView.findViewById(R.id.rejectedUserFirstName);
        LastnameView = itemView.findViewById(R.id.rejectedUserLastName);
        emailView = itemView.findViewById(R.id.rejectedUserEmail);
        phoneNumberView = itemView.findViewById(R.id.rejectedUserPhoneNum);
        addressView = itemView.findViewById(R.id.rejectedUserAddress);
        healthCardView = itemView.findViewById(R.id.rejectedUserHealthCard);


    }
}
