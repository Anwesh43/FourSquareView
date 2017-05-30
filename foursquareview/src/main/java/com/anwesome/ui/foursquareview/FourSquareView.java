package com.anwesome.ui.foursquareview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 31/05/17.
 */

public class FourSquareView extends View {
    private int time = 0,w,h;
    private Square tappedSquare = null;
    private ConcurrentLinkedQueue<Square> squares = new ConcurrentLinkedQueue<>();
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
            float x = 0,y = 0;
            int colors[] = {firstColor,secondColor,thirdColor,fourthColor};
            for(int i=0;i<4;i++) {
                squares.add(new Square(x,y,colors[i]));
                x += w/2;
                if(x >= w) {
                    x = 0;
                    y += h/2;
                }
            }
        }
        for(Square square:squares) {
            square.draw(canvas);
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && tappedSquare == null) {
            for(Square square:squares) {
                if(square.handleTap(event.getX(),event.getY())) {
                    tappedSquare = square;
                }
            }
        }
        return true;
    }
    public void update(float factor) {
        if(tappedSquare != null) {
            tappedSquare.update(factor);
        }
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
        public int hashCode() {
            return (int)(x+y+color);
        }
    }
}
