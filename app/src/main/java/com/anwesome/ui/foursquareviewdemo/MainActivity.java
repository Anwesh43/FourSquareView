package com.anwesome.ui.foursquareviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.anwesome.ui.foursquareview.FourSquareView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FourSquareView fourSquareView = new FourSquareView(this);
        addContentView(fourSquareView,new ViewGroup.LayoutParams(600,600));
    }
}
