package com.tutsplus.mapsdemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.tutsplus.mapsdemo.R;

/**
 * Created by Paul on 9/7/15.
 */
public abstract class BaseMapActivity extends Activity {

    protected GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initMapIfNecessary();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initMapIfNecessary();
    }

    protected void initMapIfNecessary() {
        if( mGoogleMap != null ) {
            return;
        }

        mGoogleMap = ( (MapFragment) getFragmentManager().findFragmentById( R.id.map ) ).getMap();

        initMapSettings();
        initCamera();
    }

    protected abstract void initMapSettings();
    protected abstract void initCamera();
}
