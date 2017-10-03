package com.example.hariharsudan.bc.User.Login_Modules.Tracking;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.hariharsudan.bc.Adapter.CustomAdapter;
import com.example.hariharsudan.bc.Adapter.DataModel;
import com.example.hariharsudan.bc.Adapter.Food;
import com.example.hariharsudan.bc.Adapter.Foodadapter2;
import com.example.hariharsudan.bc.Adapter.Pillsadapter2;
import com.example.hariharsudan.bc.Adapter.Tablets;
import com.example.hariharsudan.bc.Adapter.User_Update2;
import com.example.hariharsudan.bc.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.hariharsudan.bc.User.Login_Modules.Tracking.Pattern.patlist;

/**
 * Created by subash on 16/3/17.
 */

public class Pattern_2 extends AppCompatActivity {

    private static ArrayList<DataModel> bdata,ldata,ddata;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;

    TextView aadhar,name,email;
    public static int pos;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Firebase fb_db;
    String Base_url;

    private int[] tabIcons = {
            R.drawable.ic_action_bf,
            R.drawable.ic_action_lunch,
            R.drawable.ic_action_dinner,
            R.drawable.ic_action_pills,

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern_2);

        aadhar = (TextView)findViewById(R.id.aadhar);
        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);


        pos = getIntent().getExtras().getInt("pos");
        System.out.println(" fuck is "+pos );

        System.out.println("bazhhhhhhhhhhh"+ patlist.get(pos));

        Firebase.setAndroidContext(this);

        Base_url = "https://baycare-bfeee.firebaseio.com/UsersProfile/"+patlist.get(pos)+"/";

        System.out.println("url is "+Base_url);
        fb_db = new Firebase(Base_url);

        new MyTask().execute();
        new MyTask2().execute();
        new MyTask3().execute();


        getSupportActionBar().setTitle("Treatment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Pattern_Fragment1(), "Breakfast");
        adapter.addFragment(new Pattern_Fragment2(), "Lunch");
        adapter.addFragment(new Pattern_Fragment3(), "Dinner");
        adapter.addFragment(new Pattern_Fragment4(), "Medication");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



    private class MyTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params)
        {

            fb_db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                      User_Update2 user_update2 = dataSnapshot.getValue(User_Update2.class);

                    aadhar.setText(user_update2.getUaadhar());
                    name.setText(user_update2.getUname());
                    email.setText(user_update2.getUname().toLowerCase().trim()+"@gmail.com");



                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            return "SUCCESS";
        }


        @Override
        protected void onPostExecute(String result) {
            if (result.equals("SUCCESS")) {

            }
        }
    }
    private class MyTask2 extends AsyncTask<String, Integer, String> {

        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = ProgressDialog.show(OrgSignin.this, "Message", "Creating Account...");

        }

        // This is run in a background thread
        @Override
        protected String doInBackground(String... params) {
            // get the string from params, which is an array
            //  Accounts doc=new Accounts(date,fullname,image);

            String Base_url2 ="https://baycare-bfeee.firebaseio.com/Foods/"+patlist.get(pos)+"/";
            Firebase fb_db2 = new Firebase(Base_url2);
            bdata = new ArrayList<DataModel>();
            ldata = new ArrayList<DataModel>();
            ddata = new ArrayList<DataModel>();
            fb_db2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        System.out.println(postSnapshot.getKey());
                        Foodadapter2 foodadapter2 = postSnapshot.getValue(Foodadapter2.class);
                        String tdate = postSnapshot.getKey();

                        System.out.println("val iss"+tdate +foodadapter2.getBrc1());

                        bdata.add(new DataModel(
                                foodadapter2.getBr1(),
                                foodadapter2.getBrc1(),
                                "",
                                "",
                                R.drawable.bf,
                                tdate
                        ));
                        ldata.add(new DataModel(
                                foodadapter2.getAn1(),
                                foodadapter2.getAnc1(),
                                "",
                                "",
                                R.drawable.lunch,
                                tdate
                        ));
                        ddata.add(new DataModel(
                                foodadapter2.getDin1(),
                                foodadapter2.getDinc1(),
                                "",
                                "",
                                R.drawable.dinner,
                                tdate
                        ));



                    }
                    CustomAdapter adapter = new CustomAdapter(bdata);
                    CustomAdapter  adapter2 = new CustomAdapter(ldata);
                    CustomAdapter  adapter3 = new CustomAdapter(ddata);
                    Food.adapterbb=adapter;
                    Food.adapterlb=adapter2;
                    Food.adapterdb=adapter3;
//                    setupViewPager(viewPager);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

//            fb_db.child("OrgCreds").child(IRDA).setValue(org_update);


            return "SUCCESS";
        }



        // This runs in UI when background thread finishes
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result.equals("SUCCESS")){
                System.out.println("SUCCESS");


            }
            else{
            }

            // Do things like hide the progress bar or change a TextView
        }
    }

    private class MyTask3 extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {
            // get the string from params, which is an array
            //  Accounts doc=new Accounts(date,fullname,image);
            String Base_url2 ="https://baycare-bfeee.firebaseio.com/Pills/"+patlist.get(pos)+"/";
            Firebase fb_db2 = new Firebase(Base_url2);

            data = new ArrayList<DataModel>();
            fb_db2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        System.out.println(postSnapshot.getKey());
                        Pillsadapter2 pillsadapter2 = postSnapshot.getValue(Pillsadapter2.class);
                        String tdate = postSnapshot.getKey();
                        ArrayList<Tablets> pills = new ArrayList<Tablets>(pillsadapter2.pills);
                        for (int i = 0; i < pills.size(); i++)
                        {


                            String dur="",afbf="";
                            if(pills.get(i).mrng)
                            {
                                dur=dur+"M / ";
                                if(pills.get(i).mrngt)
                                {
                                    afbf=afbf+"BF / ";
                                }
                                else
                                {
                                    afbf=afbf+"AF / ";
                                }

                            }
                            else
                            {
                                dur=dur+"- / ";
                                afbf=afbf+"- / ";
                            }
                            if(pills.get(i).noon)
                            {
                                dur=dur+"A / ";
                                if(pills.get(i).noont)
                                {
                                    afbf=afbf+"BF / ";
                                }
                                else
                                {
                                    afbf=afbf+"AF / ";
                                }
                            }
                            else
                            {
                                dur=dur+"- / ";
                                afbf=afbf+"- / ";
                            }
                            if(pills.get(i).night)
                            {
                                dur=dur+"N";
                                if(pills.get(i).nightt)
                                {
                                    afbf=afbf+"BF";
                                }
                                else
                                {
                                    afbf=afbf+"AF";
                                }
                            }
                            else
                            {
                                dur=dur+"-";
                                afbf=afbf+"-";
                            }

                            data.add(new DataModel(
                                    pills.get(i).name,
                                    dur,
                                    tdate,
                                    afbf,
                                    R.drawable.pills,
                                    pills.get(i).tcount.toString()
                            ));
                        }
                    }



                    adapter = new CustomAdapter(data);



                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

//            fb_db.child("OrgCreds").child(IRDA).setValue(org_update);


            return "SUCCESS";
        }



        // This runs in UI when background thread finishes
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result.equals("SUCCESS")){
                System.out.println("SUCCESS");


            }
            else{
            }

            // Do things like hide the progress bar or change a TextView
        }
    }


}
