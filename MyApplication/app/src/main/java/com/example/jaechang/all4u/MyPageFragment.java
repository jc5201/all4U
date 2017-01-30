package com.example.jaechang.all4u;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jaechang on 2017-01-17.
 */

public class MyPageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, null);
        view.setBackgroundColor(Color.WHITE);
        loadLiked(view);

        return view;
    }

    private void loadLiked(View view){
        UserDBHelper userDBHelper = new UserDBHelper(getActivity());

    }

}
