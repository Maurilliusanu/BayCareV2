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
import android.widget.Toast;

import com.example.hariharsudan.bc.R;
import com.example.hariharsudan.bc.User.Login_Modules.Tracking.Pattern;
import com.example.hariharsudan.bc.User.Login_Modules.Tracking.Pattern_2;

import java.util.ArrayList;

import static com.example.hariharsudan.bc.R.id.aadharnum;

public class CustomAdapter4 extends RecyclerView.Adapter<CustomAdapter4.MyViewHolder> {


    public static int pos;
    private ArrayList<DataModel4> dataSet;
    public Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView aadharnum,disease,age,name;
        ImageView imageViewIcon;
        Button callnow;

        public MyViewHolder(final View itemView) {
            super(itemView);
            this.aadharnum = (TextView) itemView.findViewById(R.id.aadharnum);
            this.disease = (TextView) itemView.findViewById(R.id.disease);
            this.age = (TextView) itemView.findViewById(R.id.age);
            this.name = (TextView)itemView.findViewById(R.id.name);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     pos = getAdapterPosition();
                    System.out.println("CLicked at "+pos);
                     Intent i = new Intent(itemView.getContext(),Pattern_2.class);
                        i.putExtra("pos",pos);
                      itemView.getContext().startActivity(i);
                }
            });


        }


    }

    public CustomAdapter4(ArrayList<DataModel4> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout4, parent, false);




       view.setOnClickListener(Pattern.myOnClickListener);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        this.context=parent.getContext();
        return myViewHolder;
    }

    public CustomAdapter4(Context context)
    {
        this.context=context;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {


        TextView aadharnum = holder.aadharnum;
        TextView disease = holder.disease;
        TextView age = holder.age;
        TextView name = holder.name;
        ImageView imageView99 = holder.imageViewIcon;
        Button callnow = holder.callnow;

        aadharnum.setText(dataSet.get(listPosition).getAadhar());
        disease.setText("Disease: "+dataSet.get(listPosition).getDisease());
        age.setText("Age: "+dataSet.get(listPosition).getAge());
        name.setText("Name: "+dataSet.get(listPosition).getName());



    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}