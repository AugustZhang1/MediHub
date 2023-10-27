package com.example.medibook;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.medibook.classes.User;
public class AdminInboxAdapter extends RecyclerView.Adapter<AdminInboxViewHolder> {
    Context context;
    List<User> userList2;

    public AdminInboxAdapter(Context context, List<User> userList2){
        this.context = context;
        this.userList2 = userList2;
    }

    @NonNull
    @Override
    public AdminInboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new AdminInboxViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_recycler_userinfo, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdminInboxViewHolder holder, int position) {
        holder.firstNameView.setText(userList2.get(position).getFirstName());
        holder.lastNameView.setText(userList2.get(position).getLastName());
        holder.emailView.setText(userList2.get(position).getEmail());
        holder.phoneNumberView.setText(userList2.get(position).getPhoneNumber());
        holder.addressView.setText(userList2.get(position).getAddress());

    }



    @Override
    public int getItemCount(){
        return userList2.size();
    }





}
