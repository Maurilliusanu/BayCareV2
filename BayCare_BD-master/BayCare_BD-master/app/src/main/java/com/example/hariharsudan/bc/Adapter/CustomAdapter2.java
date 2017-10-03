package com.example.hariharsudan.bc.Adapter;

/**
 * Created by Hariharsudan on 2/13/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hariharsudan.bc.Org.Prescribe_userprof;
import com.example.hariharsudan.bc.R;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {

    private ArrayList<DataModel2> dataSet;
    public Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView patient_name,patient_age,patient_contact,patient_blood;
        ImageView imageViewIcon;
        Button prescribe;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.patient_name = (TextView) itemView.findViewById(R.id.patient_name);
            this.patient_age = (TextView) itemView.findViewById(R.id.patient_age);
            this.patient_contact = (TextView) itemView.findViewById(R.id.patient_contact);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);
            this.patient_blood = (TextView) itemView.findViewById(R.id.patient_blood);
            this.prescribe = (Button) itemView.findViewById(R.id.prescribe);

        }
    }

    public CustomAdapter2(ArrayList<DataModel2> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout2, parent, false);

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

        TextView patient_name = holder.patient_name;
        TextView patient_age = holder.patient_age;
        TextView patient_contact = holder.patient_contact;
        ImageView imageView99 = holder.imageViewIcon;
        TextView patient_blood = holder.patient_blood;
        Button prescribe = holder.prescribe;

        patient_name.setText(dataSet.get(listPosition).getPatientName());
        patient_age.setText("Age: "+dataSet.get(listPosition).getPatientAge());
        patient_contact.setText("Contact: "+dataSet.get(listPosition).getPatientContact());
        imageView99.setImageResource(dataSet.get(listPosition).getImage());
        patient_blood.setText(dataSet.get(listPosition).getPatientBlood());

        prescribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Prescribe_userprof.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}