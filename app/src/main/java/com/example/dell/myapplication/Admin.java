package com.example.dell.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
Button p1,p2,p3;
    SQLiteDatabase d,db;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        p1=(Button)findViewById(R.id.b1);
        p2=(Button)findViewById(R.id.b2);
        p3=(Button)findViewById(R.id.b3);
        t=(TextView)findViewById(R.id.t1);
        t.setText("STAFF ID:"+values.v);
        try {
            String dat = "BANKING" + values.v;
            d = openOrCreateDatabase(dat, SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db = openOrCreateDatabase("MAIN", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            d.execSQL("CREATE TABLE BANK(id integer PRIMARY KEY,type text,bal integer,name text)");
            db.execSQL("CREATE TABLE MAINACC(id integer PRIMARY KEY,type text,bal integer,name text,password text)");
        }
        catch(Exception e)
        {
         e.printStackTrace();
        }
        Cursor cc=db.rawQuery("SELECT * FROM MAINACC",null);
            cc.moveToFirst();
            while(!cc.isAfterLast())
            {

                    d.execSQL("UPDATE BANK SET BAL="+cc.getString(2)+" WHERE ID="+cc.getString(0));
                    cc.moveToNext();
            }

    }
    public void create(View view)
    {
        Intent i=new Intent(getApplicationContext(),create.class);
        startActivity(i);
    }
    public void view(View view)
    {
        Intent i=new Intent(getApplicationContext(),view.class);
        startActivity(i);
    }
    public void delete(View view)
    {
        Intent i=new Intent(getApplicationContext(),Delete.class);
        startActivity(i);
    }
    public void modify(View view)
    {
        Intent i=new Intent(getApplicationContext(),Modify.class);
        startActivity(i);
    }
    public void logout(View view)
    {
        AlertDialog.Builder d=new AlertDialog.Builder(Admin.this);
        d.setMessage("DO YOU WANT TO LOGOUT??");
        d.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getApplicationContext(),"SUCCESSFULLY LOGGED OUT",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        d.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        d.show();
    }

}
