package com.example.hariharsudan.bc.User.Login_Modules.Tracking;

import android.app.NotificationManager;
import android.app.PendingIntent;
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

import com.example.hariharsudan.bc.Adapter.CurrentUser;
import com.example.hariharsudan.bc.Adapter.CustomAdapter;
import com.example.hariharsudan.bc.Adapter.DataModel;
import com.example.hariharsudan.bc.Adapter.Food;
import com.example.hariharsudan.bc.Adapter.Pillsadapter2;
import com.example.hariharsudan.bc.Adapter.Tablets;
import com.example.hariharsudan.bc.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import static com.example.hariharsudan.bc.User.Login_Modules.Tracking.Pattern.patlist;
import static com.example.hariharsudan.bc.User.Login_Modules.Tracking.Pattern_2.pos;

/**
 * Created by Hariharsudan on 2/13/2017.
 */

public class Pattern_Fragment1 extends Fragment {

    Firebase fb_db;

    String Base_url;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.pattern_fragment1, container, false);

        myOnClickListener = new MyOnClickListener(getActivity());
        Firebase.setAndroidContext(getActivity());
        Base_url = "https://baycare-bfeee.firebaseio.com/Foods/"+patlist.get(pos)+"/";
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
        recyclerView.setAdapter(Food.adapterbb);




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



}