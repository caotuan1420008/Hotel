package com.example.an.hotelservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.an.hotelservice.Login.Login;
import com.example.an.hotelservice.Login.UserDetails;
import com.example.an.hotelservice.Room.RoomList;
import com.example.an.hotelservice.Service.About;
import com.example.an.hotelservice.Service.MainService;

import java.util.Random;

//https://androidchatapp-b03bb.firebaseio.com/
public class MainActivity extends Activity {
    final int random = new Random().nextInt(61) + 20; // [0, 60] + 20 => [20, 80]
    public String android_id=Integer.toString(random);
    String user, pass;
    TextView login;

    private static final String TAG = "ChatActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final int random = new Random().nextInt(61) + 20; // [0, 60] + 20 => [20, 80]
//        final String android_id = Integer.toString(random);
        final Button btnService = (Button) findViewById(R.id.btnService);
        final Button btnRoom = (Button) findViewById(R.id.btnRoom);
        final Button btnAbout = (Button) findViewById(R.id.btnAbout);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent aboutIntent = new Intent(MainActivity.this, About.class);
                MainActivity.this.startActivity(aboutIntent);
            }
        });



        final TextView txtUsername = (TextView) findViewById(R.id.txtUserName);
        if (UserDetails.username.equals(""))
        {
            txtUsername.setText("Guess_"+android_id);
//            AutoGuessRegister();
        }
        else {
            txtUsername.setText(UserDetails.username);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(MainActivity.this,Login.class);
                MainActivity.this.startActivity(loginIntent);
            }
        });

        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(MainActivity.this,MainService.class);
                MainActivity.this.startActivity(serviceIntent);
            }
        });

        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AutoGuessRegister();
                Intent roomIntent = new Intent(MainActivity.this,RoomList.class);
                MainActivity.this.startActivity(roomIntent);
            }
        });
    }

//    public void AutoGuessRegister()
//    {
//        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
//        pd.setMessage("Loading...");
//        pd.show();
//
//        String url = "https://androidchatapp-b03bb.firebaseio.com/users.json";
//
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
//            @Override
//            public void onResponse(String s) {
//                Firebase reference = new Firebase("https://androidchatapp-b03bb.firebaseio.com/users");
//
//                if(s.equals("null")) {
//                    reference.child(android_id).child("password").setValue("11111");
//                    Toast.makeText(MainActivity.this, "registration successful", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    try {
//                        JSONObject obj = new JSONObject(s);
//
//                        if (!obj.has(android_id)) {
//                            reference.child(android_id).child("password").setValue("11111");
//                            Toast.makeText(MainActivity.this, "registration successful", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(MainActivity.this, "username already exists", Toast.LENGTH_LONG).show();
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                pd.dismiss();
//            }
//
//        },new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                System.out.println("" + volleyError );
//                pd.dismiss();
//            }
//        });
//
//        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
//        rQueue.add(request);
//
//        StringRequest requestLoginAuto = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                if (s.equals("null")) {
//                    Toast.makeText(MainActivity.this, "user not found", Toast.LENGTH_LONG).show();
//                } else {
//                    try {
//                        JSONObject obj = new JSONObject(s);
//
//                        if (!obj.has(android_id)) {
//                            Toast.makeText(MainActivity.this, "user not found", Toast.LENGTH_LONG).show();
//
//                        } else if (obj.getJSONObject(android_id).getString("password").equals(pass)) {
//                            UserDetails.username = android_id;
//                            UserDetails.password = pass;
//
////                            startActivity(new Intent(MainActivity.this, MainActivity.class));
////                                        }
//                        } else {
//                            Toast.makeText(MainActivity.this, "incorrect password", Toast.LENGTH_LONG).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                pd.dismiss();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                System.out.println("" + volleyError);
//                pd.dismiss();
//            }
//        });
//
//    }
}
