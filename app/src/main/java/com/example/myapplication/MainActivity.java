package com.example.myapplication;

import android.Manifest;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private LocationManager manager;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    @Override


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        manager = (LocationManager)getSystemService(LOCATION_SERVICE);


    }

    @RequiresPermission(allOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})

    @Override
    protected void onResume() {super.onResume();// 実行時パーミッションが許可されているか確認
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {// 許可されていれば位置情報をリクエスト
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);}
        else {// 許可されていなければリクエストを表示
             ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_REQUEST_CODE);}
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 許可されたので再度位置情報をリクエスト
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
                }
            } else {
                // 拒否された場合のユーザー通知
                Toast.makeText(this, "位置情報の許可が必要です", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void onPause() {
        super.onPause();
        manager.removeUpdates((LocationListener) this);
    }
    @Override
    public void onLocationChanged(@NonNull Location location) { // 得られた緯度経度の情報を表示
        double lat = location.getLatitude();   // 緯度
        double lng = location.getLongitude(); // 経度
        Toast.makeText(this, String.format("%.3f %.3f", lat, lng), Toast.LENGTH_SHORT).show(); }


}