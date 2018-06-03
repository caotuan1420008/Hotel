package com.example.an.hotelservice.Service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.an.hotelservice.Login.ChatClient;
import com.example.an.hotelservice.Login.UserDetails;
import com.example.an.hotelservice.Login.Users;
import com.example.an.hotelservice.R;
import com.example.an.hotelservice.Service.Payment.PaymentList;
import com.example.an.hotelservice.Service.RoomService.RoomService;

public class MainService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);
        final Button btnChatC = (Button) findViewById(R.id.btnChat);
        final Button btnRoomService = (Button) findViewById(R.id.btnRoomService);
        btnRoomService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserDetails.username.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Dich vu chi danh cho nguoi da dang nhap", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent roomSIntent = new Intent(MainService.this, RoomService.class);
                    MainService.this.startActivity(roomSIntent);
                }

            }
        });

        btnChatC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (UserDetails.username.equals("qqqqq"))
                {
                    Intent chatcIntent = new Intent(MainService.this, Users.class);
                    MainService.this.startActivity(chatcIntent);
                }else {
                    Intent chatcIntent = new Intent(MainService.this, ChatClient.class);
                    MainService.this.startActivity(chatcIntent);
                }
            }
        });
        final Button btnPayment = (Button) findViewById(R.id.btnPaid);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserDetails.username.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Dich vu chi danh cho nguoi da dang nhap", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent paymentIntent = new Intent(MainService.this, PaymentList.class);
                    MainService.this.startActivity(paymentIntent);
                }

            }
        });
    }
}
