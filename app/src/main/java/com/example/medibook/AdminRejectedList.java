package com.example.medibook;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.medibook.AdminRejectedList;
import com.example.medibook.classes.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class AdminRejectedList extends AppCompatActivity{

    private RelativeLayout userinfoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_rejected_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerRejectedUserInfo);

        List<User> userList = new ArrayList<User>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdminRejectedListAdapter(getApplicationContext(),userList ));

//        createViews();
//
//        userinfoBtn.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v){
////                ViewGroup viewGroup = (ViewGroup) v;
////                for (int i = 0; i < viewGroup .getChildCount(); i++) {
////
////                    View viewChild = viewGroup .getChildAt(i);
////                    viewChild.setPressed(true);
////
////                }
//                Intent a = new Intent(AdminRejectedList.this,MainActivity.class);
//                startActivity(a);
//
//            }
//
//        });


    }



//    public void createViews(){
//       userinfoBtn = findViewById(R.id.rejectUserBox);
//
//
//    }
}
