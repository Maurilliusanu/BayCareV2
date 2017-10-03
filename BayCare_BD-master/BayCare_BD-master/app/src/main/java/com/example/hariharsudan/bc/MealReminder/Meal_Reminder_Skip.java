package com.example.hariharsudan.bc.MealReminder;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hariharsudan.bc.R;

/**
 * Created by Hariharsudan on 2/16/2017.
 */

public class Meal_Reminder_Skip extends AppCompatActivity{

    TextView textView31,textView32,textView33,textView35,textView36,textView37,next_meal_h,next_meal_m;
    Button ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_reminder_skip);


        textView31 = (TextView) findViewById(R.id.textView31);
        textView32 = (TextView) findViewById(R.id.textView32);
        textView33 = (TextView) findViewById(R.id.textView33);
        textView35 = (TextView) findViewById(R.id.textView35);
        textView36 = (TextView) findViewById(R.id.textView36);
        textView37 = (TextView) findViewById(R.id.textView37);
        next_meal_h = (TextView) findViewById(R.id.next_meal_h);
        next_meal_m = (TextView) findViewById(R.id.next_meal_m);


        ok= (Button) findViewById(R.id.ok);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Ubuntu-R.ttf");
        textView31.setTypeface(typeface);
        textView32.setTypeface(typeface);
        textView33.setTypeface(typeface);
        ok.setTypeface(typeface);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Meal_Reminder_Skip.this,"LOL",Toast.LENGTH_SHORT).show();
                Meal_Reminder_Skip.this.finish();
            }
        });
    }
}
