package com.example.medibook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class AdministratorInterface extends AppCompatActivity {

    private Button logOffBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_interface);


        createViews();

        logOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mAuth.signOut();
                Intent intent = new Intent(AdministratorInterface.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }



    public void createViews(
    ){
        logOffBtn = findViewById(R.id.logOutAsAdministrator);


    }
}
