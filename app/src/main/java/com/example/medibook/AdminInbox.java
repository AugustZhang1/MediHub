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

import com.example.medibook.classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

class AdminInbox extends AppCompatActivity {

        private static FirebaseDatabase database = FirebaseDatabase.getInstance();
        protected static DatabaseReference registrationRef = database.getReference("Registered");


        @Override
        protected void onCreate(Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_admin_inbox);

                RecyclerView recyclerView = findViewById(R.id.recyclerUserInfo);
                List<User> userList = new ArrayList<User>();

                registrationRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {



                                        String firstName = snapshot1.child("firstName").getValue(String.class);
                                        String lastName = snapshot1.child("lastName").getValue(String.class);
                                        String email = snapshot1.child("email").getValue(String.class);
                                        String phoneNumber = snapshot1.child("phoneNumber").getValue(String.class);
                                        String address = snapshot1.child("address").getValue(String.class);
                                        String password = snapshot1.child("password").getValue(String.class);
                                        String status = snapshot1.child("status").getValue(String.class);
                                        userList.add(new User(firstName,lastName,email,password,phoneNumber,address,status));


                                }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                });

                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new AdminInboxAdapter(getApplicationContext(), userList));


        }
        




}
