package com.example.jaechang.all4u;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jaechang on 2017-02-14.
 */

public class IntroduceArticleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_introduce_article);



        Intent intent = getIntent();
        depart = intent.getStringExtra("depart");
        create();

        //customPagerAdapter = new CustomPagerAdapter(getActivity());
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(customPagerAdapter);
    }

    String depart;
    String path;
    int num;

    CustomPagerAdapter customPagerAdapter;
    ViewPager viewPager;

    void create(){
        DBHelper dbHelper = new DBHelper(this);
        path = dbHelper.getContentsPath(depart);
        num = dbHelper.getNum(depart);

        customPagerAdapter = new CustomPagerAdapter(this);
        customPagerAdapter.mResources = new int[num];
        for(int i=0;i<num;i++) {
            String uri = "@drawable/" + path + "_" + Integer.toString(i);
            int imageResource = getResources().getIdentifier(uri, "drawabale", getPackageName());
            customPagerAdapter.mResources[i] = imageResource;
        }


    }

}
