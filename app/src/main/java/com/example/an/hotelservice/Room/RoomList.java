package com.example.an.hotelservice.Room;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.an.hotelservice.R;
import com.google.firebase.database.Query;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RoomList extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";
    private Query mQuery;
    private ArrayList<Room> mAdapterItems;
    private ArrayList<String> mAdapterKeys;
    private static final String TAG = RoomList.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Room> cartList;
    private RoomAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;

    public TextView txtName;
    public TextView txtDescription;
    public ImageView imgPoster;

    static public int Wifi = 2;
    static public int Aircon = 2;
    static public int dayTo = 0;
    static public int dayFrom = 0;
    static public int monthTo = 0;
    static public int monthFrom = 0;
    static public int dayDiff = 0;
    static public int Bed = 0;
    static public int searchNum = 0;


    // url to fetch menu json
    private static final String URL = "https://androidchatapp-b03bb.firebaseio.com/roomList.json";
//    private static final String URL = "https://api.androidhive.info/json/menu.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.my_cart));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        cartList = new ArrayList<>();
        mAdapter = new RoomAdapter(this, cartList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);


        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Room room = cartList.get(position);
                openDetailActivity(room.getImg1(),room.getImg2(),room.getName(),room.getDayFrom(),room.getDayTo(),room.getMonthFrom()
                        ,room.getMonthTo(),room.getDescription(),room.getPrice(),room.getWifi(),room.getThumbnail());
//                Toast.makeText(getApplicationContext(), room.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // making http call and fetching menu json
        prepareCart();

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback1 = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Row is swiped from recycler view
                // remove it from adapter
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        // attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback1).attachToRecyclerView(recyclerView);
    }

    /**
     * method make volley network call and parses json
     */

    private void prepareCart() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
            RoomFinder roomfinder = new RoomFinder();
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getApplicationContext(), "Couldn't fetch the menu! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        int w;
                        int bed ;
                        int aircon;
                        List<Room> items = new Gson().fromJson(response.toString(), new TypeToken<List<Room>>() {
                        }.getType());
                        cartList.clear();
                        if (searchNum < 1)
                        {
                            cartList.addAll(items);
                        }else {
                              boolean dayGood = false;
                            for (int i = 0; i < items.size(); i++) {
//                                dayGood = false;
                                int dateGetDif = dateDiffCalculate(items.get(i).dayFrom,items.get(i).monthFrom,items.get(i).dayTo,items.get(i).monthTo);
                                int dateGetDif2 = dateDiffCalculate(items.get(i).dayFrom,items.get(i).monthFrom,dayFrom,monthFrom);
                                if (dayDiff <= dateGetDif)
                                {
                                    if (dateGetDif2 >=0 )
                                    {
                                        dayGood = true;
                                    }
                                }

                                String tt = items.get(i).getName();
                                 w = items.get(i).getWifi();
                                 bed = items.get(i).getBed();
                                aircon = items.get(i).getAircon();

                                if ((w == Wifi || Wifi == 2)&& (bed == Bed || Bed == 0) && (aircon == Aircon || Aircon == 2)&&dayGood)
                                {
                                    cartList.add(items.get(i));
                                }
//                                if (w == Wifi || Wifi == 2) {
//                                    cartList.add(items.get(i));
//                                }
//                                if (bed == Bed || Bed == 0) {
//                                    cartList.add(items.get(i));
//                                }
//                                if (aircon == Aircon || Aircon == 2) {
//                                    cartList.add(items.get(i));
//                                }
                            }
                        }
                        // adding items to cart list
//                        cartList.clear();
//                        cartList.addAll(items);

                        // refreshing recycler view
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MyApp.getInstance().addToRequestQueue(request);
    }

    /**
     * callback when recycler view is swiped
     * item will be removed on swiped
     * undo option will be provided in snackbar to restore the item
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof RoomAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = cartList.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            final Room deletedItem = cartList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    mAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds cartList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        Toast.makeText(getApplicationContext(), "Error0: ", Toast.LENGTH_SHORT).show();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_search) {
//            return true;
//        }
//        Toast.makeText(getApplicationContext(), "Error1: ", Toast.LENGTH_SHORT).show();
//        return super.onOptionsItemSelected(item);
//    }
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
        case R.id.action_search:
            Search();
            return true;

        default:
            return super.onOptionsItemSelected(item);
    }
}
    private void openDetailActivity(String img1,String img2,String name, int dayF,int dayT,int monF,int monT, String description, double price, int Wifi,String thumbnail)
    {
        Intent i=new Intent(RoomList.this, RoomDetail.class);

        //PACK DATA TO SEND
        i.putExtra("title",name);
        i.putExtra("overview",description);
        i.putExtra("thumbnail",thumbnail);
        i.putExtra("price",price);
        i.putExtra("Wifi",Wifi);
        i.putExtra("dayFrom",dayF);
        i.putExtra("dayTo",dayT);
        i.putExtra("monthFrom",monF);
        i.putExtra("monthTo",monT);
        i.putExtra("img1",img1);
        i.putExtra("img2",img2);
//        open activity
        RoomList.this.startActivity(i);

    }
    public int dateDiffCalculate(int dayF,int monF,int dayT,int monT)
    {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString1 = dayF+" "+monF+" 2018";
        String inputString2 = dayT+" "+monT+" 2018";
        int a = 0;
        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            a = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
//            Toast.makeText(getApplicationContext(),  "Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS), Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return a;
    }

public void Search()
{
    searchNum ++;
    Intent filterIntent = new Intent(RoomList.this,RoomFinder.class);
    RoomList.this.startActivity(filterIntent);
}
    protected OnBackPressedListener onBackPressedListener;

    public interface OnBackPressedListener {
        void doBack();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null)
            onBackPressedListener.doBack();
        else
            super.onBackPressed();
    }
}
