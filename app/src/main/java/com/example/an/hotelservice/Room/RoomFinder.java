package com.example.an.hotelservice.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.an.hotelservice.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RoomFinder extends AppCompatActivity {
    public EditText txtDateFrom;
    public EditText txtDateTo;
    public Calendar myCalendar;
    public int year;
    public int month;
    public int day;
    public String name;
    public int dayTo = 0;
    public int dayFrom = 0;
    public int monthTo = 0;
    public int monthFrom = 0;
    public int yearTo = 0;
    public int yearFrom = 0;
    public int diff1=0;
    long diff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_finder);
        txtDateFrom = (EditText) findViewById(R.id.dateFrom);
        txtDateTo = (EditText) findViewById(R.id.dateTO);
        final CheckBox chWifi = (CheckBox) findViewById(R.id.checkBox2);
        final CheckBox chAircon = (CheckBox) findViewById(R.id.checkBox);
        final Spinner spBed = (Spinner) findViewById(R.id.spinnerBed);
        final RoomList roomlist = new RoomList();
        txtDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v, txtDateFrom);
            }
        });
        txtDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v, txtDateTo);
            }
        });

        try {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chWifi.isChecked()) {
                    RoomList.Wifi = 1;
                } else {
                    RoomList.Wifi = 0;
                }
                if (chAircon.isChecked())
                {
                    RoomList.Aircon = 1;
                }else
                {
                    RoomList.Aircon = 0;
                }
                if (spBed.getSelectedItem().toString().equals("0"))
                {
                    RoomList.Bed = 0;
                }else if (spBed.getSelectedItem().toString().equals("1"))
                {
                    RoomList.Bed = 1;
                }else if (spBed.getSelectedItem().toString().equals("2"))
                {
                    RoomList.Bed = 2;
                }
                diff1 = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                RoomList.dayFrom = dayFrom;
                RoomList.dayTo = dayTo;
                RoomList.monthFrom = monthFrom;
                RoomList.monthTo = monthTo;
                RoomList.dayDiff = diff1;


                Intent roomIntent = new Intent(RoomFinder.this, RoomList.class);
                RoomFinder.this.startActivity(roomIntent);
            }
        });
          }catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void setDate(View v,final EditText ed) {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthofyear, int dayofmonth) {
                ed.setText(dayofmonth + "-" + (monthofyear+1) + "-" + year);
                if (ed.getId() == R.id.dateFrom)
                {
                    dayFrom = dayofmonth;
                    monthFrom = monthofyear+1;
                    yearFrom = year;
                }else if (ed.getId() == R.id.dateTO)
                {
                    dayTo = dayofmonth;
                    monthTo = monthofyear+1;
                    yearTo = year;
                }


            }
        }, year, month, day);
        dpd.show();

        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString1 = dayFrom+" "+monthFrom+" "+yearFrom;
        String inputString2 = dayTo+" "+monthTo+" "+yearTo;
        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
             diff = date2.getTime() - date1.getTime();
//            Toast.makeText(getApplicationContext(),  "Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS), Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
