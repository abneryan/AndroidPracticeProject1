package com.example.androidpracticeproject.annotation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidpracticeproject.R;
import com.example.annotation.MyClass;

@MyClass
public class AnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
    }
}