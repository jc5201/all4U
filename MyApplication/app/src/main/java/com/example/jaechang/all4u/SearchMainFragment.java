package com.example.jaechang.all4u;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Jaechang on 2017-02-06.
 */

public class SearchMainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_main, null);

        ((ImageButton) view.findViewById(R.id.imageButton2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragT = getFragmentManager().beginTransaction();
                android.support.v4.app.Fragment fragment = android.support.v4.app.Fragment.instantiate(getActivity(), SearchWebFragment.class.getName());
                fragT.add(android.R.id.content, fragment, "article");
                fragT.addToBackStack(null);
                fragT.commit();
            }
        });

        return view;
    }
}
