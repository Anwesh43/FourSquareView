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
    private AnimationHandler animationHandler;
    public void setColors(int...colors) {
        for(int i=0;i<colors.length;i++) {
            this.colors[i] = colors[i];
        }
    }
    private ConcurrentLinkedQueue<Square> squares = new ConcurrentLinkedQueue<>();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int colors[] = {Color.parseColor("#009688"),Color.parseColor("#00BCD4"),Color.parseColor("#1E88E5"),Color.parseColor("#f44336")};
    public FourSquareView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = (canvas.getWidth()*9)/10;
            h = (canvas.getHeight()*9)/10;
            float x = 0,y = 0;
            for(int i=0;i<4;i++) {
                squares.add(new Square(x,y,colors[i]));
                x += w/2;
                if(x >= w) {
                    x = 0;
                    y += h/2;
                }
            }
            animationHandler = new AnimationHandler();
        }
        for(Square square:squares) {
            square.draw(canvas);
        }
        time++;
        animationHandler.animate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            for(Square square:squares) {
                if(square.handleTap(event.getX(),event.getY())) {
                    animationHandler.addSquare(square);
                    break;
                }
            }
        }
        return true;
    }
    private class Square {
        private float x,y,size,scale = 0,dir = 0;
        private int color;
        public Square(float x,float y,int color) {
            this.x = x;
            this.y = y;
            this.size = w/2;
            this.color = color;
        }
        public void draw(Canvas canvas) {
            paint.setStrokeWidth(size/12);
            canvas.save();
            canvas.translate(x,y);
            canvas.save();
            canvas.scale(scale,scale);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(color);
            canvas.drawRect(new RectF(0,0,size,size),paint);
            canvas.restore();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.GRAY);
            canvas.drawRect(new RectF(0,0,size,size),paint);
            canvas.restore();
        }
        public void update() {
            scale += 0.2f*dir;
            if(scale > 1) {
                dir = 0;
                scale = 1;
            }
            if(scale < 0) {
                dir = 0;
                scale = 0;
            }
        }
        public boolean stopped() {
            return dir == 0;
        }
        public boolean handleTap(float x,float y) {
            boolean condition =  x>=this.x && x<=this.x+size && y>=this.y && y<=this.y+size && dir == 0;
            if(condition) {
                if(scale == 0) {
                    dir = 1;
                }
                else {
                    dir = -1;
                }
            }
            return condition;
        }
        public int hashCode() {
            return (int)(x+y+color);
        }
        public float getScale() {
            return scale;
        }
    }
    private class AnimationHandler {
        private boolean isAnimated = false;
        private ConcurrentLinkedQueue<Square> tappedSquares = new ConcurrentLinkedQueue<>();
        public void animate() {
            if(isAnimated) {
                for (Square square : tappedSquares) {
                    square.update();
                    if(square.stopped()) {
                        tappedSquares.remove(square);
                        if(tappedSquares.size() == 0) {
                            isAnimated = false;
                        }
                    }
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                } catch (Exception ex) {

                }
            }
        }
        public void addSquare(Square square) {
            tappedSquares.add(square);
            if(!isAnimated) {
                isAnimated = true;
                postInvalidate();
            }
        }
    }
}
