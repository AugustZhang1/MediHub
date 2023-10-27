package com.example.medibook;
import android.widget.Button;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

class AdminInbox extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_admin_inbox);

                RecyclerView recyclerView = findViewById(R.id.recyclerUserInfo);

                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new AdminRejectedListAdapter(getApplicationContext(), MainActivity.userList));


        }
        




}
