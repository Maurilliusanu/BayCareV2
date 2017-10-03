package com.example.hariharsudan.bc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hariharsudan.bc.Org.OrgSignin;
import com.example.hariharsudan.bc.User.Login_Modules.UserRegister;

/**
 * Created by Hariharsudan on 2/16/2017.
 */

public class Main extends AppCompatActivity {

    String cur="patient";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //final ImageView org = (ImageView) findViewById(R.id.org);
        final ImageView patient = (ImageView) findViewById(R.id.patient);
        Button next = (Button) findViewById(R.id.next);

        getSupportActionBar().setTitle("Who are you?");


//
//        patient.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View arg0, MotionEvent arg1) {
//                switch (arg1.getAction()) {
//                    case MotionEvent.ACTION_DOWN: {
//                        patient.setImageResource(R.drawable.patient);
//                 //       org.setImageResource(R.drawable.org_gray);
//                        cur = "patient";
//                        Toast.makeText(Main.this,"Patient",Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                    case MotionEvent.ACTION_CANCEL:{
//                        Toast.makeText(Main.this,"nol",Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                }
//                return true;
//            }
//        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(cur=="org")
//                {
//                    Intent i = new Intent(Main.this, OrgSignin.class);
//                    startActivity(i);
//                }

                if(cur=="patient")
                {
                    Intent i = new Intent(Main.this, UserRegister.class);
                    startActivity(i);
                }
            }
        });
    }
}
