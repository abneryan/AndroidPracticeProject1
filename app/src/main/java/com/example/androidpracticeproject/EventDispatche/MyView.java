package com.example.androidpracticeproject.EventDispatche;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class MyView extends View {
    private static final String TAG = "MyView";

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(getClass().getSimpleName(),"dispatchTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(getClass().getSimpleName(),"dispatchTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(getClass().getSimpleName(),"dispatchTouchEvent:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(getClass().getSimpleName(),"dispatchTouchEvent:ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(getClass().getSimpleName(),"onTouchEvent:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(getClass().getSimpleName(),"onTouchEvent:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(getClass().getSimpleName(),"onTouchEvent:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(getClass().getSimpleName(),"onTouchEvent:ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }
}
