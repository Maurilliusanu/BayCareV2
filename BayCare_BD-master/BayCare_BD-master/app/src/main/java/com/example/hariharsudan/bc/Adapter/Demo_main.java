package com.example.hariharsudan.bc.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hariharsudan.bc.Org.OrgEditProf;
import com.example.hariharsudan.bc.Org.OrgProfView;
import com.example.hariharsudan.bc.Org.OrgSignin;
import com.example.hariharsudan.bc.R;
import com.example.hariharsudan.bc.User.Login_Modules.Profile.UserEditProf;
import com.example.hariharsudan.bc.User.Login_Modules.Profile.UserProfView;
import com.example.hariharsudan.bc.User.Login_Modules.Tracking.Bills;
import com.example.hariharsudan.bc.User.Login_Modules.Tracking.Diet;
import com.example.hariharsudan.bc.User.Login_Modules.Tracking.Fitness_Step;
import com.example.hariharsudan.bc.User.Login_Modules.Tracking.Pills;
import com.example.hariharsudan.bc.User.Login_Modules.UserRegister;


/**
 * Created by Hariharsudan on 1/31/2017.
 */

public class Demo_main extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_main);

        Button user_sign_in = (Button) findViewById(R.id.user_sign_in);
        Button user_edit_prof = (Button) findViewById(R.id.user_edit_prof);
        Button org_sign_in = (Button) findViewById(R.id.org_sign_in);
        Button org_edit_prof = (Button) findViewById(R.id.org_edit_prof);
        Button user_prof_view = (Button) findViewById(R.id.user_prof_view);
        Button org_prof_view = (Button) findViewById(R.id.org_prof_view);
        Button bills = (Button) findViewById(R.id.bills);
        Button pills = (Button) findViewById(R.id.pills);
        Button fitness = (Button) findViewById(R.id.fitness);
        Button diet = (Button) findViewById(R.id.diet);

        user_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Demo_main.this, UserRegister.class);
                startActivity(i);
            }
        });

        user_edit_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Demo_main.this, UserEditProf.class);
                startActivity(i);
            }
        });

        org_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Demo_main.this, OrgSignin.class);
                startActivity(i);
            }
        });

        org_edit_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Demo_main.this, OrgEditProf.class);
                startActivity(i);
            }
        });

        user_prof_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Demo_main.this, UserProfView.class);
                startActivity(i);
            }
        });

        org_prof_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Demo_main.this, OrgProfView.class);
                startActivity(i);
            }
        });

        bills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Demo_main.this, Bills.class);
                startActivity(i);
            }
        });

        pills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Demo_main.this, Pills.class);
                startActivity(i);
            }
        });

        fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Demo_main.this, Fitness_Step.class);
                startActivity(i);
            }
        });

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Demo_main.this, Diet.class);
                startActivity(i);
            }
        });
    }
}
