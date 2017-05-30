package com.anwesome.ui.foursquareview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by anweshmishra on 31/05/17.
 */

public class FourSquareView extends View {
    private int time = 0,w,h;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int firstColor = Color.parseColor("#009688"),secondColor = Color.parseColor("#00BCD4"),thirdColor = Color.parseColor("#1E88E5"),fourthColor = Color.parseColor("#f44336");
    public FourSquareView(Context context) {
        super(context);
    }
    public void setFirstColor(int firstColor) {
        this.firstColor = firstColor;
    }
    public void setSecondColor(int secondColor) {
        this.secondColor = secondColor;
    }
    public void setThirdColor(int thirdColor) {
        this.thirdColor = thirdColor;
    }
    public void setFourthColor(int fourthColor) {
        this.fourthColor = fourthColor;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        time++;
    }
    public void update(float factor) {
        postInvalidate();
    }
    private class Square {
        private float x,y,size,scale = 0;
        private int color;
        public Square(float x,float y,int color) {
            this.x = x;
            this.y = y;
            this.size = w/2;
            this.color = color;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            canvas.save();
            canvas.scale(scale,scale);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(color);
            canvas.drawRect(new RectF(0,0,w,h),paint);
            canvas.restore();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.GRAY);
            canvas.drawRect(new RectF(0,0,w,h),paint);
            canvas.restore();
        }
        public void update(float factor) {
            scale = factor;
        }
        public boolean handleTap(float x,float y) {
            return x>=this.x && x<=this.x+size && y>=this.y && y<=this.y+size;
        }
    }
}
