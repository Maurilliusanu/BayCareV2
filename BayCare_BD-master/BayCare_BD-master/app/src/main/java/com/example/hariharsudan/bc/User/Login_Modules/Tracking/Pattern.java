package com.example.hariharsudan.bc.User.Login_Modules.Tracking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hariharsudan.bc.Adapter.Curent_Org;
import com.example.hariharsudan.bc.Adapter.CustomAdapter4;
import com.example.hariharsudan.bc.Adapter.Org_Update;
import com.example.hariharsudan.bc.Adapter.User_Update;
import com.example.hariharsudan.bc.Adapter.DataModel4;
import com.example.hariharsudan.bc.Adapter.User_Update2;
import com.example.hariharsudan.bc.Org.OrgSignin;
import com.example.hariharsudan.bc.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import static com.example.hariharsudan.bc.Adapter.CustomAdapter4.pos;

/**
 * Created by subash on 15/3/17.
 */

public class Pattern extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel4> data;
    public static View.OnClickListener myOnClickListener;

    String Base_url = "https://baycare-bfeee.firebaseio.com/Diseases/";
    Firebase fb_db;

    Firebase fb_db2;
    String Base_url2;

    public static ArrayList<String> patlist = new ArrayList<>();


    EditText symp;
    Button get,show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);

        Firebase.setAndroidContext(this);
        fb_db = new Firebase(Base_url);
        data = new ArrayList<DataModel4>();


      //  symp = (EditText)findViewById(R.id.symp);
//        get = (Button)findViewById(R.id.get);
        show = (Button)findViewById(R.id.show);


      //  symp.setText(User_Update.getDisease());


        new MyTask().execute();


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<patlist.size();i++)
                {
                    Base_url2 = "https://baycare-bfeee.firebaseio.com/UsersProfile/"+patlist.get(i)+"/";
                    System.out.println("noob is "+Base_url2);
                    fb_db2 = new Firebase(Base_url2);

                    fb_db2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot)
                        {

                            User_Update2 user_update2 = dataSnapshot.getValue(User_Update2.class);
                            System.out.println("asdfasdf"+user_update2.getUage());
                            System.out.println("asdfasdf"+user_update2.getUname());
                            System.out.println("asdfasdf"+user_update2.getUaadhar());
                            System.out.println("asdfasdf"+user_update2.getUdisease());

                            data.add(new DataModel4(user_update2.getUaadhar(),user_update2.getUdisease(),user_update2.getUage(),user_update2.getUname()));

                            adapter = new CustomAdapter4(data);

                        recyclerView.setAdapter(adapter);


                        }


                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });

                }            }
        });


//        myOnClickListener = new MyOnClickListener(Pattern.this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Treatment");

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        data = new ArrayList<DataModel4>();




        //recyclerView.setAdapter(Food.adapterb);
    }

//
//    private static class MyOnClickListener implements View.OnClickListener {
//
//        private final Context context;
//
//        MyOnClickListener(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public void onClick(View v) {
//            System.out.println("LOLOLOL");
//
//
//        }
//
//    }
    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }



    private class MyTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params)
        {

            fb_db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                    {

                        System.out.println("key is "+postSnapshot.getKey());
                        patlist.add(postSnapshot.getKey());
                        System.out.println("list is "+patlist);
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            return "SUCCESS";
        }




    }


}
