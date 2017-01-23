package com.example.jaechang.all4u;

import android.app.Activity;
import android.os.Bundle;
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

public class IntroduceMainActivity extends Activity {
    private List<String> departmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_main);

        LoadDB();
        setArticle();
    }

    private void LoadDB(){
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        departmentList = dbHelper.getDepartment();
    }

    private void setArticle(){
        LinearLayout layout = (LinearLayout)findViewById(R.id.introduce_layout);
        layout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        for(int i=0;i<departmentList.size();i++){
            FrameLayout frameLayout = new FrameLayout(this);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            frameLayout.setPadding(20, 20,20,20);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            ImageButton mainImageButton = new ImageButton(this);
            mainImageButton.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            String uri = "@drawable/" + departmentList.get(i) + "0";
            int imageResource = getApplicationContext().getResources().getIdentifier(uri, "drawabale", getApplicationContext().getPackageName());
            mainImageButton.setImageDrawable(getApplicationContext().getResources().getDrawable(imageResource));
            mainImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //컨텐츠 표시하도록 이동
                }
            });

            linearLayout.addView(mainImageButton);

            ImageButton likeImageButton = new ImageButton(this);
            likeImageButton.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            likeImageButton.setImageResource(R.drawable.ic_like);
            //여기 뭔가 추가해야

            linearLayout.addView(likeImageButton);
            frameLayout.addView(linearLayout);
            layout.addView(frameLayout);
        }
    }
}
