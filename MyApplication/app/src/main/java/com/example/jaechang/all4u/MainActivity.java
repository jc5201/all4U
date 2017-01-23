package com.example.jaechang.all4u;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentTransaction;
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

        addActionBarTab();
        setCustomActionBar();
    }

    void addActionBarTab(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = null;
        tab = actionBar.newTab();
        tab.setIcon(R.drawable.ic_action_find);
        tab.setTabListener(listener);
        actionBar.addTab(tab);

        tab = actionBar.newTab();
        tab.setIcon(R.drawable.ic_action_search);
        tab.setTabListener(listener);
        actionBar.addTab(tab);

        tab = actionBar.newTab();
        tab.setIcon(R.drawable.ic_action_inform);
        tab.setTabListener(listener);
        actionBar.addTab(tab);

        tab = actionBar.newTab();
        tab.setIcon(R.drawable.ic_action_contact);
        tab.setTabListener(listener);
        actionBar.addTab(tab);

        tab = actionBar.newTab();
        tab.setIcon(R.drawable.ic_action_user);
        tab.setTabListener(listener);
        actionBar.addTab(tab);
    }

    void setCustomActionBar(){
        ActionBar actionBar = getSupportActionBar();

        //actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
/*
        // Set custom view layout
        View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_main, null);
        actionBar.setCustomView(mCustomView);


        // Set no padding both side
        //Toolbar parent = (Toolbar) mCustomView.getParent(); // first get parent toolbar of current action bar
        //parent.setContentInsetsAbsolute(0, 0);              // set padding programmatically to 0dp

        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,255,255)));

        // Set actionbar layout layoutparams
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(mCustomView, params);*/
    }

    ActionBar.TabListener listener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            int position = tab.getPosition();
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    };
}
