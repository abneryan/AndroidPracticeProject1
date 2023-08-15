package com.example.androidpracticeproject.reflect;

import static com.example.common.constant.ARouterConstants.REFLECT_ACTIVITY;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.androidpracticeproject.R;
import com.example.reflect.InjectUtil;
import com.example.reflect.OnClick;
import com.example.reflect.OnLongClick;

@Route(path = REFLECT_ACTIVITY)
public class ReflectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        InjectUtil.injectEvent(this);
    }

    //@OnClick({R.id.tv_1, R.id.tv_2})
    public void onClick(View view){
        if (view.getId() == R.id.tv_1) {
            Log.d("YGQ", "tv_1 onClick");
        } else if(view.getId() == R.id.tv_2){
            Log.d("YGQ", "tv_1 onClick");
        }
    }
   // @OnLongClick({R.id.tv_1,R.id.tv_2})
    public void onLongClick(View view){
        if (view.getId() == R.id.tv_1) {
            Log.d("YGQ", "tv_1 onLongClick");
        } else if(view.getId() == R.id.tv_2){
            Log.d("YGQ", "tv_1 onLongClick");
        }
    }
}