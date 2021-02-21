package com.example.sandbox;

import android.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PilotScreen extends Fragment {
    TextView textView;
    final String baseURL = "https://prevision-meteo.ch/services/json/bordeaux";

    public PilotScreen() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.pilot_screen, container, false);

        textView = rootView.findViewById(R.id.textView);
        Button buttonView = rootView.findViewById(R.id.button);
        buttonView.setOnClickListener(callApi);

        JoystickView joystickView1 = rootView.findViewById(R.id.joystick1);
        JoystickView joystickView2 = rootView.findViewById(R.id.joystick2);

        joystickView1.setListener((JoystickView joystick, float percentX, float percentY) -> {
            Log.i("joystick1 xPos", ":"+percentX);
            Log.i("joystick1 yPos", ":"+percentY);
        });

        joystickView2.setListener((JoystickView joystick, float percentX, float percentY) -> {
            Log.i("joystick2 xPos", ":"+percentX);
            Log.i("joystick2 yPos", ":"+percentY);
        });

        return rootView;
    }

    private View.OnClickListener callApi = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            RequestQueue req = Volley.newRequestQueue(getActivity());
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
}