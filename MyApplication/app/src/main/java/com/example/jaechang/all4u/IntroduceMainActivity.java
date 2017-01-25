package com.example.jaechang.all4u;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaechang on 2017-01-24.
 */

public class IntroduceMainActivity extends Fragment {
    private List<String> departmentList = new ArrayList<>();
    private int tempInt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_introduce_main, null);
        LoadDB();
        setArticle(view);
        return view;
    }

    private void LoadDB(){
        DBHelper dbHelper = new DBHelper(getActivity());
        departmentList = dbHelper.getDepartment();
    }

    private void setArticle(View view){
        DBHelper dbHelper = new DBHelper(getActivity());
        final UserDBHelper userDBHelper = new UserDBHelper(getActivity());

        LinearLayout layout = (LinearLayout)view.findViewById(R.id.introduce_layout);
        layout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.WHITE);
        for(int i=0;i<departmentList.size();i++){
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setPadding(50,50,50,50);
            linearLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.RIGHT);

            ImageButton mainImageButton = new ImageButton(getActivity());
            mainImageButton.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            String uri = "@drawable/" + dbHelper.getContentsPath(departmentList.get(i)) + "_" + Integer.toString(0);
            int imageResource = getActivity().getResources().getIdentifier(uri, "drawabale", getActivity().getPackageName());
            mainImageButton.setImageDrawable(getActivity().getResources().getDrawable(imageResource));
            mainImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //컨텐츠 표시하도록 이동
                }
            });

            linearLayout.addView(mainImageButton);

            ImageButton likeImageButton = new ImageButton(getActivity());
            likeImageButton.setTag(i);
            likeImageButton.setLayoutParams(new FrameLayout.LayoutParams(200, ViewGroup.LayoutParams.MATCH_PARENT));
            likeImageButton.setBackgroundColor(Color.WHITE);
            if(userDBHelper.isLiked(departmentList.get(i)))
                likeImageButton.setImageResource(R.drawable.ic_like_light);
            else
                likeImageButton.setImageResource(R.drawable.ic_like);

            likeImageButton.setOnClickListener(new View.OnClickListener(){
                int i;
                ImageButton likeImageButton;
                @Override
                public void onClick(View v) {
                    userDBHelper.setLike(departmentList.get(i));
                    if(userDBHelper.isLiked(departmentList.get(i)))
                        likeImageButton.setImageResource(R.drawable.ic_like_light);
                    else
                        likeImageButton.setImageResource(R.drawable.ic_like);
                }

                public View.OnClickListener init(int i, ImageButton likeImageButton){
                    this.i = i;
                    this.likeImageButton = likeImageButton;
                    return this;
                }
            }.init(i, likeImageButton));

            linearLayout.addView(likeImageButton);
            layout.addView(linearLayout);
        }
    }
}
