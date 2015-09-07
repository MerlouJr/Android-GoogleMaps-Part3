package com.tutsplus.mapsdemo.activity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
import com.tutsplus.mapsdemo.model.ClusterMarkerLocation;

import java.util.Random;

/**
 * Created by Paul on 9/7/15.
 */
public class ClusterMarkerActivity extends BaseMapActivity {
    private LatLng mCenterLocation = new LatLng( 39.7392, -104.9903 );

    @Override
    protected void initMapSettings() {
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
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

    @Override
    protected void initMapIfNecessary() {
        super.initMapIfNecessary();
        initMarkers();
    }

    private void initMarkers() {
        ClusterManager<ClusterMarkerLocation> clusterManager = new ClusterManager<ClusterMarkerLocation>( this, mGoogleMap );
        mGoogleMap.setOnCameraChangeListener(clusterManager);

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
            clusterManager.addItem( new ClusterMarkerLocation( new LatLng( mCenterLocation.latitude + lat, mCenterLocation.longitude + lng ) ) );
        }
    }
}
