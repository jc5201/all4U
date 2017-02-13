package com.example.jaechang.all4u;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class IntroduceArticleFragment extends Fragment {

    View view;
    String depart;
    String path;
    int num;

    CustomPagerAdapter customPagerAdapter;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_introduce_article, container, false);
        view.setBackgroundColor(Color.DKGRAY);

        depart = getArguments().getString("depart");
        create();

        //customPagerAdapter = new CustomPagerAdapter(getActivity());
        viewPager = (ViewPager)view.findViewById(R.id.pager);
        viewPager.setAdapter(customPagerAdapter);


        return view;
    }

    void create(){
        DBHelper dbHelper = new DBHelper(getActivity());
        path = dbHelper.getContentsPath(depart);
        num = dbHelper.getNum(depart);

        customPagerAdapter = new CustomPagerAdapter(getActivity());
        customPagerAdapter.mResources = new int[num];
        for(int i=0;i<num;i++) {
            String uri = "@drawable/" + path + "_" + Integer.toString(i);
            int imageResource = getActivity().getResources().getIdentifier(uri, "drawabale", getActivity().getPackageName());
            customPagerAdapter.mResources[i] = imageResource;
        }


    }


}
