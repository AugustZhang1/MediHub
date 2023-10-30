package com.example.medibook;
import android.util.Log;
import android.widget.Button;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.medibook.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminInbox extends AppCompatActivity {
        private Button clickBack;


        private static String inboxEmail,inboxPassword;
        private static User tempUser;

        private static FirebaseDatabase database = FirebaseDatabase.getInstance();

        protected static DatabaseReference registrationRef = database.getReference("Registered");

        protected static FirebaseAuth mAuth = FirebaseAuth.getInstance();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_admin_inbox);

                RecyclerView recyclerView = findViewById(R.id.recyclerUserInfo);

                fetchData(userList -> {
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));


                        AdminInboxAdapter adapter = new AdminInboxAdapter(getApplicationContext(), userList);
                        adapter.setOnClickListener(new AdminInboxAdapter.OnClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                        tempUser = userList.get(position);
                                        inboxEmail = tempUser.getEmail();
                                        inboxPassword = tempUser.getPassword();

                                        Log.d("email", "email" + inboxEmail);
                                        Log.d("p", "p" + inboxPassword);

                                        Intent intent = new Intent(AdminInbox.this,AdminConfirmReject.class); //Change to the inbox class
                                        startActivity(intent);
                                }
                        });
                        recyclerView.setAdapter(adapter);

                });

        }

        private void fetchData(OnDataFetchedCallback callback) {
                List<User> pendingList = new ArrayList<>();

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

                                        if(status.equals("pending")) {
                                                pendingList.add(new User(firstName, lastName, email, password, phoneNumber, address, status));
                                        }
                                }

                                // Now that data is available, call the callback function
                                callback.onDataFetched(pendingList);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                                // Handle any errors here
                        }
                });
        }

        public static String getInboxEmail() {
                return inboxEmail;
        }

        public static String getInboxPassword() {
                return inboxPassword;
        }

        public static User getTempUser() {
                return tempUser;
        }

        // Define a callback interface
        public interface OnDataFetchedCallback {
                void onDataFetched(List<User> pendingList);
        }
        




}
