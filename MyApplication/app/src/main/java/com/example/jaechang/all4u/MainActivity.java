package com.example.jaechang.all4u;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setCustomActionBar();
    }

    void setCustomActionBar(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        // Set custom view layout
        View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_main, null);
        actionBar.setCustomView(mCustomView);


        // Set no padding both side
        //Toolbar parent = (Toolbar) mCustomView.getParent(); // first get parent toolbar of current action bar
        //parent.setContentInsetsAbsolute(0, 0);              // set padding programmatically to 0dp

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,255,255)));

        // Set actionbar layout layoutparams
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(mCustomView, params);
    }
}
