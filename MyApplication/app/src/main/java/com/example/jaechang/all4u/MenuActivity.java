package com.example.jaechang.all4u;

import android.app.Activity;
import android.os.Bundle;

//import com.sababado.circularview.CircularView;
//import com.sababado.circularview.Marker;
import com.sababado.circularview.*;

/**
 * Created by Jaechang on 2017-01-21.
 */

public class MenuActivity extends Activity {
    CustomSimpleCircularViewAdapter cAdapter;
    CircularView circularView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cAdapter = new CustomSimpleCircularViewAdapter();
        circularView = (CircularView) findViewById(R.id.circular_view);
        circularView.setAdapter(cAdapter);

        circularView.setOnCircularViewObjectClickListener(new CircularView.OnClickListener() {
            public void onClick(final CircularView view){}

            public void onMarkerClick(CircularView view, Marker marker, int position){
                switch(position){

                }
            }
        });
    }

    @Override
    public void finishAfterTransition() {
        super.finishAfterTransition();
    }
}
