package com.example.serviceclient;

import static com.example.common.constant.ARouterConstants.SERVICECLIENT_ACTIVITY;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.service.IRemoteService;
import com.example.service.beans.Rect;
import com.example.serviceclient.databinding.ActivityServiceclientBinding;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/5 16:50
 * @Description:
 */
@Route(path = SERVICECLIENT_ACTIVITY)
public class ServiceClientActivity extends AppCompatActivity {
    private IRemoteService iRemoteService;
    private boolean mShouldUnbind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityServiceclientBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_serviceclient);
        viewDataBinding.commonTitel.tvTitel.setText("客户端");
        viewDataBinding.tvContent.setText("这是客户端");
        viewDataBinding.tvBindservice.setOnClickListener(view ->{
            doBindService();
        });

        viewDataBinding.tvUnbindservice.setOnClickListener(view->{
           doUnbindService();
        });
        viewDataBinding.tvSetObj.setOnClickListener(view->{
            setObj2Service();
        });
    }

    void doBindService() {
        // Attempts to establish a connection with the service.  We use an
        // explicit class name because we want a specific service
        // implementation that we know will be running in our own process
        // (and thus won't be supporting component replacement by other
        // applications).
        //声明软件包可见性需求
        //在创建应用时，请务必考虑您的应用需要与之交互的设备中的其他应用。如果您的应用以 Android 11（API 级别 30）或更高版本为目标平台，在默认情况下，系统会自动让部分应用对您的应用可见，但会过滤掉其他应用。本指南将介绍如何让上述其他应用对您的应用可见。
           /* <manifest package="com.example.game">
                <queries>
                        <package android:name="com.example.store" />
                        <package android:name="com.example.services" />
                </queries>
                ...
            </manifest>*/
        Intent bindIntent = new Intent();
        bindIntent.setComponent(new ComponentName("com.example.service","com.example.service.RemoteService"));
        bindIntent.setPackage(getPackageName());
        if (bindService(bindIntent,
                serviceConnection, Context.BIND_AUTO_CREATE)) {
            mShouldUnbind = true;
        } else {
            Log.e("MY_APP_TAG", "Error: The requested service doesn't " +
                    "exist, or this client isn't allowed access to it.");
        }
    }

    void doUnbindService() {
        if (mShouldUnbind) {
            // Release information about the service's state.
            unbindService(serviceConnection);
            mShouldUnbind = false;
        }
    }

    void setObj2Service() {
        final Rect rect = new Rect();
        rect.left =1;
        rect.right =2;
        rect.top = 3;
        rect.bottom = 4;

        if (iRemoteService != null) {
            try {
                iRemoteService.setRect(rect);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iRemoteService = IRemoteService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iRemoteService = null;
        }
    };
}
