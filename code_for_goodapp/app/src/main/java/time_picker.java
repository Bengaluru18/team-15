package com.example.shubhamkumar.code_for_good;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class time_picker extends AppCompatActivity {
    private DatePicker datePicker;
    private Button button1,button2,check;
    private Calendar calendar;
    private EditText dateView1,dateView2;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        check=(Button)findViewById(R.id.check_availability);
        dateView1 = (EditText) findViewById(R.id.start_date);
        dateView2 = (EditText) findViewById(R.id.end_date);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //showDate(year, month+1, day);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate1();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate2();

            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start_date=dateView1.getText().toString();
                String end_date=dateView2.getText().toString();

                Intent intent=new Intent(time_picker.this,farmer_select.class);
                Bundle args = new Bundle();
                args.putString("start_date", start_date);
                args.putString("end_date", end_date);
                intent.putExtras(args);
                startActivity(intent, args);
            }
        });
    }




    public void setDate1() {
        showDialog(999);

    }

   /* @Override
    protected Dialog onCreateDialog(int id1) {
        // TODO Auto-generated method stub
        if (id1 == 999) {
            return new DatePickerDialog(this,
                    myDateListener1, year, month, day);
        }
        return null;
    }
    */

    public DatePickerDialog.OnDateSetListener myDateListener1 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {

                    showDate1(arg1, arg2+1, arg3);
                }
            };

    private void showDate1(int year, int month, int day) {
        dateView1.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

    }
    public void setDate2() {
        showDialog(998);

    }
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 999:return new DatePickerDialog(this,
                    myDateListener1, year, month, day);


            case 998:
                return new DatePickerDialog(this,
                        myDateListener2, year, month, day);

            default:
                return null;
        }
    }


/*
    @Override
    protected Dialog onCreateDialog(int id2) {
        // TODO Auto-generated method stub
        if (id2 == 998) {
            return new DatePickerDialog(this,
                    myDateListener2, year, month, day);

        }
        return null;
    }
    */

    public DatePickerDialog.OnDateSetListener myDateListener2 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {

                    showDate2(arg1, arg2+1, arg3);
                }
            };

    private void showDate2(int year, int month, int day) {
        dateView2.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

    }
    }

