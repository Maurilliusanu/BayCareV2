package com.example.hariharsudan.bc.User.Login_Modules.Tracking;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.hariharsudan.bc.Adapter.CurrentUser;
import com.example.hariharsudan.bc.Adapter.CustomAdapter;
import com.example.hariharsudan.bc.Adapter.DataModel;
import com.example.hariharsudan.bc.Adapter.Food;
import com.example.hariharsudan.bc.Adapter.Foodadapter;
import com.example.hariharsudan.bc.Adapter.Foodadapter2;
import com.example.hariharsudan.bc.Adapter.Pillsadapter2;
import com.example.hariharsudan.bc.Adapter.Tablets;
import com.example.hariharsudan.bc.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Hariharsudan on 2/13/2017.
 */

public class Diet extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private static ArrayList<DataModel> bdata,ldata,ddata;
    String Base_url = "https://baycare-bfeee.firebaseio.com/Foods/"+ CurrentUser.Aadhar+"/";
    Firebase fb_db;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_action_bf,
            R.drawable.ic_action_lunch,
            R.drawable.ic_action_dinner
    };
    FloatingActionButton fab1;
    CoordinatorLayout c1;
    Snackbar snackbar;
    String date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        Firebase.setAndroidContext(Diet.this);
        fb_db = new Firebase(Base_url);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Diet");
        c1 = (CoordinatorLayout) findViewById(R.id.c1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Diet.this,"Alarm Setter",Toast.LENGTH_SHORT).show();
                Calendar now = Calendar.getInstance();
                DatePickerDialog datepickerdialog = DatePickerDialog.newInstance(
                        Diet.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                //datepickerdialog.setThemeDark(true); //set dark them for dialog?
                datepickerdialog.vibrate(true); //vibrate on choosing date?
                datepickerdialog.dismissOnPause(true); //dismiss dialog when onPause() called?
                datepickerdialog.showYearPickerFirst(false); //choose year first?
                datepickerdialog.setAccentColor(Color.parseColor("#01aaad")); // custom accent color
                datepickerdialog.setTitle("Please select a date"); //dialog title
                datepickerdialog.show(getFragmentManager(), "Datepickerdialog"); //show dialog
            }
        });

        new MyTask().execute();
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
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Diet_Fragment1(), "Breakfast");
        adapter.addFragment(new Diet_Fragment2(), "Lunch");
        adapter.addFragment(new Diet_Fragment3(), "Dinner");
        try
        {
            viewPager.setAdapter(adapter);
        }
        catch (Exception e)
        {
            System.out.println("SECOND TIME PRESCRIBTION");
            Toast.makeText(this,"You Cannot Prescribe Twice a day.",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        Toast.makeText(Diet.this,date,Toast.LENGTH_SHORT).show();

        Calendar now = Calendar.getInstance();
        TimePickerDialog timepickerdialog = TimePickerDialog.newInstance(Diet.this,
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), true);
        timepickerdialog.setThemeDark(false); //Dark Theme?
        timepickerdialog.vibrate(false); //vibrate on choosing time?
        timepickerdialog.dismissOnPause(false); //dismiss the dialog onPause() called?
        timepickerdialog.enableSeconds(true); //show seconds?
        timepickerdialog.setAccentColor(Color.parseColor("#01aaad")); // custom accent color

        //Handling cancel event
        timepickerdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(Diet.this, "Cancel choosing time", Toast.LENGTH_SHORT).show();
            }
        });
        timepickerdialog.show(getFragmentManager(), "Timepickerdialog"); //show time picker dialog

    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String secondString = second < 10 ? "0" + second : "" + second;
        String time = hourString + "h" + minuteString + "m" + secondString + "s";

       snackbar = Snackbar
                .make(c1, "Alarm set for " +time+" on "+date, Snackbar.LENGTH_LONG)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Snackbar snackbar1 = Snackbar.make(c1, "Message is restored!", Snackbar.LENGTH_SHORT);
//                        snackbar1.show();
                        snackbar.dismiss();
                    }
                });

        snackbar.show();
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
            bdata = new ArrayList<DataModel>();
            ldata = new ArrayList<DataModel>();
            ddata = new ArrayList<DataModel>();
            fb_db.addValueEventListener(new ValueEventListener() {
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
                    CustomAdapter  adapter = new CustomAdapter(bdata);
                    CustomAdapter  adapter2 = new CustomAdapter(ldata);
                    CustomAdapter  adapter3 = new CustomAdapter(ddata);
                    Food.adapterb=adapter;
                    Food.adapterl=adapter2;
                    Food.adapterd=adapter3;
                    setupViewPager(viewPager);

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
