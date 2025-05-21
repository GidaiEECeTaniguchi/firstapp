package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // ✅ ウィンドウの構成を変えるのは super.onCreate() より前に！
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        // ✅ 全画面表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // ✅ カメラビューを直接表示（activity_main は不要なら削除）
        CameraView camview = new CameraView(this);
        setContentView(camview); // 1回だけ使用する
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: パーミッション確認などここに追加可能
    }
}
