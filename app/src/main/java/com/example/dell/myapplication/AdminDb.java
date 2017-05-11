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
import android.widget.EditText;
import android.widget.Toast;

public class AdminDb extends AppCompatActivity {
Button b,bb;
    EditText p1,p2;
    SQLiteDatabase d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_db);

        b=(Button)findViewById(R.id.b1);
        bb=(Button)findViewById(R.id.b2);
        p1=(EditText)findViewById(R.id.e1);
        p2=(EditText)findViewById(R.id.e2);
        try {
            d=openOrCreateDatabase("BANKING", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        }
        catch(Exception e){e.printStackTrace();}
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                Cursor c;
                int k=0;
                c=d.rawQuery("SELECT * FROM STAFF",null);
                    c.moveToFirst();
                while(!c.isAfterLast())
                {
                    String nm=c.getString(0);
                    if(nm.equals(p1.getText().toString()))
                    {
                        k=1;break;
                    }
                    c.moveToNext();
                }

                if(k==1) {
                    if (c.getString(2).equals(p2.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        values.v = p1.getText().toString();
                        p2.setText("1234567891234567889");
                        Intent i = new Intent(getApplicationContext(), Admin.class);
                        startActivity(i);
                    } else {
                        AlertDialog.Builder a = new AlertDialog.Builder(AdminDb.this);
                        a.setMessage("WRONG USERNAME OR PASSWORD");
                        a.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //
                            }
                        });
                        a.create();
                        a.show();
                    }
                }
                 else
                {
                    AlertDialog.Builder a=new AlertDialog.Builder(AdminDb.this);
                    a.setMessage("WRONG USERNAME OR PASSWORD");
                    a.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                         //
                        }
                    });
                    a.create();
                    a.show();
                }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });




        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder a=new AlertDialog.Builder(AdminDb.this);
                a.setMessage("REQUIRES BANK MANAGER PASS CODE AND AUTHENTICATION..WANT TO CONTINUE??");
                a.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                   Intent p=new Intent(getApplicationContext(),StaffReg.class);
                        startActivity(p);
                    }
                });
                a.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                    }
                });
                a.create();
                a.show();
            }
        });


    }
}
