package com.example.medibook;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class AdminConfirmReject extends AppCompatActivity {

    private Button acceptBtn, rejectBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_confirm_reject);

        createViews();

        acceptBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){


                Intent a = new Intent(AdminConfirmReject.this,AdministratorInterface.class);
                startActivity(a);

            }

        });

        rejectBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

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
