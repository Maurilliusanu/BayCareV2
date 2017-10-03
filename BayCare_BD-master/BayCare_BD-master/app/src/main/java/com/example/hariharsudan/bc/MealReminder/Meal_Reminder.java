package com.example.hariharsudan.bc.MealReminder;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hariharsudan.bc.R;

/**
 * Created by Hariharsudan on 2/16/2017.
 */

public class Meal_Reminder extends AppCompatActivity{

    TextView textView31,textView32,textView33,textView34;
    Button meal_yes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_reminder);


        textView31 = (TextView) findViewById(R.id.textView31);
        textView32 = (TextView) findViewById(R.id.textView32);
        textView33 = (TextView) findViewById(R.id.textView33);
        textView34 = (TextView) findViewById(R.id.textView34);

        meal_yes= (Button) findViewById(R.id.meal_yes);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Ubuntu-R.ttf");
        textView31.setTypeface(typeface);
        textView32.setTypeface(typeface);
        textView33.setTypeface(typeface);
        textView34.setTypeface(typeface);
        meal_yes.setTypeface(typeface);

        meal_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Meal_Reminder.this,Meal_Reminder_Two.class);
                startActivity(i);
                Meal_Reminder.this.finish();
            }
        });

        textView34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Meal_Reminder.this,Meal_Reminder_Skip.class);
                startActivity(i);
                Meal_Reminder.this.finish();
            }
        });
    }
}
