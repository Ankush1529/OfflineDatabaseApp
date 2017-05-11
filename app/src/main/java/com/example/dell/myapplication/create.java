package com.example.dell.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create extends AppCompatActivity {
Button b;
    EditText p1,p2,p3,p4;
    SQLiteDatabase d,db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        p1=(EditText) findViewById(R.id.e1);
        p2=(EditText)findViewById(R.id.e2);
        p3=(EditText)findViewById(R.id.e3);
        p4=(EditText)findViewById(R.id.e4);

        b=(Button)findViewById(R.id.b1);
        final Context c=this;
        try
        {
            String dat="BANKING"+values.v;
            d=openOrCreateDatabase(dat,SQLiteDatabase.CREATE_IF_NECESSARY,null);
            db=openOrCreateDatabase("MAIN",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            d.execSQL("CREATE TABLE BANK(id integer PRIMARY KEY,type text,bal integer,name text)");
            db.execSQL("CREATE TABLE MAINACC(id integer PRIMARY KEY,type text,bal integer,name text,password text)");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues v=new ContentValues();
                ContentValues vv=new ContentValues();

                if(!p1.getText().toString().equals(""))
                {
                    if(!p2.getText().toString().equals(""))
                    {
                        if(!p3.getText().toString().equals(""))
                        {
                            if(!p4.getText().toString().equals(""))
                            {
                                        vv.put("id",p1.getText().toString());
                                        vv.put("type",p2.getText().toString());
                                        vv.put("bal",p3.getText().toString());
                                        vv.put("name",p4.getText().toString());
                                vv.put("password","SBI"+p1.getText().toString());
                                        if(db.insert("MAINACC",null,vv)!=-1)
                                        {
                                            v.put("id",p1.getText().toString());
                                            v.put("type",p2.getText().toString());
                                            v.put("bal",p3.getText().toString());
                                            v.put("name",p4.getText().toString());
                                            if(d.insert("BANK",null,v)!=-1)
                                            {
                                            Toast.makeText(getApplicationContext(),"INSERTED....",Toast.LENGTH_LONG).show();
                                            Intent p=new Intent(getApplicationContext(),Admin.class);
                                            startActivity(p);
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
                                                Intent p=new Intent(getApplicationContext(),Admin.class);
                                                startActivity(p);
                                            }

                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
                                            Intent p=new Intent(getApplicationContext(),Admin.class);
                                            startActivity(p);
                                        }
                                        p1.setText("");p2.setText("");
                                        p3.setText("");p4.setText("");




                            }
                            else{
                                Toast.makeText(getApplicationContext(),"ERROR!!ONE OR MORE FIELD EMPTY",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"ERROR!!ONE OR MORE FIELD EMPTY",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"ERROR!!ONE OR MORE FIELD EMPTY",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"ERROR!!ONE OR MORE FIELD EMPTY",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
