package com.example.shubhamkumar.code_for_good;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button farmer_login_button,center_login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        center_login_button=(Button)findViewById(R.id.center_head);
        farmer_login_button=(Button)findViewById(R.id.farmer);
        center_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(MainActivity.this,center_head_login.class);
                startActivity(i1);
            }
        });
        farmer_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(MainActivity.this,farmer_login.class);
                startActivity(i2);
            }
        });

    }
}
