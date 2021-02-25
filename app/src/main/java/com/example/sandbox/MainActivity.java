package com.example.sandbox;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends WearableActivity implements Communication {

    private static final String TAG = "MainActivity";
    private static final String KEY_FRAGMENT = "fragment_save";

    private long time;
    private Toast toast;

    private static String mFragment;
    private final SplashScreen splashScreen = new SplashScreen();
    private final MainMenu mainMenu = new MainMenu();
    private final PilotScreen pilotScreen = new PilotScreen();
    private final LocationScreen locationScreen = new LocationScreen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enables Always-on
        setAmbientEnabled();
        Log.d(TAG, "onCreate: Started.");

        if(savedInstanceState != null)
            mFragment = savedInstanceState.getString(KEY_FRAGMENT);
        else
            mFragment = getIntent().getStringExtra(KEY_FRAGMENT);

        // Case of app resume
        if (mFragment != null) {
            if(mFragment.equals(((Object) splashScreen).getClass().getSimpleName()))
                showFragment(this.splashScreen);
            else if(mFragment.equals(((Object) mainMenu).getClass().getSimpleName()))
                showFragment(this.mainMenu);
            else if(mFragment.equals(((Object) pilotScreen).getClass().getSimpleName()))
                showFragment(this.pilotScreen);
            else if(mFragment.equals(((Object) locationScreen).getClass().getSimpleName()))
                showFragment(this.locationScreen);
        } else
            showFragment(this.splashScreen);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_FRAGMENT, mFragment != null ? mFragment: "");
        super.onSaveInstanceState(outState);
    }

    private void showFragment(final Fragment fragment) {
        if (fragment == null)
            return;

        // Update current fragment
        mFragment = ((Object) fragment).getClass().getSimpleName();
        // Begin a fragment transaction
//        final FragmentManager fm = new FragmentActivity().getSupportFragmentManager();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        // animate the changing fragment
        // Replace current fragment by a new one
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        //commit changes
        ft.commit();
    }

    @Override
    public void goToMainMenu(){
        showFragment(this.mainMenu);
    }

    @Override
    public void goToPilotScreen(){ showFragment(this.pilotScreen); }

    @Override
    public void goToLocationScreen() { showFragment(this.locationScreen); }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "BACK PRESSED");

        if(time+2000 > System.currentTimeMillis()){
            toast = Toast.makeText(getApplicationContext(),"GoodBye!",
                    Toast.LENGTH_SHORT);
            toast.show();
            Log.i(TAG, "Quit");
            finish();
            return;
        } else {
            goToMainMenu();
        }

        time = System.currentTimeMillis();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy()");
        super.onDestroy();
    }
}