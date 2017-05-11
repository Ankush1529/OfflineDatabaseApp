package com.example.dell.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StaffReg extends AppCompatActivity {
Button b;
    EditText p1,p2,p3,p4,p5;
    SQLiteDatabase d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_reg);
        p1=(EditText)findViewById(R.id.e1);p2=(EditText)findViewById(R.id.e2);
        p3=(EditText)findViewById(R.id.e3);p4=(EditText)findViewById(R.id.e4);
        p5=(EditText)findViewById(R.id.e5);
        b=(Button)findViewById(R.id.b1);
        try{
            d=openOrCreateDatabase("BANKING",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            d.execSQL("CREATE TABLE STAFF(id INTEGER PRIMAKRY KEY,NAME TEXT,PASSWORD TEXT)");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues v=new ContentValues();
                if(!p1.getText().toString().equals(""))
                {
                    if(!p2.getText().toString().equals(""))
                    {
                     if(!p3.getText().toString().equals(""))
                     {
                         if(!p4.getText().toString().equals(""))
                         {
                             if(p5.getText().toString().equals("android"))
                             {
                                 if(p3.getText().toString().equals(p4.getText().toString()))
                                 {
                                   v.put("id",p1.getText().toString());
                                     v.put("NAME",p2.getText().toString());
                                     v.put("PASSWORD",p3.getText().toString());
                                     if(d.insert("STAFF",null,v)!=-1)
                                     {
                                         Toast.makeText(getApplicationContext(),"REGISTERED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                                         Intent p=new Intent(getApplicationContext(),AdminDb.class);
                                         startActivity(p);
                                     }
                                     else
                                     {
                                         Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
                                         Intent p=new Intent(getApplicationContext(),AdminDb.class);
                                         startActivity(p);
                                     }
                                     p1.setText("");p2.setText("");
                                     p3.setText("");p4.setText("");
                                     p5.setText("");p3.setHint("new password");p4.setHint("confirm password");
                             }
                                 else
                                 {
                                     Toast.makeText(getApplicationContext(),"WRONG PASS CODE",Toast.LENGTH_LONG).show();

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
