package com.anwesome.ui.foursquareview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 31/05/17.
 */

public class FourSquareViewList {
    private Activity activity;
    private ScrollView scrollView;
    private ListLayout listLayout;
    private boolean isShown = false;
    public FourSquareViewList(Activity activity) {
        this.activity = activity;
        this.scrollView = new ScrollView(activity);
        listLayout = new ListLayout(activity);
        scrollView.addView(listLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
    public void addView(int...colors) {
        if(!isShown) {
            listLayout.addView(colors);
        }
    }
    private class ListLayout extends ViewGroup {
        private int w,h;
        public ListLayout(Context context) {
            super(context);
            initDimension(context);
        }
        private void initDimension(Context context) {
            DisplayManager displayManager = (DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
            Display display = displayManager.getDisplay(0);
            if(display != null) {
                Point size = new Point();
                display.getRealSize(size);
                w = size.x;
                h = size.y;
            }
        }
        public void onMeasure(int wspec,int hspec) {
            int hMax = h/20;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                measureChild(child,wspec,hspec);
                hMax += (child.getMeasuredHeight()+h/20);
            }
            setMeasuredDimension(w,hMax);
        }
        public void addView(int ...colors) {
            FourSquareView fourSquareView = new FourSquareView(getContext());
            fourSquareView.setColors(colors);
            addView(fourSquareView,new LayoutParams(w/2,w/2));
            requestLayout();
        }
        public void onLayout(boolean reloaded,int a,int b,int wa,int ha) {
            int x = w/4,y = h/20;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                child.layout(x,y,x+child.getMeasuredWidth(),y+child.getMeasuredHeight());
                y += (child.getMeasuredHeight()+h/20);
            }
        }
    }
}
