package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class MyView extends View {
// コンストラクタ（ここではこの引数のものを追加）
public MyView(Context context) {
    super(context);
}
// ビューの描画を行うときに呼ばれるメソッド
@Override
protected void onDraw(@NonNull Canvas canvas) {
    super.onDraw(canvas);

    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.seiza12_uo);
    if (bitmap != null) {
        canvas.drawBitmap(bitmap, 0, 10, null);
    } else {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(40);
        canvas.drawText("画像読み込み失敗", 0, 100, paint);
    }
}

}