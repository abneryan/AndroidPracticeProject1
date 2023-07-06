package com.example.service;

import static com.example.common.constant.ARouterConstants.SERVICE_ACTIVITY;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.service.databinding.ActivityServiceBinding;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/5 16:50
 * @Description:
 */
@Route(path = SERVICE_ACTIVITY)
public class ServiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityServiceBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_service);
        viewDataBinding.commonTitel.tvTitel.setText("服务端");
        viewDataBinding.tvContent.setText("这是服务端");
    }
}
