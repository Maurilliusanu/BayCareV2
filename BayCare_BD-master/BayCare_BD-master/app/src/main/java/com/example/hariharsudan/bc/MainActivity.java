package com.example.hariharsudan.bc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hariharsudan.bc.Org.OrgSignin;
import com.example.hariharsudan.bc.User.Login_Modules.UserRegister;

public class MainActivity extends AppCompatActivity {
    Button userbut,orgbut;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userbut = (Button)findViewById(R.id.userbut);
        orgbut = (Button)findViewById(R.id.orgbut);

        userbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserRegister.class);
                startActivity(intent);
            }
        });
        orgbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrgSignin.class);
                startActivity(intent);
            }
        });

    }
}
