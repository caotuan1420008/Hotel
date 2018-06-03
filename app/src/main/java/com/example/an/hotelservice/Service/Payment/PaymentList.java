package com.example.an.hotelservice.Service.Payment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.an.hotelservice.R;

import java.util.ArrayList;
import java.util.List;



public class PaymentList extends AppCompatActivity {
    private List<PaymentObj> payList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PaymentAdapter mAdapter;

    public static String roomName = "";
    public static double roomPrice = 0;
    public static String roomPriceStr = "";
    public static int sGiat = 0;
    public static int sChanNem = 0;
    public static int sCap = 0;
    public static int sInternet = 0;
    public static int sStaff = 0;
    public static int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_list);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new PaymentAdapter(payList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
//        addPayment("4","4","4");
    }
    public void addPayment(String payName, String notice, String money){
        PaymentObj payment = new PaymentObj(payName,notice,money);
        payList.add(payment);
        mAdapter.notifyDataSetChanged();

    }
    private void prepareMovieData() {
        roomPriceStr = Double.toString(roomPrice);
        total = (int)roomPrice + sCap + sGiat + sChanNem + sInternet;
        PaymentObj movie = new PaymentObj(roomName, "", roomPriceStr);
        payList.add(movie);

        movie = new PaymentObj("Phí giặt quần áo", "", Integer.toString(sGiat));
        payList.add(movie);
        movie = new PaymentObj("Phí Internet", "", Integer.toString(sInternet));
        payList.add(movie);
        movie = new PaymentObj("Phí truyền hình cáp", "", Integer.toString(sCap));
        payList.add(movie);
        movie = new PaymentObj("Phí thay chăn nệm", "", Integer.toString(sChanNem));
        payList.add(movie);
//

        movie = new PaymentObj("Tổng cộng: ", " ", Integer.toString(total));
        payList.add(movie);



        mAdapter.notifyDataSetChanged();
    }
}
