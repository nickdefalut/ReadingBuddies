package com.example.lxia.readingbuddies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;


/*
* Map to show history comments and location of the person
* */
public class MapActivity extends AppCompatActivity implements AMap.OnMapLoadedListener
        ,AMap.OnMarkerClickListener
        ,AMap.OnInfoWindowClickListener
        ,AMap.InfoWindowAdapter {

    private AMap aMap;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState); // ???????
        init();
    }

    private void init() {

        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    private void setUpMap() {
        aMap.setOnMapLoadedListener(this);// ??amap?????????
        aMap.setOnMarkerClickListener(this);// ????marker?????
        aMap.setOnInfoWindowClickListener(this);// ????infoWindow?????
        aMap.setInfoWindowAdapter(this);// ?????InfoWindow??

        loadData();
    }

    private void loadData() {
        addMarkersToMap();// ??????marker
    }

    /**
     * ??????marker
     */
    private void addMarkersToMap() {

        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(Constants.CHENGDU).title("成都市")
                .snippet("成都市:30.679879, 104.064855").draggable(true));
        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(Constants.XIAN).title("西安市")
                .snippet("西安市:30.679879, 104.064855").draggable(true));
        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(Constants.ZHENGZHOU).title("郑州市")
                .snippet("郑州市:30.679879, 104.064855").draggable(true));
        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(Constants.BEIJING).title("北京市")
                .snippet("北京市:30.679879, 104.064855").draggable(true));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /*OnMapLoadedListener*/
    @Override
    public void onMapLoaded() {
        // ????maker????????????
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(Constants.XIAN).include(Constants.CHENGDU)
                .include(Constants.ZHENGZHOU).include(Constants.BEIJING).build();
        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
    }


    @Override
    public void onInfoWindowClick(Marker marker) {

        //ToastUtil.show(this, "????infoWindow??" + marker.getTitle());
    }

    @Override
    public boolean onMarkerClick(Marker marker) {


        if (!marker.isInfoWindowShown()) {
            marker.showInfoWindow();
        } else {
            marker.hideInfoWindow();
            return true;
        }
        return false;
    }

    @Override
    public View getInfoWindow(Marker marker) {

        View infoContent = getLayoutInflater().inflate(R.layout.custom_info_contents, null);

        render(marker, infoContent);
        return infoContent;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);

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
}
