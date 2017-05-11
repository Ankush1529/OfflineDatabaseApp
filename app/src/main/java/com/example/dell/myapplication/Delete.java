package com.example.dell.myapplication;

import android.app.Dialog;
import android.content.Context;
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

public class Delete extends AppCompatActivity {
    Button b;
    EditText e;
    SQLiteDatabase d,db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        b = (Button) findViewById(R.id.b1);
        e = (EditText) findViewById(R.id.e1);
        final Context c = this;
        try {
            String dat="BANKING"+values.v;
            d = openOrCreateDatabase(dat, SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db=openOrCreateDatabase("MAIN",SQLiteDatabase.CREATE_IF_NECESSARY,null);


        } catch (Exception e) {
            e.printStackTrace();
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    AlertDialog.Builder a = new AlertDialog.Builder(Delete.this);
                    a.setMessage("are u sure that you want to delete id:"+e.getText().toString());


                    a.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            int k = 0, kk = 0;
                            Cursor c,cc;
                            c = d.rawQuery("SELECT * FROM BANK", null);
                            c.moveToFirst();
                            while (!c.isAfterLast()) {
                                k++;
                                c.moveToNext();
                            }
                            String f = "DELETE FROM BANK WHERE id=" + e.getText().toString();
                            d.execSQL(f);
                            cc=d.rawQuery("select * from bank",null);
                            cc.moveToFirst();
                            while (!cc.isAfterLast()) {
                                kk++;
                                cc.moveToNext();
                                                       }
                            if (k>kk) {
                                Toast.makeText(getApplicationContext(), "DELETED....", Toast.LENGTH_LONG).show();
                                String h = "DELETE FROM MAINACC WHERE id=" + e.getText().toString();
                                db.execSQL(h);


                                Intent pp=new Intent(getApplicationContext(),Admin.class);
                                startActivity(pp);
                            } else

                            {
                                Toast.makeText(getApplicationContext(), "NOT DELETED....", Toast.LENGTH_LONG).show();
                                AlertDialog.Builder o=new AlertDialog.Builder(Delete.this);
                                o.setMessage("WRONG ID....!!!\nDELETE ACCOUNT USING ACC NAME??");



                                o.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent pp=new Intent(getApplicationContext(),Delete2.class);
                                        startActivity(pp);
                                    }
                                });



                                o.setNegativeButton("no", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        Intent pp=new Intent(getApplicationContext(),Admin.class);
                                        startActivity(pp);

                                    }
                                });

                                o.create();
                                o.show();

                            }
                        }
                    });




                    a.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent p=new Intent(getApplicationContext(),Admin.class);
                            startActivity(p);
                        }
                    });

                    a.create();
                    a.show();
                }



                catch(Exception e){
    e.printStackTrace();
}

            }
        });
    }
}
