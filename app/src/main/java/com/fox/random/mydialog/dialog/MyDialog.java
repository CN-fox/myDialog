package com.fox.random.mydialog.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fox.random.mydialog.R;

/**
 * Created by 渠 on 2014/9/7.
 */
public class MyDialog extends View {

    private String TAG = "MyDialog";

    //px;
    private int space = 20;

    private Bitmap background;
    private int width;
    private Center center = new Center();
    private int progress = 37;
    /**
     * default 100;
     */
    private int max = 100;
    private String speed = "23kb";

    public MyDialog(Context context) {
        super(context);
        init(context);
    }

    public MyDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        BitmapDrawable drawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.dialog_background);
        background = drawable.getBitmap();
        Log.d(TAG,"Width:"+background.getWidth()+"\tHeight:"+background.getHeight());

        width = background.getWidth();
        center.x = width/2;
        center.y = width/2;
        setBackgroundColor(Color.GREEN);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background,0,0,new Paint());
        RectF oval = new RectF(space,space,width-space,width-space);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#3f3f3f"));
        canvas.drawArc(oval,0,360,false,paint);

        paint.setColor(Color.parseColor("#ffab03"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(space/2);

        int percent = (int)(((float) progress / (float) max) * 100);

        oval = new RectF(space/2,space/2,width-space/2,width-space/2);
        canvas.drawArc(oval,0,360 * progress / max,false,paint);

        paint = new TextPaint();
        paint.setTextSize(20);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);

        String s = percent + "%";
        Rect rect = new Rect();
        paint.getTextBounds(s,0,s.length(),rect);

        canvas.drawText(s,center.x - rect.width()/2,center.y - rect.height()/4,paint);

        paint.setTextSize(15);

        rect = new Rect();
        paint.getTextBounds(s,0,s.length(),rect);

        canvas.drawText(speed,center.x - rect.width()/2,center.y + 2*rect.height(),paint);
    }

    class Center{
        public int x;
        public int y;
    }
}
