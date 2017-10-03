package com.example.hariharsudan.bc.User.Login_Modules.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.util.CircularArray;
import android.support.v4.util.CircularIntArray;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hariharsudan.bc.Adapter.CurrentUser;
import com.example.hariharsudan.bc.Adapter.Loginnext;
import com.example.hariharsudan.bc.Adapter.User_Update;
import com.example.hariharsudan.bc.Adapter.User_Update2;
import com.example.hariharsudan.bc.Org.Prescribe_userprof;
import com.example.hariharsudan.bc.R;
import com.example.hariharsudan.bc.Sleep;
import com.example.hariharsudan.bc.User.Login_Modules.Tracking.Bills;
import com.example.hariharsudan.bc.User.Login_Modules.Tracking.Diet;
import com.example.hariharsudan.bc.User.Login_Modules.Tracking.Fitness_Step;
import com.example.hariharsudan.bc.User.Login_Modules.Tracking.Pattern;
import com.example.hariharsudan.bc.User.Login_Modules.Tracking.Pills;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfView extends AppCompatActivity
{
    public Loginnext obj;
    String profpicnum;
    TextView user_name,aadhar_num,textView3,textView2,textView,textView9,textView15,textView18,textView6,textView5,textView4,textView16,textView17,textView19;
    TextView heightinp,weightinp,bldgrp,disease;
    String Base_url = "https://baycare-bfeee.firebaseio.com/UsersProfile";
    Firebase fb_db;
    ProgressDialog progressDialog;
    BoomMenuButton bmb;
    CircleImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofview);
        avatar = (CircleImageView)findViewById(R.id.avatar);
        String filei = Environment.getExternalStorageDirectory() + "/BayCare/Login/logs.tmp";
        File f = new File(filei);
        if (f.exists()) {
            System.out.println("FILE READING");
            FileInputStream fisi = null;

            try {

                fisi = new FileInputStream(filei);
                ObjectInputStream ois = new ObjectInputStream(fisi);
                obj = (Loginnext) ois.readObject();
                ois.close();
                if (obj.islog) {
                    CurrentUser.Aadhar = obj.user;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String Base_url = "https://baycare-bfeee.firebaseio.com/UsersProfile/"+ CurrentUser.Aadhar+"/";
        Firebase.setAndroidContext(this);
        fb_db = new Firebase(Base_url);
        new MyTask().execute();

        user_name = (TextView)findViewById(R.id.user_name);
        aadhar_num = (TextView)findViewById(R.id.aadhar_num);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView = (TextView)findViewById(R.id.textView);
//        textView9 = (TextView)findViewById(R.id.textView9);
        textView15 = (TextView)findViewById(R.id.textView15);
//        textView18 = (TextView)findViewById(R.id.textView18);
        textView6 = (TextView)findViewById(R.id.textView6);
        textView5 = (TextView)findViewById(R.id.textView5);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView16 = (TextView)findViewById(R.id.textView16);
        textView17 = (TextView)findViewById(R.id.textView17);
//        textView19 = (TextView)findViewById(R.id.textView19);
        TextView hp = (TextView) findViewById(R.id.hp);
        TextView hp2 = (TextView) findViewById(R.id.hp2);
        bldgrp = (TextView)findViewById(R.id.bldinp);
        heightinp = (TextView)findViewById(R.id.heightinp);
        weightinp = (TextView)findViewById(R.id.weightinp);
        disease = (TextView)findViewById(R.id.diseaseinp);




        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

//        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Ubuntu-R.ttf");
//        user_name.setTypeface(typeface);
//        aadhar_num.setTypeface(typeface);
//        textView3.setTypeface(typeface);
//        textView2.setTypeface(typeface);
//        textView.setTypeface(typeface);
////        textView9.setTypeface(typeface);
//        textView15.setTypeface(typeface);
////        textView18.setTypeface(typeface);
//        textView6.setTypeface(typeface);
//        textView5.setTypeface(typeface);
//        textView4.setTypeface(typeface);
//        textView16.setTypeface(typeface);
//        textView17.setTypeface(typeface);
////        textView19.setTypeface(typeface);
//        hp.setTypeface(typeface);
//        hp2.setTypeface(typeface);


        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_1);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++)
            bmb.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());

        bmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                Toast.makeText(UserProfView.this,"Clicked : "+index,Toast.LENGTH_SHORT).show();


                if(index==0)
                {
                    Intent i = new Intent(UserProfView.this, Fitness_Step.class);
                    startActivity(i);
                }


                if(index==1)
                {
                    Intent i = new Intent(UserProfView.this, Diet.class);
                    startActivity(i);
                }

                if(index==2)
                {
                    Intent i = new Intent(UserProfView.this, Pills.class);
                    startActivity(i);
                }

                if(index==3)
                {
                    Intent i = new Intent(UserProfView.this, Sleep.class);
                    startActivity(i);
                }

                if(index==4)
                {
                    Intent i = new Intent(UserProfView.this, Bills.class);
                    startActivity(i);
                }

                if(index==5)
                {
                    Intent i = new Intent(UserProfView.this, Pattern.class);
                    startActivity(i);
                }
            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {

            }
        });






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_edit:
                Intent i = new Intent(UserProfView.this,UserEditProf.class);
                startActivity(i);
                return true;
            case R.id.menu_del:
                return true;
            case R.id.menu_logout:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private class MyTask extends AsyncTask<String, Integer, String> {

        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(UserProfView.this, "Message", "Fetching Data...");

        }

        // This is run in a background thread
        @Override
        protected String doInBackground(String... params) {
            // get the string from params, which is an array

            System.out.println("BGPROCESS");
            fb_db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    User_Update2 user_update = dataSnapshot.getValue(User_Update2.class);
                    user_name.setText(user_update.getUname());
                    aadhar_num.setText(user_update.getUaadhar());
                    profpicnum = user_update.getUaadhar();
                    new MyTaskPic().execute();

                    String saadhar = user_update.getUaadhar();
                    String ssex = user_update.getUsex();
                    String sage = user_update.getUyob();
                    textView6.setText(user_update.getUname() + "@gmail.com");
                    textView5.setText(user_update.getUaddress());
                    aadhar_num.setText(saadhar);
                    textView4.setText(user_update.getUage());
                    bldgrp.setText(user_update.getUbldgrp());
                    heightinp.setText(user_update.getUheight());
                    weightinp.setText(user_update.getUweight());
                    disease.setText(user_update.getUdisease());


//                    int agenum = year - Integer.parseInt(sage);
//                    System.out.println("ageee is " +(year-Integer.parseInt(sage)));
//                    textView4.setText(agenum);



                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("FIREBASE ERROR OCCOURED");
                }
            });

            return "SUCCESS";
        }



        // This runs in UI when background thread finishes
        @Override
        protected void onPostExecute(String result)
        {


            progressDialog.dismiss();

        }
    }

    private class MyTaskPic extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {

            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("ProfPic").child(profpicnum);

            System.out.println("Storage refference : " + storageReference);
//            Glide.with(getApplicationContext())
//                    .using(new FirebaseImageLoader())
//                    .load(storageReference)
//                    .into(propic);
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
//                    imgprog.dismiss();
                    System.out.println("NOOB");
                    Picasso.with(getApplicationContext()).load(uri).fit().centerCrop().into(avatar);
//                                            Picasso.with(Reports.this).load(uri).fit().centerCrop().into(imgg);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    System.out.println("sad" + exception);
                }
            });
            return null;
        }
    }

}