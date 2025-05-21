package com.example.myapplication;

import android.Manifest;

import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }



    @Override
    protected void onResume() {
        super.onResume();// 実行時パーミッションが許可されているか確認
        final int FREQUENCY = 8000;
        int bufsize = AudioRecord.getMinBufferSize(
                FREQUENCY,                        // 標本化周波数
                AudioFormat.CHANNEL_CONFIGURATION_MONO,  // モノラル
                AudioFormat.ENCODING_PCM_16BIT
        );
            //rokuon
        short[] buf = new short[bufsize];
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        AudioRecord rec = new AudioRecord(
                MediaRecorder.AudioSource.MIC,  // マイクから録音
                FREQUENCY,                      // この3つは先ほどと同じ
                AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT,
                bufsize);
        rec.startRecording();

        rec.stop();
        rec.release();
        rec.read(buf, 0, bufsize);
// 二回目に読み込んだデータを処理
        int datasize = rec.read(buf, 0, bufsize), max = 0;
// 振幅の最大値を計算
        for (int i = 0; i < datasize; i++) {
            if ((buf[i] > 0) && (buf[i] >  max)) { max =  buf[i]; } if ((buf[i] < 0) && (buf[i] < -max)) { max = -buf[i]; }
        }
// 結果を    Toast で表示
        String str = Integer.toString(max);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }





}