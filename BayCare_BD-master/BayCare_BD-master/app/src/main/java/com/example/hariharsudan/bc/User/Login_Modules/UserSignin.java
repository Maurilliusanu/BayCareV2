//package com.example.hariharsudan.bc.User.Login_Modules;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.hariharsudan.bc.R;
//import com.example.hariharsudan.bc.User.Login_Modules.Profile.UserProfView;
//
//
//public class UserSignin extends AppCompatActivity
//{
//    EditText aadharnum,userpass;
//    Button userlogbut, usersignbut;
//    String aadhar,pass;
//    String Base_url = "https://baycare-bfeee.firebaseio.com/";
//  //  Firebase fb_db;
//    private Intent serviceIntent;
//    ImageView imageView;
//
//    ProgressDialog progressDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_usersignin);
//        ImageView imageView = (ImageView)findViewById(R.id.imageView);
//
//        TextView an_textview = (TextView) findViewById(R.id.an_textview);
//        TextView pass_textview = (TextView) findViewById(R.id.pass_textview);
//
//        aadharnum = (EditText)findViewById(R.id.aadharnum);
//        userpass = (EditText)findViewById(R.id.userpass);
//
//        userlogbut = (Button)findViewById(R.id.userlogbut);
//        usersignbut = (Button)findViewById(R.id.usersignbut);
//
//
//
//        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Ubuntu-R.ttf");
//        an_textview.setTypeface(typeface);
//        pass_textview.setTypeface(typeface);
//        aadharnum.setTypeface(typeface);
//        userpass.setTypeface(typeface);
//        userlogbut.setTypeface(typeface);
//        usersignbut.setTypeface(typeface);
//
//
//        usersignbut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                aadhar = aadharnum.getText().toString();
//                pass = userpass.getText().toString();
//                Toast.makeText(getApplicationContext(),"login",Toast.LENGTH_LONG).show();
//
//
//
//            }
//        });
//        userlogbut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                aadhar = aadharnum.getText().toString();
//                pass = userpass.getText().toString();
//                Intent i = new Intent(UserSignin.this, UserProfView.class);
//                startActivity(i);
//            }
//        });
//
//
//    }
//
//
//
//
//
//
//
//}
