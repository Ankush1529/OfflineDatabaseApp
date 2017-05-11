package com.example.dell.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class view extends AppCompatActivity {
TextView t;
    SQLiteDatabase d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        try{
            String dat="BANKING"+values.v;
            d=openOrCreateDatabase(dat,SQLiteDatabase.CREATE_IF_NECESSARY,null);
            Cursor c=d.rawQuery("SELECT * FROM BANK",null);
            t=(TextView)findViewById(R.id.t1);
            c.moveToLast();
            String tt="";
            while(!c.isBeforeFirst())
            {
                tt=tt+"\n Id:"+c.getString(0)+"\t Type:"+c.getString(1)+"\t Bal.:"+c.getString(2)+"\t Name:"+c.getString(3);
                c.moveToPrevious();

            }
            t.setText(tt);
        }
        catch (Exception e){e.printStackTrace();}
    }
}
