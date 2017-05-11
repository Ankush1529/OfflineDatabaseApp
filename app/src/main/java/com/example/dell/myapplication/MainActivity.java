package com.example.dell.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b,bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.b1);
        bb = (Button) findViewById(R.id.b2);
    }


            public void admin(View view) {
                Intent i=new Intent(getApplicationContext(),AdminDb.class);
                startActivity(i);
            }

                public void ebank(View view) {
                Intent r=new Intent(getApplicationContext(),ConLogin.class);
                startActivity(r);
            }

    }

