package com.anwesome.ui.foursquareview;

import android.app.Activity;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 31/05/17.
 */

public class FourSquareViewList {
    private Activity activity;
    private ScrollView scrollView;
    private boolean isShown = false;
    public FourSquareViewList(Activity activity) {
        this.activity = activity;
        this.scrollView = new ScrollView(activity);
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
    public void addView(int...colors) {
        if(!isShown) {
        }
    }
}
