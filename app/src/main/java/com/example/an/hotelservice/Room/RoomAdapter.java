package com.example.an.hotelservice.Room;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.an.hotelservice.R;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder>  {

    private Context context;
    private List<Room> cartList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, overview, price;
        public ImageView thumbnail;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            overview = view.findViewById(R.id.overview);
            price = view.findViewById(R.id.price);
            thumbnail = view.findViewById(R.id.thumbnail);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);


        }
    }
    public RoomAdapter(Context context, List<Room> cartList) {
        this.context = context;
        this.cartList = cartList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_room, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Room item = cartList.get(position);
        holder.name.setText(item.getName());
        holder.overview.setText(item.getOverview());
        holder.price.setText("₹" + item.getPrice());

        Glide.with(context)
                .load(item.getThumbnail())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return cartList.size();

    }


    private void openDetailActivity(String title, int path_poster, String overview, double releaseDate, boolean vote_average)
    {
        Intent i=new Intent(context, RoomDetail.class);

        //PACK DATA TO SEND
        i.putExtra("title",title);
        i.putExtra("path_poster",path_poster);
        i.putExtra("overview",overview);
        i.putExtra("vote_average",vote_average);
        i.putExtra("releaseDate","Release Date: " + releaseDate);
        //open activity
        context.startActivity(i);

    }

    public void removeItem(int position) {
        cartList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }


    public void restoreItem(Room item, int position) {
        cartList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}