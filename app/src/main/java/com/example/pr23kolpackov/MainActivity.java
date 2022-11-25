package com.example.pr23kolpackov;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat lSwipeDetector;

    RelativeLayout main_layout;
    TextView tvTxt;
    int i;

    private static final int SWIPE_MIN_DISTANCE = 130;
    private static final int SWIPE_MAX_DISTANCE = 300;
    private static final int SWIPE_MIN_VELOCITY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i = 1;
        lSwipeDetector = new GestureDetectorCompat(this, new MyGestureListener());
        main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        tvTxt = (TextView) findViewById(R.id.tvTxt);
        tvTxt.setText("" + i);

        main_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return lSwipeDetector.onTouchEvent(event);
            }
        });
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_DISTANCE)
                return false;
            if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_MIN_VELOCITY) {
                i++;
                tvTxt.setText("" + i);
            }
            return false;
        }
    }
}
