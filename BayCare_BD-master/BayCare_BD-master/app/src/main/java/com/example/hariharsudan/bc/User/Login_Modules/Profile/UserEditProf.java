package com.example.hariharsudan.bc.User.Login_Modules.Profile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.hariharsudan.bc.Adapter.Disease_Adapter;
import com.example.hariharsudan.bc.Adapter.User_Update;
import com.example.hariharsudan.bc.Adapter.User_Update2;
import com.example.hariharsudan.bc.Adapter.WHadapter;
import com.example.hariharsudan.bc.R;
import com.firebase.client.Firebase;

import java.util.Calendar;

public class UserEditProf extends AppCompatActivity
{

    EditText Uaadhar,Uname,Uaddress,Usex,Uyob,Uage,Ubldgrp,Udisease;

    static EditText Uheight,Uweight;
    Button finalreg;
   static BottomDialogHeight bottomDialogh;
    static BottomDialogWeight bottomDialogw;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;
    User_Update2 user_update2 = new User_Update2();
    Disease_Adapter disease_adapter = new Disease_Adapter();
    String aadhar,name,add,gen,age,bld,height,weight,diss;

    String Base_url = "https://baycare-bfeee.firebaseio.com/";
    Firebase fb_db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_profile);

        System.out.println("VAIBH "+User_Update.getUname() +"  "+User_Update.getFinalstreet());

        getSupportActionBar().setTitle("Edit your profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Uaadhar = (EditText)findViewById(R.id.aadhar_input);
        Uname = (EditText)findViewById(R.id.user_first_name_input);
        Uaddress = (EditText)findViewById(R.id.user_add_input);
        Usex = (EditText)findViewById(R.id.user_sex_input);
        Uyob = (EditText)findViewById(R.id.user_dob_input);
        Uage = (EditText)findViewById(R.id.user_age_input);
        Uheight = (EditText)findViewById(R.id.user_height_input);
        Uweight = (EditText)findViewById(R.id.user_weight_input);
        Ubldgrp = (EditText)findViewById(R.id.user_bg_input);
        finalreg = (Button)findViewById(R.id.user_proceed_button);
        Udisease = (EditText)findViewById(R.id.user_disease_inp);


        Firebase.setAndroidContext(this);
        fb_db = new Firebase(Base_url);


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String yob = User_Update.getUyob();
        int a = Integer.parseInt(yob);
        int finage = year - a;

        System.out.println("age is "+finage +"\n" +User_Update.getUaadhar()+"  "+User_Update.getUname()+"  "+User_Update.getFinalstreet()+"  "+User_Update.getUsex());


        Uaadhar.setText(User_Update.getUaadhar());
        Uname.setText(User_Update.getUname());
        Uaddress.setText(User_Update.getFinalstreet()+" "+User_Update.getUstate()+" "+User_Update.getUdist()+" "+User_Update.getUpin());
        Usex.setText(User_Update.getUsex());
        Uyob.setText(User_Update.getUyob());
        Uage.setText(String.valueOf(finage));


        aadhar = Uaadhar.getText().toString();
        name = Uname.getText().toString();
        add = Uaddress.getText().toString();
        gen = Usex.getText().toString();
        age = Uage.getText().toString();



        Uaadhar.setEnabled(false);
        Uname.setEnabled(false);
        Uaddress.setEnabled(false);
        Usex.setEnabled(false);
        Uyob.setEnabled(false);
        Uage.setEnabled(false);



        Uheight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogFragment();
                Uheight.setText(WHadapter.getHeight());

            }
        });

        Uweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogFragment2();
                Uweight.setText(WHadapter.getWeight());


            }
        });

//        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Ubuntu-R.ttf");


//        Firebase.setAndroidContext(this);
//        fb_db = new Firebase(Base_url);



        finalreg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bld = Ubldgrp.getText().toString();
                diss = Udisease.getText().toString();
                new MyTask().execute();


            }
        });




    }
    public static void setHeight(String h)
    {
        Uheight.setText(h);
        bottomDialogh.dismiss();

    }
    public static void setWeight(String w)
    {
        Uweight.setText(w);
        bottomDialogw.dismiss();
    }

    public void showDialogFragment(){
        bottomDialogh= BottomDialogHeight.newInstance();
        bottomDialogh.show(getSupportFragmentManager(),BottomDialogHeight.class.getSimpleName());

    }

    public void showDialogFragment2(){
         bottomDialogw = BottomDialogWeight.newInstance();
        bottomDialogw.show(getSupportFragmentManager(),BottomDialogWeight.class.getSimpleName());
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }


    private class MyTask extends AsyncTask<String, Integer, String>
    {

        @Override
        protected String doInBackground(String... params)
        {

            user_update2.setUaadhar(aadhar);
            user_update2.setUname(name);
            user_update2.setUaddress(add);
            user_update2.setUsex(gen);
            user_update2.setUheight(WHadapter.getHeight());
            user_update2.setUweight(WHadapter.getWeight());
            user_update2.setUbldgrp(bld);
            user_update2.setUage(age);
            user_update2.setUdisease(diss);

            disease_adapter.setDaadhar(aadhar);
            disease_adapter.setDisease(diss);
            disease_adapter.setUname(name);
            disease_adapter.setUage(age);

            User_Update.setDisease(diss);
            User_Update.setBloodgrp(bld);


            fb_db.child("UsersProfile").child(User_Update.getUaadhar()).setValue(user_update2);


            fb_db.child("Diseases").child(User_Update.getUaadhar()).setValue(disease_adapter);

            fb_db.child("OrgPatients").child("600001002").child(User_Update.getUaadhar()).setValue(disease_adapter);


            Intent i = new Intent(UserEditProf.this,UserProfView.class);
            startActivity(i);

            return "SUCCESS";

        }



    }


    }





