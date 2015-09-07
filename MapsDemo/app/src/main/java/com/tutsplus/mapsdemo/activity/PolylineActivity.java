package com.tutsplus.mapsdemo.activity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.List;

/**
 * Created by Paul on 9/7/15.
 */
public class PolylineActivity extends BaseMapActivity {

    private static final String polyline = "gsqqFxxu_SyRlTys@npAkhAzY{MsVc`AuHwbB}Lil@}[goCqGe|BnUa`A~MkbG?eq@hRq}@_N}vKdB";
    private LatLng mCenterLocation = new LatLng( 39.7392, -104.9903 );

    @Override
    protected void initMapSettings() {
        List<LatLng> decodedPath = PolyUtil.decode( polyline );

        mGoogleMap.addPolyline(new PolylineOptions().addAll(decodedPath));
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
