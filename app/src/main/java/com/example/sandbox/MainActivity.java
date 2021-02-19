package com.example.sandbox;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends WearableActivity {

    TextView textView;
    final String baseURL = "https://prevision-meteo.ch/services/json/bordeaux";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);
        Button buttonView = findViewById(R.id.button);
        buttonView.setOnClickListener(callApi);

        JoystickView joystickView1 = findViewById(R.id.joystick1);
        JoystickView joystickView2 = findViewById(R.id.joystick2);

        joystickView1.setListener((JoystickView joystick, float percentX, float percentY) -> {
                Log.i("joystick1 xPos", ":"+percentX);
                Log.i("joystick1 yPos", ":"+percentY);
        });

        joystickView2.setListener((JoystickView joystick, float percentX, float percentY) -> {
                Log.i("joystick2 xPos", ":"+percentX);
                Log.i("joystick2 yPos", ":"+percentY);
        });

        // Enables Always-on
        setAmbientEnabled();
    }

    private View.OnClickListener callApi = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            RequestQueue req = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    baseURL,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            textView.setText("SUCCESS");
                            try {
                                Log.i("response", response.get("city_info").toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textView.setText("FAILURE");
                            Log.e("respErr", error.toString());
                        }
                    }
            );
            req.add(objectRequest);
        }
    };

    @Override
    protected void onStart() {
        Log.d("MainActivity.java", "onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity.java", "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity.java", "onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity.java", "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity.java", "onDestroy()");
        super.onDestroy();
    }
}