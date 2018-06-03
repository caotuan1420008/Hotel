package com.example.an.hotelservice.Room;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.an.hotelservice.R;
import com.squareup.picasso.Picasso;

public class RoomImg extends AppCompatActivity {
    public static String img1;
    public static String img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_img);
        final ImageView imgView1 = (ImageView) findViewById(R.id.imgExtra1);
        final ImageView imgView2 = (ImageView) findViewById(R.id.imgExtra2);
        Picasso.get().load(img1).into(imgView1);
        Picasso.get().load(img2).into(imgView2);
    }
}
