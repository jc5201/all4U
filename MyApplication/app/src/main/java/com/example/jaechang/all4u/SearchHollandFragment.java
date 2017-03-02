package com.example.jaechang.all4u;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jaechang on 2017-03-02.
 */

public class SearchHollandFragment extends Fragment {

    List<Pair<String, String>> questionList;
    int HollandR, HollandI, HollandA, HollandS, HollandE, HollandC;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_holland, null);

        getDB();
        setArticle(view);
        return  view;
    }

    void getDB(){
        DBHelper dbHelper = new DBHelper(getActivity());
        questionList = dbHelper.getHolland();
        dbHelper.close();
    }

    void setArticle(View view){
        LinearLayout layout = (LinearLayout)view.findViewById(R.id.Linear_search);
        layout.setBackgroundColor(Color.WHITE);
        for(int i=0;i<questionList.size();i++){

            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setPadding(50,50,50,50);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.LEFT);

            TextView textView = new TextView(getActivity());
            textView.setText(questionList.get(i).first);

            RadioGroup radioGroup = new RadioGroup(getActivity());

            RadioButton radioButton1 = new RadioButton(getActivity());
            radioButton1.setText("예");
            radioButton1.setId(1 + 2000);
            RadioButton radioButton2 = new RadioButton(getActivity());
            radioButton2.setText("아니오");
            radioButton2.setId(2 + 2000);

            radioGroup.addView(radioButton1);
            radioGroup.addView(radioButton2);

            radioGroup.setTag(i);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                boolean checked = false;
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int i = (int)group.getTag();
                    if(checked == false && checkedId == 2000 + 2){
                        checked = true;
                    }
                    switch (questionList.get(i).second){
                        case "A":
                            if(checkedId == 2000 + 1) HollandA++;
                            else HollandA--;
                            if(checked == false) checked = true;
                            break;
                        case "R":
                            if(checkedId == 2000 + 1) HollandR++;
                            else HollandR--;
                            if(checked == false) checked = true;
                            break;
                        case "I":
                            if(checkedId == 2000 + 1) HollandI++;
                            else HollandI--;
                            if(checked == false) checked = true;
                            break;
                        case "S":
                            if(checkedId == 2000 + 1) HollandS++;
                            else HollandS--;
                            if(checked == false) checked = true;
                            break;
                        case "E":
                            if(checkedId == 2000 + 1) HollandE++;
                            else HollandE--;
                            if(checked == false) checked = true;
                            break;
                        case "C":
                            if(checkedId == 2000 + 1) HollandC++;
                            else HollandC--;
                            if(checked == false) checked = true;
                            break;
                    }
                }
            });

            linearLayout.addView(textView);
            linearLayout.addView(radioGroup);
            layout.addView(linearLayout);
        }
        Button button = new Button(getActivity());
        button.setText("완료");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragT = getFragmentManager().beginTransaction();
                Fragment fragment = Fragment.instantiate(getActivity(), SearchResultFragment.class.getName());
                Bundle bundle = new Bundle(1);
                int[] arg = {HollandR, HollandI, HollandA, HollandS, HollandE, HollandC};
                bundle.putIntArray("Result", arg);
                fragment.setArguments(bundle);
                fragT.add(android.R.id.content, fragment, "article");
                fragT.addToBackStack(null);
                fragT.commit();
            }
        });
        layout.addView(button);
    }
}
