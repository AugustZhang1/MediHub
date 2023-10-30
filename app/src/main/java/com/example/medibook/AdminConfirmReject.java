package com.example.medibook;

import static com.example.medibook.AdminInbox.getInboxEmail;
import static com.example.medibook.AdminInbox.getTempUser;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AdminConfirmReject extends AppCompatActivity {

    private Button acceptBtn, rejectBtn, backBtn;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_confirm_reject);

        createViews();

        acceptBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                mAuth.signInWithEmailAndPassword(AdminInbox.getInboxEmail(),AdminInbox.getInboxPassword());
                MainActivity.mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser current = firebaseAuth.getCurrentUser();
                        if (current != null) {
                            MainActivity.userRef.child(current.getUid()).setValue(AdminInbox.getTempUser());
                            MainActivity.userRef.child(current.getUid()).child("status").setValue("Confirmed");
                            MainActivity.registrationRef.child(current.getUid()).removeValue();

                        }
                        mAuth.signOut();
                    }
                });
                Intent a = new Intent(AdminConfirmReject.this,AdministratorInterface.class);
                startActivity(a);

            }

        });

        rejectBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                mAuth.signInWithEmailAndPassword(AdminInbox.getInboxEmail(),AdminInbox.getInboxPassword());
                MainActivity.mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser current = firebaseAuth.getCurrentUser();
                        if (current != null) {
                            MainActivity.registrationRef.child(current.getUid()).child("status").setValue("rejected");

                        }
                        mAuth.signOut();
                    }
                });
                Intent a = new Intent(AdminConfirmReject.this,AdministratorInterface.class);
                startActivity(a);
            }


        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a= new Intent(AdminConfirmReject.this,AdministratorInterface.class);
                startActivity(a);
            }
        });

    }











    public void createViews(){
        acceptBtn = findViewById(R.id.buttonAccept);
        rejectBtn = findViewById(R.id.buttonRejectAdmin);
        backBtn = findViewById(R.id.backBtn);


    }
}
