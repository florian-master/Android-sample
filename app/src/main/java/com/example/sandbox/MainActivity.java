package com.example.sandbox;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);

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