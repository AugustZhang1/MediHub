package com.example.medibook;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.medibook.AdminRejectedList;
import com.example.medibook.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class AdminRejectedList extends AppCompatActivity {

    private Button clickBack;
    private String rejectedEmail,rejectedPassword;
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    protected static DatabaseReference registrationRef = database.getReference("Registered");
    protected static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_rejected_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerRejectedUserInfo);

        fetchData(userList -> {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            AdminRejectedListAdapter adapter = new AdminRejectedListAdapter(getApplicationContext(), userList);
            adapter.setOnClickListener(new AdminRejectedListAdapter.OnClickListener() {
                @Override
                public void onItemClick(int position) {
                    User clickedUser = userList.get(position);
                    rejectedEmail = clickedUser.getEmail();
                    rejectedPassword = clickedUser.getPassword();

                    Log.d("email", "email " + rejectedEmail);
                    Log.d("p", "p " + rejectedPassword);


                    Intent intent = new Intent(AdminRejectedList.this,AdminConfirmReject.class); //Change to the inbox class
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapter);
        });


    }

    private void fetchData(OnDataFetchedCallback callback) {
        List<User> rejectedList = new ArrayList<>();

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

                    if(status.equals("rejected")) {
                        if(password != null)
                            rejectedList.add(new User(firstName, lastName, email, password, phoneNumber, address, status));
                    }
                }

                // Now that data is available, call the callback function
                callback.onDataFetched(rejectedList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle any errors here
            }
        });
    }
    public String getRejectedEmail(){
        return rejectedEmail;
    }

    public String getRejectedPassword(){
        return rejectedPassword;
    }

    // Define a callback interface
    public interface OnDataFetchedCallback {
        void onDataFetched(List<User> rejectedList);
    }
}


