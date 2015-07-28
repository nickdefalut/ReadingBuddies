package com.example.lxia.readingbuddies;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

/**
 * Created by v-lesh on 7/27/2015.
 * 1 find your location
 * 2 find comments near by.
 */
public class NearByFragment extends Fragment implements AMapLocationListener,LocationSource,SensorEventListener
        ,AMap.OnMarkerClickListener
        ,AMap.OnInfoWindowClickListener
        ,AMap.InfoWindowAdapter{

    private MapView mMapView;
    private AMap aMap;
    private Marker mGPSMarker;
    private LocationManagerProxy mAMapLocationManager;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private OnLocationChangedListener mListener;
    private AMapLocation lastMapLocation;
    private View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (root == null) {
            root = inflater.inflate(R.layout.map_layout, container, false);
            aMap = null;
        }

        mMapView = (MapView) root.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getActivity()
                .getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        init();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void init() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            setUpMap();
        }
    }

    /**
     * ????amap???
     */
    private void setUpMap() {

        mGPSMarker=aMap.addMarker(
                new MarkerOptions().icon(

                        BitmapDescriptorFactory
                                .fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.location_marker))
                ).anchor((float)0.5, (float)0.5));

        aMap.setLocationSource(this);// ??????
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// ????????????
        aMap.setMyLocationEnabled(true);// ???true??????????????false??????????????????false

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        registerSensorListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
        deactivate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    /*
    * AMapLocationListener
    * */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
           // mListener.onLocationChanged(aMapLocation);// ???????
            mGPSMarker.setPosition(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()));

            //Location changed and Time changed to update search result 500m and 1min
            if (lastMapLocation == null || AMapUtils.calculateLineDistance(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()),
                    new LatLng(lastMapLocation.getLatitude(), lastMapLocation.getLongitude())) > 500 ||
                    Math.abs(lastMapLocation.getTime() - aMapLocation.getTime()) > 60000) {

                lastMapLocation = aMapLocation;

                searchNearby();

                Toast.makeText(getActivity(), aMapLocation.getAddress(),Toast.LENGTH_SHORT).show();
            }

        }
    }

    // search info from server
    private void searchNearby() {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    /*
    * LocationSource
    * */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mAMapLocationManager == null) {
            mAMapLocationManager = LocationManagerProxy.getInstance(getActivity());

            /*
             * mAMapLocManager.setGpsEnable(false);//
             * 1.0.2?????????true?????????gps???false???????????true Location
             * API????GPS?????????
             * ?????????provider???????????2000????????????????????????????
             */
            mAMapLocationManager.requestLocationData(
                    LocationProviderProxy.AMapNetwork, 2000, 10, this);
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mAMapLocationManager != null) {
            mAMapLocationManager.removeUpdates(this);
            mAMapLocationManager.destroy();
        }
        mAMapLocationManager = null;
        unRegisterSensorListener();
    }

    /*
    * SensorEventListener
    * */

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void registerSensorListener() {
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unRegisterSensorListener() {
        mSensorManager.unregisterListener(this, mSensor);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if (!marker.isInfoWindowShown()) {
            marker.showInfoWindow();
        } else {
            marker.hideInfoWindow();
        }
        return false;
    }

    @Override
    public View getInfoWindow(Marker marker) {

        View infoContent = getActivity().getLayoutInflater().inflate(R.layout.custom_info_contents, null);

        render(marker, infoContent);
        return infoContent;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View infoWindow = getActivity().getLayoutInflater().inflate(R.layout.custom_info_window, null);

        render(marker, infoWindow);
        return infoWindow;
    }


    public void render(Marker marker, View view) {

        String title = marker.getTitle();
        TextView titleUi = ((TextView) view.findViewById(R.id.title));

        if (!TextUtils.isEmpty(title)) {
            titleUi.setText(title);
        }

        String snippet = marker.getSnippet();
        TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));

        if (!TextUtils.isEmpty(snippet)) {
            snippetUi.setText(snippet);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {



        // start another activity
    }
}
