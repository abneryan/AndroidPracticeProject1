package com.example.androidpracticeproject.EventDispatche;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidpracticeproject.R;


public class EventDispatchMainActivity extends AppCompatActivity {

    private String TAG = "EventDispatchMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch_main);
        View motionEvent = findViewById(R.id.motion_event);
        View ketEvent = findViewById(R.id.key_event);

        motionEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventDispatchMainActivity.this,MotionEventActivity.class);
                startActivity(intent);
            }
        });

        ketEvent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(EventDispatchMainActivity.this,KeyActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }
}