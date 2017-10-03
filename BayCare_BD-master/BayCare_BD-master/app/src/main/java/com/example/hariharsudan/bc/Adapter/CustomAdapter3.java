package com.example.hariharsudan.bc.Adapter;

/**
 * Created by Hariharsudan on 2/13/2017.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hariharsudan.bc.R;

import java.util.ArrayList;

public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.MyViewHolder> {

    private ArrayList<DataModel3> dataSet;
    public Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView doctor_name,doctor_age,doctor_contact;
        ImageView imageViewIcon;
        Button callnow;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.doctor_name = (TextView) itemView.findViewById(R.id.doctor_name);
            this.doctor_age = (TextView) itemView.findViewById(R.id.doctor_age);
            this.doctor_contact = (TextView) itemView.findViewById(R.id.doctor_contact);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);
            this.callnow = (Button) itemView.findViewById(R.id.callnow);

        }
    }

    public CustomAdapter3(ArrayList<DataModel3> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout3, parent, false);

        //view.setOnClickListener(Pill_Fragment1.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        this.context=parent.getContext();
        return myViewHolder;
    }

//    public CustomAdapter2(Context context)
//    {
//        this.context=context;
//    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView doctor_name = holder.doctor_name;
        TextView doctor_age = holder.doctor_age;
        TextView doctor_contact = holder.doctor_contact;
        ImageView imageView99 = holder.imageViewIcon;
        Button callnow = holder.callnow;

        doctor_name.setText(dataSet.get(listPosition).getdoctorName());
        doctor_age.setText("Age: "+dataSet.get(listPosition).getdoctorAge());
        doctor_contact.setText("Contact: "+dataSet.get(listPosition).getdoctorContact());
        imageView99.setImageResource(dataSet.get(listPosition).getImage());

        callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Calling...",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}