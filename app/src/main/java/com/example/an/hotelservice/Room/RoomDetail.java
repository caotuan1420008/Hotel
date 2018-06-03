package com.example.an.hotelservice.Room;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.an.hotelservice.Login.UserDetails;
import com.example.an.hotelservice.R;
import com.example.an.hotelservice.Service.Payment.PaymentAdapter;
import com.example.an.hotelservice.Service.Payment.PaymentList;
import com.example.an.hotelservice.Service.Payment.PaymentObj;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RoomDetail extends AppCompatActivity {

    private static final String TAG = RoomDetail.class.getSimpleName();
    ImageView imgPoster;
    TextView txtName, txtDateF,txtDateT, txtvOverview;
    RatingBar rbVotePoint;

    public String name;
    public int dayF;
    public int dayT;
    public int monF;
    public int monT;
    public String description;
    public String thumb;
    public double price;
    public String imgExtra1;
    public String imgExtra2;

    private List<PaymentObj> payList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PaymentAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        imgPoster = (ImageView) findViewById(R.id.imgPosterDetail);
        txtvOverview = (TextView) findViewById(R.id.tvOverview);
        txtDateF = (TextView)findViewById(R.id.txtDateFrom);
        txtDateT = (TextView)findViewById(R.id.txtDateTo);
        txtName = (TextView) findViewById(R.id.txtName);
        Intent i=this.getIntent();
        name = i.getExtras().getString("title");
        description = i.getExtras().getString("overview");
        thumb = i.getExtras().getString("thumbnail");
        price = i.getExtras().getDouble("price");
        dayF = i.getExtras().getInt("dayFrom");
        dayT = i.getExtras().getInt("dayTo");
        monF = i.getExtras().getInt("monthFrom");
        monT = i.getExtras().getInt("monthTo");
        txtDateF.setText("Ngày sẵn sàng: "+Integer.toString(dayF)+"/"+Integer.toString(monF)+"/"+"2018");
        txtDateT.setText("Ngày hết thời hạn:"+Integer.toString(dayT)+"/"+Integer.toString(monT)+"/"+"2018");
        txtName.setText(name);
        txtvOverview.setText(description);

        RoomImg.img1 = i.getExtras().getString("img1");
        RoomImg.img2 = i.getExtras().getString("img2");


        Picasso.get().load(thumb).into(imgPoster);
        imgPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgIntent = new Intent(RoomDetail.this,RoomImg.class);
                RoomDetail.this.startActivity(imgIntent);
            }
        });


//        txtvReleaseDate.setText(i.getExtras().getString("releaseDate"));
//        //txtvReleaseDate.setText(i.getExtras().getString("vote_average"));
//        float point = Float.parseFloat(i.getExtras().getString("vote_average"));
//        rbVotePoint.setRating((point*5)/10);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (UserDetails.username.equals("")) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent guestbuyIntent = new Intent(RoomDetail.this, GuestBuy.class);
                    RoomDetail.this.startActivity(guestbuyIntent);
                }
            });
        }
        else
        {
            PaymentList.roomName = name;
            PaymentList.roomPrice = price;

             fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
            Intent userbuyIntent = new Intent(RoomDetail.this, RoomList.class);
            RoomDetail.this.startActivity(userbuyIntent);
                }
            });
        }
    }
}