package com.fiumelabs.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by paulkuzich on 10/16/14.
 */
public class WideButton extends ImageView {

    public static final String TAG = WideButton.class.getSimpleName();

    private int width;
    private int height;

    private Paint buttonPaint;
    private int buttonColor;


    public WideButton(Context context){
        super(context);
        initialize(context, null);

    }

    public WideButton(Context context, AttributeSet attributes){
        super(context);
        initialize(context, attributes);

    }

    protected void initialize(Context context, AttributeSet attr){

        buttonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        buttonPaint.setStyle(Paint.Style.FILL);

        this.setFocusable(true);
        this.setScaleType(ScaleType.CENTER_INSIDE);
        setClickable(true);

        buttonColor = Color.RED;

        if (attr != null) {
            final TypedArray array = context.obtainStyledAttributes(attr, R.styleable.WideButton);
            buttonColor = array.getColor(R.styleable.WideButton_color_wide_button, buttonColor);
        }

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
