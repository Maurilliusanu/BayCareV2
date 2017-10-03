package com.example.hariharsudan.bc.Adapter;

/**
 * Created by Hariharsudan on 2/13/2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hariharsudan.bc.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMedication,textViewPrescribedby,textViewExpiry,textViewIntake,textViewDate;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewMedication = (TextView) itemView.findViewById(R.id.textViewMedication);
            this.textViewPrescribedby = (TextView) itemView.findViewById(R.id.textViewPrescribedby);
            this.textViewExpiry = (TextView) itemView.findViewById(R.id.textViewExpiry);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);
            this.textViewIntake = (TextView) itemView.findViewById(R.id.textViewIntake);
            this.textViewDate=(TextView)itemView.findViewById(R.id.totalcount);

        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        //view.setOnClickListener(Pill_Fragment1.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewMedication = holder.textViewMedication;
        TextView textViewPrescribedby = holder.textViewPrescribedby;
        TextView textViewExpiry = holder.textViewExpiry;
        ImageView imageView99 = holder.imageViewIcon;
        TextView textViewIntake = holder.textViewIntake;
        TextView textViewDate=holder.textViewDate;

        textViewMedication.setText(dataSet.get(listPosition).getMedication());
        textViewPrescribedby.setText(dataSet.get(listPosition).getPrescribedby());
        textViewExpiry.setText(dataSet.get(listPosition).getExpiry());
        imageView99.setImageResource(dataSet.get(listPosition).getImage());
        textViewIntake.setText(String.valueOf(dataSet.get(listPosition).getIntake()));
        textViewDate.setText(dataSet.get(listPosition).getDate());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}