package com.example.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager manager;
    @Override


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        manager = (SensorManager)getSystemService(SENSOR_SERVICE);


    }

    public void onResume() {
        super.onResume(); // 明るさセンサ(TYPE_LIGHT)のリストを取得
         List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_LIGHT); // ひとつ以上見つかったら、最初のセンサを取得してリスナーに登録
         if (!sensors.isEmpty())
         {
             Sensor sensor = sensors.get(0); manager.registerListener( this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
         }
    }

    protected void onPause() { super.onPause();
        manager.unregisterListener(this); }


    @Override
    public void onSensorChanged(SensorEvent arg0) { // 明るさセンサが変化したとき
        if (arg0.sensor.getType() == Sensor.TYPE_LIGHT) { // 明るさの値（単位ルクス）を取得
            float intensity = arg0.values[0]; // 結果をテキストとして表示
            String str = Float.toString(intensity) + "ルクス"; TextView textview = (TextView) findViewById(R.id.status_text);
            textview.setText(str);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}