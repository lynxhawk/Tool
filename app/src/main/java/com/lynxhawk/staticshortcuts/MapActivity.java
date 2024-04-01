package com.lynxhawk.staticshortcuts;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;
    public LocationClient mLocationClient;
    private TextView positionText;

    private BaiduMap baiduMap;

    private boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById( R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);

        requestLocation();
    }

    private void requestLocation(){
        initLocation();
        mLocationClient.start();

    }

    private void initLocation()
    {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    private void navigateTo(BDLocation location)
    {
        if(isFirstLocate)
        {
            LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);


    }

    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation location)
        {
            if(location.getLocType()==BDLocation.TypeGpsLocation||location.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(location);
            }
        }
    }

     @Override
    protected void onResume()
     {
         super.onResume();
         mapView.onResume();
     }

     @Override
    protected void onPause()
     {
         super.onPause();
         mapView.onPause();
     }
     @Override
    protected void onDestroy()
     {
         super.onDestroy();
         mLocationClient.stop();
         mapView.onDestroy();
         baiduMap.setMyLocationEnabled(false);
     }


}
