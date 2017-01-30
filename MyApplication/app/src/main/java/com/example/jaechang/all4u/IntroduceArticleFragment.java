package com.example.jaechang.all4u;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;

/**
 * Created by jaechang on 2017-01-27.
 */

public class IntroduceArticleFragment extends Fragment {

    View view;
    String depart;
    String path;
    int num;
    int curnum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_introduce_article, container, false);
        view.setBackgroundColor(Color.WHITE);

        create();

        return view;
    }

    void create(){
        DBHelper dbHelper = new DBHelper(getActivity());
        path = dbHelper.getContentsPath(depart);
        num = dbHelper.getNum(depart);

        ImageSwitcher imageSwitcher = (ImageSwitcher) view.findViewById(R.id.ImageSwitcher);

        String uri = "@drawable/" + path + "_0";
        int imageResource = getActivity().getResources().getIdentifier(uri, "drawabale", getActivity().getPackageName());
        imageSwitcher.setImageDrawable(getActivity().getResources().getDrawable(imageResource));

        curnum=0;

        view.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
            @Override
            public void onSwipeRight() {
                //이미지 전환
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
            }
        });
    }

    public IntroduceArticleFragment init(String str){
        depart = str;
        return this;
    }
}
