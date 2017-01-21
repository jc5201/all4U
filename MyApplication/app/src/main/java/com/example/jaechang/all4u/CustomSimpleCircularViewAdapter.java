package com.example.jaechang.all4u;

import com.sababado.circularview.Marker;
import com.sababado.circularview.SimpleCircularViewAdapter;

import static com.example.jaechang.all4u.R.drawable.center_bg;

/**
 * Created by Jaechang on 2017-01-21.
 */

public class CustomSimpleCircularViewAdapter extends SimpleCircularViewAdapter {
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public void setupMarker(int position, Marker marker) {
        marker.setSrc(center_bg);
        marker.setFitToCircle(true);
        marker.setRadius(50 + 1 * position);
    }
}
