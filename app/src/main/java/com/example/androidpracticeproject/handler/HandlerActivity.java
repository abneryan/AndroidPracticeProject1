package com.example.androidpracticeproject.handler;

import static com.example.common.constant.ARouterConstants.APP_HANDLERACTIVITY;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.androidpracticeproject.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Route(path = APP_HANDLERACTIVITY)
public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "HandlerActivity";
    private Handler myThreadHandler;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage---threadname:" + Thread.currentThread().getName());
            Log.d(TAG, "handleMessage---msg.what:" + msg.what);
        }
    };
    private View view;
    private View begin;

    public void startScaleAnim() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1,0.8f,1,0.8f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(100);
        scaleAnimation.setFillAfter(true);
        begin.startAnimation(scaleAnimation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        view = findViewById(R.id.creat_looper);
        begin = findViewById(R.id.btn_begin);
        begin.setOnClickListener(view ->startScaleAnim());
        Message message1 = Message.obtain();
        message1.obj = "abner1";
        message1.what = 0;
        handler.sendMessageDelayed(message1,2000);

        Message message = Message.obtain();
        message.obj = "abner";
        message.what = 1;
        handler.sendMessage(message);
        view.setOnClickListener(this);
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "post");
            }
        });
        Log.d(TAG, "onCreate()");
        MessageQueue messageQueue = getMainLooper().getQueue();
        Log.d(TAG,"queun:"+getMainLooper().getQueue());

    }

    @Override
    protected void onResume() {
        super.onResume();
        Looper.myQueue().addIdleHandler(() -> {
            //延迟加载
            //1 . 拿到OkHttpClient对象
            OkHttpClient client = new OkHttpClient();
            //2 . 构造Request对象
            Request request = new Request.Builder()
                    .get()
                    .url("https:www.baidu.com")
                    .build();
            //3, 将Request封装为Call
            Call call = client.newCall(request);

            //4, 异步调用,并设置回调函数
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(HandlerActivity.this, "Post Failed", Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"faild ---e:" + e.getStackTrace());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String res = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG,"onResponse:" + res);
                            Toast.makeText(HandlerActivity.this, "onResponse:" + res, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            return false;
        });
    }

    @Override
    public void onClick(View v) {

        new Thread("thread-1"){
            @Override
            public void run() {
                super.run();
                Log.d(TAG, "Thread---run--start---threadname:" + Thread.currentThread().getName());
                Looper.prepare();
                Handler handler1 = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        Log.d(TAG, "Thread---handle---threadname:" + Thread.currentThread().getName());
                        Looper.myLooper().quit();//在子线程中如果手动为其创建一个Looper,那么所有事情完成之后应该调用quit()
                                                    //方法来终止消息循环，否则这个子线程就会一直处于等待状态，而如果退出Looper
                                                    //以后，这个线程就会立刻终止，因此建议不需要的时候终止Looper
                        Looper.myLooper().quitSafely();
                    }
                };
                Message message = Message.obtain();
                message.obj ="test";
                handler1.sendMessage(message);
                Looper.loop();
               /* 注意：如果在handleMessage 中处理完时间之后没有调用Looper.myLooper().quit(); 或者
                Looper.myLooper().quitSafely()方法，子线程会处于阻塞状态，则Log.d(TAG, "Thread---handle---end");
                log不会执行。*/
                Log.d(TAG, "Thread---handle---end");
            }
        }.start();

        /*HandlerThread handlerThread = new HandlerThread("myThread");
        handlerThread.start();
        myThreadHandler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "myThreadHandler---handleMessage---threadName:" + Thread.currentThread().getName());

            }
        };
        Message message = Message.obtain();
        message.obj = "abneryan";
        myThreadHandler.sendMessage(message);
        myThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "myThreadHandler---post---threadName:" + Thread.currentThread().getName());
            }
        });*/
    }
}
