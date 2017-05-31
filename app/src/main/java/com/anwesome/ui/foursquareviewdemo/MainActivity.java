package com.anwesome.ui.foursquareviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anwesome.ui.foursquareview.FourSquareView;
import com.anwesome.ui.foursquareview.FourSquareViewList;
import com.anwesome.ui.foursquareview.OnCompleteFillListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FourSquareViewList fourSquareViewList = new FourSquareViewList(this);
        for(int i=0;i<12;i++) {
            final int index = (i+1);
            fourSquareViewList.addView(new OnCompleteFillListener() {
                @Override
                public void onFill() {
                    Toast.makeText(MainActivity.this, String.format("%d is filled",index), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onEmpty() {
                    Toast.makeText(MainActivity.this, String.format("%d is empty",index), Toast.LENGTH_SHORT).show();
                }
            });
        }
        fourSquareViewList.show();
    }
}
