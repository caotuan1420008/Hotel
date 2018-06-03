package com.example.an.hotelservice.Service.Payment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.an.hotelservice.R;

import java.util.List;


public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {

    private List<PaymentObj> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, money, notice;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            notice = (TextView) view.findViewById(R.id.notice);
            money = (TextView) view.findViewById(R.id.money);
        }
    }


    public PaymentAdapter(List<PaymentObj> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_payment, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PaymentObj movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.notice.setText(movie.getNotice());
        holder.money.setText(movie.getMoney());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
