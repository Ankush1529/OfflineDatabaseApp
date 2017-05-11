package com.example.dell.myapplication;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.renderscript.Sampler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
public class ConAcc extends AppCompatActivity {
Button p1,p2,p3,p4;
    EditText n1,n2,n3;
    SQLiteDatabase db,bd,bdr;
    double con=0.0,a,rec;
    int id=0,idd;
    int flag=0;
    String pass,dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_acc);
        p1=(Button)findViewById(R.id.b1);        p2=(Button)findViewById(R.id.b2);
        p3=(Button)findViewById(R.id.b3);        p4=(Button)findViewById(R.id.b4);
        n1=(EditText)findViewById(R.id.e1);
        n2=(EditText)findViewById(R.id.e2);
        n3=(EditText)findViewById(R.id.e3);
  try
  {
      String datab="HISTORY"+values.v;
      bd=openOrCreateDatabase(datab,SQLiteDatabase.CREATE_IF_NECESSARY,null);
      bd.execSQL("CREATE TABLE HIST(date text,time text,RID numerical,amt numerical,FID numerical)");


  }
  catch (Exception e)
  {
      e.printStackTrace();
  }


try{

    db=openOrCreateDatabase("MAIN",SQLiteDatabase.CREATE_IF_NECESSARY,null);

    Cursor c=db.rawQuery("SELECT * FROM MAINACC",null);
    c.moveToFirst();
    while(!c.isAfterLast())
    {
        if(c.getString(0).equals(values.v))
        {
            n1.setText(c.getString(0));
            id=Integer.parseInt(c.getString(0));
            n2.setText(c.getString(3));
            n3.setText(c.getString(2));
            con=Double.parseDouble(c.getString(2));
            pass=c.getString(4);


        }
        c.moveToNext();
    }


            }
catch (Exception e)
{
    e.printStackTrace();
}

    }
    public void money(View view)
    {

        final Dialog d=new Dialog(ConAcc.this);
        d.setContentView(R.layout.money);
        final EditText o1=(EditText)d.findViewById(R.id.e1);
        final EditText o2=(EditText)d.findViewById(R.id.e2);
        final EditText o3=(EditText)d.findViewById(R.id.e3);
        final Button b=(Button)d.findViewById(R.id.b1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Cursor c = db.rawQuery("SELECT * FROM MAINACC", null);

                    c.moveToFirst();
                    flag=0;
                    while (!c.isAfterLast()) {
                         if (c.getString(0).equals(o1.getText().toString())) {

                            if(c.getString(3).equals(o2.getText().toString()))
                            {
                                a= Double.parseDouble(o3.getText().toString());
                                rec=Double.parseDouble(c.getString(2));
                                if(!(o1.getText().toString().equals(String.valueOf(id))))
                                {
                                    if(!(o3.getText().toString().equals(""))) {

                                        if (con >= a && a>0) {
                                            idd = Integer.parseInt(c.getString(0));flag=1;

                                            final AlertDialog.Builder dd = new AlertDialog.Builder(ConAcc.this);
                                            dd.setMessage("ARE YOU SURE YOU WANT TO MAKE THE TRANSFER??");
                                            dd.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                    Calendar cal = Calendar.getInstance();
                                                    dat = String.valueOf(cal.get(Calendar.DATE)) + "/" + String.valueOf(cal.get(Calendar.MONTH)) + "/" + String.valueOf(cal.get(Calendar.YEAR));
                                                    String tim = String.valueOf((cal.get(Calendar.HOUR))) + ":" + String.valueOf((cal.get(Calendar.MINUTE))) + ":" + String.valueOf((cal.get(Calendar.SECOND)));
                                                    try
                                                    {
                                                        String datt="HISTORY"+String.valueOf(idd);
                                                        bdr=openOrCreateDatabase(datt,SQLiteDatabase.CREATE_IF_NECESSARY,null);
                                                        bdr.execSQL("CREATE TABLE HIST(date text,time text,RID numerical,amt numerical,FID numerical)");


                                                    }
                                                    catch (Exception e)
                                                    {
                                                        e.printStackTrace();
                                                    }
                                                    int ka=0;

                                                    ContentValues vv=new ContentValues();
                                                    vv.put("date", dat);
                                                    vv.put("time", tim);
                                                    vv.put("RID",ka);
                                                    vv.put("amt", a);
                                                    vv.put("FID", id);
                                                    if (bdr.insert("HIST", null, vv) != -1) {
                                                        ;
                                                    } else {
                                                        d.dismiss();
                                                    }


                                                    ContentValues v = new ContentValues();
                                                    v.put("date", dat);
                                                    v.put("time", tim);
                                                    v.put("RID", idd);
                                                    v.put("amt", a);
                                                    v.put("FID",ka);
                                                    if (bd.insert("HIST", null, v) != -1) {
                                                        ;
                                                    } else {
                                                        d.dismiss();
                                                    }


                                                    rec = rec + a;
                                                    con = con - a;
                                                    db.execSQL("UPDATE MAINACC set bal=" + rec + " WHERE ID=" + idd);
                                                    db.execSQL("UPDATE MAINACC set bal=" + con + " WHERE ID=" + id);
                                                    Toast.makeText(getApplicationContext(), "TRANSFER SUCCESSFUL", Toast.LENGTH_LONG).show();
                                                    n3.setText("" + con);

                                                    d.dismiss();
                                                }
                                            });
                                            dd.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Toast.makeText(getApplicationContext(), "TRANSFER CANCELLED", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }
                                            });
                                            dd.show();

                                        } else {

                                            Toast.makeText(getApplicationContext(), "NOT ENOUGH BALANCE", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {

                                        Toast.makeText(getApplicationContext(), "ENTER AMOUNT", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                else
                                {
                                    Toast.makeText(getApplicationContext(),"CANT TRANSFER TO SAME ID",Toast.LENGTH_SHORT).show();
                                }

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"WRONG USERNAME",Toast.LENGTH_SHORT).show();
                            }


                        }

                        c.moveToNext();

                    }

                    if(flag==0)
                    {
                        Toast.makeText(getApplicationContext(), "NO SUCH ID OR ERROR!!", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
                 d.show();


    }

    public void password(View view)
    {
        final Dialog d=new Dialog(ConAcc.this);
        d.setContentView(R.layout.password);
        final EditText o1=(EditText)d.findViewById(R.id.e1);
        final EditText o2=(EditText)d.findViewById(R.id.e2);
        final EditText o3=(EditText)d.findViewById(R.id.e3);
        final Button b=(Button)d.findViewById(R.id.b1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                      if (pass.equals(o1.getText().toString()))
                      {

                            if(o2.getText().toString().equals(o3.getText().toString()))

                            {
                                db.execSQL("UPDATE MAINACC set password='"+o2.getText().toString()+"' WHERE ID="+id);
                                Toast.makeText(getApplicationContext(),"PASSWORD CHANGED",Toast.LENGTH_SHORT).show();
                                d.dismiss();
                                return;
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"PASSWORDS DONT MATCH",Toast.LENGTH_SHORT).show();
                            }


                      }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"WRONG OLD PASSWORD",Toast.LENGTH_SHORT).show();
                        }

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        d.show();

    }

public void history(View view)
{

    try{

        Cursor h=bd.rawQuery("SELECT * FROM HIST",null);
        final Dialog d=new Dialog(ConAcc.this);
        d.setContentView(R.layout.activity_view);
        d.setTitle("TRANSACTION HISTORY");
        final TextView o1=(TextView)d.findViewById(R.id.t1);
        h.moveToLast();
        String tt="";
        while(!h.isBeforeFirst())
        {
          if(!(h.getString(2).equals("0")))
            {tt=tt+"\nON:"+h.getString(0)+"  AT:"+h.getString(1)+"  TO ID:"+h.getString(2)+"  Amt:"+h.getString(3)+"\n";}
            else if(!(h.getString(4).equals("0")))
            {  tt=tt+"\nON:"+h.getString(0)+"  AT:"+h.getString(1)+"  FROM ID:"+h.getString(4)+"  Amt:"+h.getString(3)+"\n";}

            h.moveToPrevious();

        }
        o1.setText(tt);
        d.show();
        Window w=d.getWindow();
        w.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

    }
    catch (Exception e){e.printStackTrace();}

}

    public void logout(View view)
    {
        AlertDialog.Builder d=new AlertDialog.Builder(ConAcc.this);
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
