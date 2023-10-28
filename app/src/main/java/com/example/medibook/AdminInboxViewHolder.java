package com.example.medibook;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class AdminInboxViewHolder extends RecyclerView.ViewHolder {
    TextView firstNameView, lastNameView, emailView, phoneNumberView, addressView, healthCardView;

    public AdminInboxViewHolder(View itemView){
        super(itemView);
        firstNameView = itemView.findViewById(R.id.inboxUserFirstName);
        lastNameView = itemView.findViewById(R.id.inboxUserLastName);
        emailView = itemView.findViewById(R.id.inboxUserEmail);
        phoneNumberView = itemView.findViewById(R.id.inboxUserPhoneNum);
        addressView = itemView.findViewById(R.id.inboxUserAddress);
        healthCardView = itemView.findViewById(R.id.inboxUserHealthCard);
    }

}
