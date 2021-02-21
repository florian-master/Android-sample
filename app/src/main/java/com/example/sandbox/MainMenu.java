package com.example.sandbox;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainMenu extends Fragment {
    TextView pilot_screen;
    private Communication communicationInterface;

    public MainMenu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_menu, container, false);
        pilot_screen = rootView.findViewById(R.id.pilot_screen);
        pilot_screen.setOnClickListener(show_pilot_screen);
        return rootView;
    }

    private View.OnClickListener show_pilot_screen = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            communicationInterface.goToPilotScreen();
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        communicationInterface = (Communication) activity;
    }
}