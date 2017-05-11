package com.example.dell.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Double2;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modify extends AppCompatActivity {
EditText p1,p2,p3;
    SQLiteDatabase db,dbb;
    double b;int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        p1=(EditText)findViewById(R.id.e1);
        p2=(EditText)findViewById(R.id.e2);
        p3=(EditText)findViewById(R.id.e3);
        try
        {
            String dat="BANKING"+values.v;
            dbb=openOrCreateDatabase(dat,SQLiteDatabase.CREATE_IF_NECESSARY,null);
            db=openOrCreateDatabase("MAIN",SQLiteDatabase.CREATE_IF_NECESSARY,null);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    public void save(View view)
    {
        Cursor c=db.rawQuery("SELECT * FROM MAINACC",null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if (c.getString(0).equals(p1.getText().toString())) {


                if (c.getString(3).equals(p2.getText().toString())) {
                    id = Integer.parseInt(c.getString(0));
                    AlertDialog.Builder d = new AlertDialog.Builder(Modify.this);
                    d.setMessage("ARE YOU SURE YOU WANT TO MAKE THE CAHNGE??");
                    d.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            b = Double.parseDouble(p3.getText().toString());
                            dbb.execSQL("UPDATE BANK set bal="+b+" where id=" + id);
                            db.execSQL("UPDATE MAINACC set bal="+b+" where id=" + id);
                            Toast.makeText(getApplicationContext(), "BALANCE UPDATED", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Admin.class);
                            startActivity(intent);
                        }
                    });
                    d.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "NO CHANGES MADE", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    });
                    d.show();


                }
                else {
                    Toast.makeText(getApplicationContext(), "WRONG USERNAME", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                if (c.isLast()) {
                    Toast.makeText(getApplicationContext(), "NO SUCH ID!!", Toast.LENGTH_SHORT).show();
                }

                }
            c.moveToNext();
        }

    }

}
