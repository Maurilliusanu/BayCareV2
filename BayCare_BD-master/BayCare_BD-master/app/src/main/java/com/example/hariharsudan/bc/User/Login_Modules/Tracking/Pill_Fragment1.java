package com.example.hariharsudan.bc.User.Login_Modules.Tracking;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hariharsudan.bc.Adapter.Curent_Org;
import com.example.hariharsudan.bc.Adapter.CurrentUser;
import com.example.hariharsudan.bc.Adapter.CustomAdapter;
import com.example.hariharsudan.bc.Adapter.DataModel;
import com.example.hariharsudan.bc.Adapter.MyData;
import com.example.hariharsudan.bc.Adapter.Org_Update;
import com.example.hariharsudan.bc.Adapter.Pillsadapter2;
import com.example.hariharsudan.bc.Adapter.Tablets;
import com.example.hariharsudan.bc.Org.OrgSignin;
import com.example.hariharsudan.bc.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

/**
 * Created by Hariharsudan on 2/13/2017.
 */

public class Pill_Fragment1 extends Fragment {

    String Base_url = "https://baycare-bfeee.firebaseio.com/Pills/"+ CurrentUser.Aadhar+"/";
    Firebase fb_db;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.pill_fragment1, container, false);

        myOnClickListener = new MyOnClickListener(getActivity());
        Firebase.setAndroidContext(getActivity());
        fb_db = new Firebase(Base_url);
//        FirebaseApp.initializeApp(getActivity());
        recyclerView = (RecyclerView) parentView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        data = new ArrayList<DataModel>();
//        for (int i = 0; i < MyData.medication.length; i++) {
//            data.add(new DataModel(
//                    MyData.medication[i],
//                    MyData.prescribedby[i],
//                    MyData.expiry[i],
//                    MyData.drawableArray[i],
//                    MyData.intake[i]
//
//
//            ));
//        }

        new MyTask().execute();



        return parentView;
    }



private static class MyOnClickListener implements View.OnClickListener {

    private final Context context;

    MyOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

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
            data = new ArrayList<DataModel>();
           fb_db.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                       System.out.println(postSnapshot.getKey());
                       Pillsadapter2 pillsadapter2 = postSnapshot.getValue(Pillsadapter2.class);
                       String tdate = postSnapshot.getKey();
                       ArrayList<Tablets> pills = new ArrayList<Tablets>(pillsadapter2.pills);
                       for (int i = 0; i < pills.size(); i++)
                       {
                           NotificationCompat.Builder builder =
                                   (NotificationCompat.Builder) new NotificationCompat.Builder(getActivity())
                                           .setSmallIcon(R.drawable.pills)
                                           .setContentTitle("Today Pills")
                                           .setContentText("");

                           Intent notificationIntent = new Intent(getActivity(),Pills.class);
                           PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0, notificationIntent,
                                   PendingIntent.FLAG_UPDATE_CURRENT);
                           builder.setContentIntent(contentIntent);

                           // Add as notification
                           NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                           manager.notify(0, builder.build());

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
                   recyclerView.setAdapter(adapter);



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