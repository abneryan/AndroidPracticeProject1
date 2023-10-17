package com.example.androidpracticeproject.annotation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidpracticeproject.R;
import com.example.annotation.MyARouter;
import com.example.annotation.MyClass;

@MyARouter(path = "app/ArouterAnnotationActivity" )
public class ArouterAnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter_annotation);
    }
}