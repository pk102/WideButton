package com.fiumelabs.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

/**
 * Created by paulkuzich on 10/16/14.
 */
public class WideButton extends ImageView {

    public static final String TAG = WideButton.class.getSimpleName();

    private int width;
    private int height;

    private Paint buttonPaint;


    public WideButton(Context context){
        super(context);
        initialize(context);

    }

    protected void initialize(Context context){

        buttonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        buttonPaint.setStyle(Paint.Style.FILL);

        this.setFocusable(true);
        this.setScaleType(ScaleType.CENTER_INSIDE);
        setClickable(true);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, width, height, buttonPaint);

        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

}
