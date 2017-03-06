package com.example.jaechang.all4u;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Created by Jaechang on 2017-03-02.
 */

public class SearchResultFragment extends Fragment{

    int[] result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        result = getArguments().getIntArray("Result");

        calculate();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    void calculate(){
        Vector<Pair<String,Integer>> pair = new Vector<>();
        pair.add(new Pair<String, Integer>("R", result[0]));
        pair.add(new Pair<String, Integer>("I", result[1]));
        pair.add(new Pair<String, Integer>("A", result[2]));
        pair.add(new Pair<String, Integer>("S", result[3]));
        pair.add(new Pair<String, Integer>("E", result[4]));
        pair.add(new Pair<String, Integer>("C", result[5]));

        Collections.sort(pair, new Comparator<Pair<String, Integer>>(){
            @Override
            public int compare(Pair<String, Integer> lhs, Pair<String, Integer> rhs) {
                return rhs.second - lhs.second;
            }
        });

        switch(pair.get(0).first){
            case "R":
                break;
        }
    }
}
