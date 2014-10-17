package com.fiumelabs.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by paulkuzich on 10/16/14.
 */
public class WideButton extends ImageView {

    public static final String TAG = WideButton.class.getSimpleName();

    private final String TEAL_COLOR = "#0099FF";

    private final int arcRadiusX = 3;
    private final int arcRadiusY = 5;
    private final int animMargin = 2;
    private final int margin = 2+animMargin;
    private final int textMargin = 4+margin;
    private final int MAX_HEIGHT = 60;

    private int width;
    private int height;

    private Paint bigTextPaint;
    private int bigTextSize;

    private RectF buttonRect;
    private Paint buttonPaint;
    private int buttonColor;

    private Paint borderPaint;
    private RectF borderRect;

    public WideButton(Context context){
        super(context);
        initialize(context, null);

    }

    public WideButton(Context context, AttributeSet attributes){
        super(context);
        initialize(context, attributes);

    }

    protected void initialize(Context context, AttributeSet attr){

        buttonRect = new RectF();
        borderRect = new RectF();

        buttonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        buttonPaint.setStyle(Paint.Style.FILL);

        this.setFocusable(true);
        this.setScaleType(ScaleType.CENTER_INSIDE);
        setClickable(true);

        buttonColor = Color.parseColor(TEAL_COLOR);

        if (attr != null) {
            final TypedArray array = context.obtainStyledAttributes(attr, R.styleable.WideButton);
            buttonColor = array.getColor(R.styleable.WideButton_color_wide_button, buttonColor);
        }

        buttonPaint.setShader(new LinearGradient(0, 0, 0, height, getBorderColor(buttonColor), buttonColor, Shader.TileMode.MIRROR));

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.FILL);
        borderPaint.setColor(getBorderColor(buttonColor));

        bigTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bigTextPaint.setStyle(Paint.Style.FILL);
        bigTextPaint.setColor(Color.WHITE);
        bigTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        bigTextPaint.setTextSize((int)(height * 0.6));

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(borderRect, arcRadiusX, arcRadiusY, borderPaint);
        canvas.drawRoundRect(buttonRect, arcRadiusX, arcRadiusY, buttonPaint);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        updateDimensions();
    }

    protected void updateDimensions(){
        buttonRect.set(0+margin,0+margin,width-margin,height-margin);
        borderRect.set(0,0,width,height);

        buttonPaint.setShader(new LinearGradient(0, 0, 0, height, getBorderColor(buttonColor), buttonColor, Shader.TileMode.MIRROR));

        int ht = height;
        if(ht > MAX_HEIGHT)
            ht = MAX_HEIGHT;
        bigTextSize = (int)(ht * 0.8);
        bigTextPaint.setTextSize(bigTextSize);
    }

    protected int getBorderColor(int color){
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.95f; // value component
        return Color.HSVToColor(hsv);
    }



}
