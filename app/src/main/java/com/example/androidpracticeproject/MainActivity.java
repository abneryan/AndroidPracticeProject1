package com.example.androidpracticeproject;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.MessageQueue;
import android.os.Trace;
import android.util.Log;
import android.util.SparseArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.androidpracticeproject.EventDispatche.EventDispatchMainActivity;
import com.example.androidpracticeproject.adapter.MainBussinessAdapter;
import com.example.androidpracticeproject.annotation.AnnotationActivity;
import com.example.androidpracticeproject.annotation.ArouterAnnotationActivity;
import com.example.androidpracticeproject.annotation.ArouterAnnotationActivity$$$ARouter;
import com.example.androidpracticeproject.callback.ItemClickListener;
import com.example.androidpracticeproject.databinding.ActivityMainBinding;
import com.example.common.constant.ARouterConstants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    private ActivityMainBinding mainBinding;
    private String[] mBusinessList;
    private MainBussinessAdapter mainBussinessAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SparseArray<String> sParseArray = new SparseArray<>();
        sParseArray.put(1,"hangman");
        mBusinessList = getResources().getStringArray(R.array.business);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setLifecycleOwner(this);
        mainBinding.commonTitel.ivBack.setOnClickListener(v -> finish());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainBinding.recyclerview.setLayoutManager(linearLayoutManager);
        mainBussinessAdapter = new MainBussinessAdapter(this, mBusinessList, this);
        mainBinding.recyclerview.setAdapter(mainBussinessAdapter);
        mainBinding.commonTitel.ivBack.post(()-> Log.d("YGQ1",mainBinding.commonTitel.ivBack.getHeight()+""));
        final Handler handler = new Handler();
        handler.post(()-> Log.d("YGQ2",mainBinding.commonTitel.ivBack.getHeight()+""));
        mainBinding.recyclerview.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        new Thread(){
            @Override
            public void run() {
                super.run();
                mainBinding.commonTitel.tvTitel.setText("dddddd");
            }
        }.start();

       /* try {
            Thread.sleep(6_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        getMainLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                return false;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Trace.endSection();
    }


    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 3:
                //通过路由跳转到服务页面
                ARouter.getInstance()
                        .build(ARouterConstants.SERVICECLIENT_ACTIVITY)
                        .navigation();
                break;
            case 4:
                //通过路由跳转到首页组件的页面（但没有依赖首页组件）
                ARouter.getInstance()
                        .build(ARouterConstants.HOME_ACTIVITY)
                        .navigation();
                break;
            case 5:
                //Handler
                ARouter.getInstance()
                        .build(ARouterConstants.APP_HANDLERACTIVITY)
                        .navigation();
                break;
            case 6:
              //annotation/APT
//                final Intent intent = new Intent(this, AnnotationActivity.class);
                final Class targetClass = ArouterAnnotationActivity$$$ARouter.findTargetClass("app/ArouterAnnotationActivity");
                final Intent intent = new Intent(this, targetClass);
                startActivity(intent);
                break;
            case 7://reflect
                ARouter.getInstance()
                        .build(ARouterConstants.REFLECT_ACTIVITY)
                        .navigation();
                break;
            case 8://rxjava
                ARouter.getInstance()
                        .build(ARouterConstants.RXJAVA_USE_ACTIVITY)
                        .navigation();
                break;
            case 9://事件分发机制
                final Intent intent1 = new Intent(this, EventDispatchMainActivity.class);
                startActivity(intent1);
                break;

        }
    }
}