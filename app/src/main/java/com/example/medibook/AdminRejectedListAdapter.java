package com.example.medibook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medibook.classes.User;

import java.util.List;

public class AdminRejectedListAdapter extends RecyclerView.Adapter<AdminRejectedListViewHolder> {

    Context context;
    List<User> userList1;


    public AdminRejectedListAdapter(Context context, List<User> userList1){
        this.context = context;
        this.userList1 = userList1;
    }
    @NonNull
    @Override
    public AdminRejectedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminRejectedListViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_recycler_rejected_userinfo,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminRejectedListViewHolder holder, int position) {
        holder.firstNameView.setText(userList1.get(position).getFirstName());
        holder.LastnameView.setText(userList1.get(position).getLastName());
        holder.emailView.setText(userList1.get(position).getEmail());
        holder.phoneNumberView.setText(userList1.get(position).getPhoneNumber());
        holder.addressView.setText(userList1.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return userList1.size();
    }
}
