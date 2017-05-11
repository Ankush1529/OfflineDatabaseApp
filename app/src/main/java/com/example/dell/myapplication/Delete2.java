package com.example.dell.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete2 extends AppCompatActivity {

    EditText p1,p2;
    Button b;

    SQLiteDatabase d,db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete2);

        try{
            String dat="BANKING"+values.v;
            d=openOrCreateDatabase(dat,SQLiteDatabase.CREATE_IF_NECESSARY,null);
            db=openOrCreateDatabase("MAIN",SQLiteDatabase.CREATE_IF_NECESSARY,null);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        p1=(EditText)findViewById(R.id.e1);
        p2=(EditText)findViewById(R.id.e2);
        b=(Button)findViewById(R.id.b1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

try {
    int k = 0, kk = 0;
    Cursor c, cc;
    c = d.rawQuery("SELECT * FROM BANK", null);
    c.moveToFirst();
    while (!c.isAfterLast()) {
        k++;
        c.moveToNext();
    }
    String f = "DELETE FROM BANK WHERE type='" + p1.getText().toString() + "' and name='" + p2.getText().toString() + "'";
    d.execSQL(f);

    cc = d.rawQuery("select * from bank", null);
    cc.moveToFirst();
    while (!cc.isAfterLast()) {
        kk++;
        cc.moveToNext();
    }
    if (k > kk) {
        Toast.makeText(getApplicationContext(), "DELETED....", Toast.LENGTH_LONG).show();
        String h = "DELETE FROM BANK WHERE type='" + p1.getText().toString() + "' and name='" + p2.getText().toString() + "'";
        db.execSQL(h);
        Intent pp = new Intent(getApplicationContext(), Admin.class);
        startActivity(pp);
    } else {
        Toast.makeText(getApplicationContext(), "NOT DELETED....NO SUCH ID FOUND!!!", Toast.LENGTH_LONG).show();
        Intent pe = new Intent(getApplicationContext(), Admin.class);
        startActivity(pe);
    }
}
catch(Exception e)
{
    e.printStackTrace();
}

            }
        });

    }
}
