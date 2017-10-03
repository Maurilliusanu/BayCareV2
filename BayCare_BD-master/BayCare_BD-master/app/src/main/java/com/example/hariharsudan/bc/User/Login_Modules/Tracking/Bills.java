package com.example.hariharsudan.bc.User.Login_Modules.Tracking;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.hariharsudan.bc.R;

/**
 * Created by Hariharsudan on 2/3/2017.
 */

public class Bills extends AppCompatActivity {


    TextView textView;
    LinearLayout linear_to_hide;
    Button capture,save;
    Typeface typeface;

    BottomSheetDialog bottomSheetDialog ;
    BottomSheetBehavior bottomSheetBehavior ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bills);

        getSupportActionBar().setTitle("Receipt Tracker");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView) findViewById(R.id.listView);


        capture = (Button) findViewById(R.id.capture);

        textView = (TextView) findViewById(R.id.textView);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Ubuntu-R.ttf");
        capture.setTypeface(typeface);

        textView.setTypeface(typeface);

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bills.this,Bills_view.class);
                startActivity(i);

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }


}
