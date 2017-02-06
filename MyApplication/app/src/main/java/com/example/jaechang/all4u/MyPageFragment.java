package com.example.jaechang.all4u;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by jaechang on 2017-01-17.
 */

public class MyPageFragment extends Fragment {

    private View view;
    String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mypage, null);

        init();
        return view;
    }

    private void setName(){
        UserDBHelper userDB = new UserDBHelper(getActivity());
        name = userDB.getName();

        ((TextView)view.findViewById(R.id.textView)).setText(name);

        userDB.close();
    }

    private void setName(String str){
        UserDBHelper userDB = new UserDBHelper(getActivity());
        userDB.setName(str);

        ((TextView)view.findViewById(R.id.textView)).setText(name);

        userDB.close();
    }

    void init(){

        ((ImageButton)view.findViewById(R.id.imageButton_dept)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragT = getFragmentManager().beginTransaction();
                android.support.v4.app.Fragment fragment = android.support.v4.app.Fragment.instantiate(getActivity(), MyPageLikedFragment.class.getName());
                fragT.add(android.R.id.content, fragment, "liked");
                fragT.addToBackStack(null);
                fragT.commit();
            }
        });

        setName();

        ((ImageButton)view.findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    void showDialog(){

        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity(), R.style.DialogStyle);

        ad.setTitle("이름을 입력하십시오");

        final EditText et = new EditText(getActivity());
        et.setText(name);
        et.setPadding(100,0,0,0);
        et.setSingleLine();
        ad.setView(et);

        ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = et.getText().toString();
                setName(name);

                dialog.dismiss();
            }
        });

        ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ad.show();
    }
}
