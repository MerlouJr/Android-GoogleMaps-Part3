package com.tutsplus.mapsdemo.activity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Paul on 9/7/15.
 */
public class HeatMapActivity extends BaseMapActivity {

    private LatLng mCenterLocation = new LatLng( 39.7392, -104.9903 );

    private HeatmapTileProvider mProvider;
    private TileOverlay mOverlay;

    @Override
    protected void initMapSettings() {
        ArrayList<LatLng> locations = generateLocations();
        mProvider = new HeatmapTileProvider.Builder().data( locations ).build();
        mOverlay = mGoogleMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
        mProvider.setRadius( HeatmapTileProvider.DEFAULT_RADIUS );
    }

    private ArrayList<LatLng> generateLocations() {
        ArrayList<LatLng> locations = new ArrayList<LatLng>();
        double lat;
        double lng;
        Random generator = new Random();
        for( int i = 0; i < 1000; i++ ) {
            lat = generator.nextDouble() / 3;
            lng = generator.nextDouble() / 3;
            if( generator.nextBoolean() ) {
                lat = -lat;
            }
            if( generator.nextBoolean() ) {
                lng = -lng;
            }
            locations.add(new LatLng(mCenterLocation.latitude + lat, mCenterLocation.longitude + lng));
        }

        return locations;
    }

    @Override
    protected void initCamera() {
        CameraPosition position = CameraPosition.builder()
                .target( mCenterLocation )
                .zoom( 12f )
                .bearing( 0.0f )
                .tilt( 0.0f )
                .build();

        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
    }
}
