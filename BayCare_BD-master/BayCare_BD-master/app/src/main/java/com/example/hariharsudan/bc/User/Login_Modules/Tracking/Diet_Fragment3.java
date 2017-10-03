package com.example.hariharsudan.bc.User.Login_Modules.Tracking;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hariharsudan.bc.Adapter.CustomAdapter;
import com.example.hariharsudan.bc.Adapter.DataModel;
import com.example.hariharsudan.bc.Adapter.Food;
import com.example.hariharsudan.bc.Adapter.MyData;
import com.example.hariharsudan.bc.R;

import java.util.ArrayList;

/**
 * Created by Hariharsudan on 2/13/2017.
 */

public class Diet_Fragment3 extends Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;
    FloatingActionButton fab3;
    public static Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.diet_fragment3, container, false);

        myOnClickListener = new MyOnClickListener(getActivity());

        recyclerView = (RecyclerView) parentView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);


//        fab3 = (FloatingActionButton) parentView.findViewById(R.id.fab3);
//        fab3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),"Alarm Setter",Toast.LENGTH_SHORT).show();
//            }
//        });

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        context=getActivity();



       // adapter = new CustomAdapter(data);
        recyclerView.setAdapter(Food.adapterd);

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