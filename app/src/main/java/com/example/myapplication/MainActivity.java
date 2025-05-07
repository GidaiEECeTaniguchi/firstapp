package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    @Override


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(new MyView(this));




    }

    @Override
    public void onClick(View v) {
        showToast();
        finish();
    }
    private void showToast() {
        Toast.makeText(this, "アプリを終了しました", Toast.LENGTH_SHORT).show();
    }
}