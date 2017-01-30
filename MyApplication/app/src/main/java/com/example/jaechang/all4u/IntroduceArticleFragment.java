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
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by jaechang on 2017-01-27.
 */

public class IntroduceArticleFragment extends Fragment {

    View view;
    String depart;
    String path;
    int num;

    int[] mResources;
    CustomPagerAdapter customPagerAdapter;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_introduce_article, container, false);
        view.setBackgroundColor(Color.WHITE);

        depart = getArguments().getString("depart");
        create();

        return view;
    }

    void create(){
        DBHelper dbHelper = new DBHelper(getActivity());
        path = dbHelper.getContentsPath(depart);
        num = dbHelper.getNum(depart);


        mResources = new int[num];
        for(int i=0;i<num;i++) {
            String uri = "@drawable/" + path + "_" + Integer.toString(i);
            int imageResource = getActivity().getResources().getIdentifier(uri, "drawabale", getActivity().getPackageName());
            mResources[i] = imageResource;
        }

        customPagerAdapter = new CustomPagerAdapter(getActivity());
        viewPager = (ViewPager)view.findViewById(R.id.pager);
        viewPager.setAdapter(customPagerAdapter);
        viewPager.setOffscreenPageLimit(num);

    }

    public class CustomPagerAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(mResources[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
