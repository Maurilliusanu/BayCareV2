package com.example.hariharsudan.bc;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Sleep extends Activity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static Sleep inst;
    private TextView alarmStatus;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Intent motionIntent;
    Toolbar toolbar2;
    Button graph_view;
    ToggleButton alarmToggle;

    public static Sleep instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        sharedPreferences = this.getSharedPreferences("com.example.tanay.sleepcyclemonnitor",this.MODE_PRIVATE);
        alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmToggle.setChecked(sharedPreferences.getBoolean("alarmToggle",false));
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmStatus = (TextView) findViewById(R.id.alarmStatus);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        motionIntent = new Intent(this,MotionSensorLogger.class);
        graph_view = (Button) findViewById(R.id.graph_view);

        graph_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sleep.this, GraphActivity.class);
                startActivity(intent);
            }
        });
       // toolbar2 = (Toolbar) findViewById(R.id.toolbar2);

       // setActionBar(toolbar2);

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void onToggleClicked(View view) {
        if(alarmToggle.isChecked()){
            editor = sharedPreferences.edit();
            editor.putBoolean("alarmToggle",true);
            editor.commit();
        }
        else {
            editor = sharedPreferences.edit();
            editor.putBoolean("alarmToggle",false);
            editor.commit();
        }

        if (sharedPreferences.getBoolean("alarmToggle",true)) {
            Log.d("MyActivity", "Alarm On");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            alarmStatus.setText("Alarm Set For: " + sdf.format(calendar.getTime()));
            Intent myIntent = new Intent(Sleep.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(Sleep.this, 0, myIntent, 0);
            alarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            Intent stopIntent = new Intent(this, AlarmService.class);
            this.stopService(stopIntent);
            alarmStatus.setText("Alarm Set For: ");
            Log.d("MyActivity", "Alarm Off");
        }
    }





}


