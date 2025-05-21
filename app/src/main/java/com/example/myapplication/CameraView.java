package com.example.myapplication;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

@SuppressWarnings("deprecation") // Camera APIは古いため警告を抑制
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private Camera cam;
    private SurfaceHolder holder;

    public CameraView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // 古いAPI向け
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            cam = Camera.open(); // 0を指定しなくても通常は背面カメラ
            cam.setPreviewDisplay(holder);
            cam.startPreview(); // プレビューを開始
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (holder.getSurface() == null || cam == null) {
            return;
        }

        try {
            cam.stopPreview(); // 一度停止してから
        } catch (Exception e) {
            // 無視: すでに停止している可能性
        }

        try {
            cam.setPreviewDisplay(holder);
            cam.startPreview(); // 再開
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (cam != null) {
            cam.stopPreview();
            cam.release();
            cam = null;
        }
    }
}
