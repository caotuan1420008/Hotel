package com.example.an.hotelservice.Service.RoomService;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

import com.example.an.hotelservice.R;
import com.example.an.hotelservice.Service.MainService;
import com.example.an.hotelservice.Service.Payment.PaymentList;

public class RoomService extends AppCompatActivity {

    public int wash = 2;
    public int cap = 2;
    public int channem = 2;
    public int internet = 2;
    public int staff = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_service);
        final Switch swWash = (Switch) findViewById(R.id.swWash);
        final Switch swCap = (Switch) findViewById(R.id.seCap);
        final Switch swInternet = (Switch) findViewById(R.id.swInternet);
        final Switch swChanNem = (Switch) findViewById(R.id.swChanNem);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swWash.isChecked())
                {
                    PaymentList.sGiat = 10000;
                }
                if (swChanNem.isChecked())
                {
                    PaymentList.sChanNem = 5000;
                }
                if (swInternet.isChecked())
                {
                    PaymentList.sInternet = 10000;
                }
                if (swCap.isChecked())
                {
                    PaymentList.sCap = 20000;
                }

                Intent mainserviceIntent = new Intent(RoomService.this, MainService.class);
                RoomService.this.startActivity(mainserviceIntent);
            }
        });
    }

}
