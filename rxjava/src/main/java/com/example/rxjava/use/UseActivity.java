package com.example.rxjava.use;

import static com.example.common.constant.ARouterConstants.RXJAVA_USE_ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.rxjava.R;
import com.example.rxjava.databinding.ActivityUseBinding;
import com.example.rxjava.use.api.WanAndroidApi;
import com.example.rxjava.use.bean.ProjectBean;
import com.example.rxjava.use.bean.ProjectItemBean;
import com.example.rxjava.use.utils.HttpUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

@Route(path = RXJAVA_USE_ACTIVITY)
public class UseActivity extends AppCompatActivity {
    private ActivityUseBinding activityUseBinding;
    private WanAndroidApi   wanAndroidApi;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wanAndroidApi = HttpUtil.getOnlineRetrofit().create(WanAndroidApi.class);
        activityUseBinding = DataBindingUtil.setContentView(this, R.layout.activity_use);
        activityUseBinding.tvProject.setOnClickListener(v->{
            getProject();
        });

        activityUseBinding.tvProjectitem.setOnClickListener(v ->{
            getProjectItem();
        });
        RxView.clicks(activityUseBinding.tvShake)
                .throttleFirst(2000, TimeUnit.MICROSECONDS)
                .observeOn(io.reactivex.schedulers.Schedulers.io())
                .flatMap(new Function<Object, ObservableSource<ProjectBean>>() {
                    @Override
                    public ObservableSource<ProjectBean> apply(Object o) throws Exception {
                        return wanAndroidApi.getProject();
                    }
                }).flatMap(new Function<ProjectBean, ObservableSource<ProjectBean.Data>>() {
                    @Override
                    public ObservableSource<ProjectBean.Data> apply(ProjectBean projectBean) throws Exception {
                        return Observable.fromIterable(projectBean.getData());
                    }
                }).flatMap(new Function<ProjectBean.Data, ObservableSource<ProjectItemBean>>() {
                    @Override
                    public ObservableSource<ProjectItemBean> apply(ProjectBean.Data data) throws Exception {
                        return wanAndroidApi.getProjectItem(1, data.getId());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.d("YGQ", data.toString());
                });

       /* RxView.clicks(activityUseBinding.tvShake)
                .throttleFirst(2000, TimeUnit.MICROSECONDS)
                .observeOn(io.reactivex.schedulers.Schedulers.io())
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {
                        wanAndroidApi.getProject()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new io.reactivex.rxjava2.functions.Consumer<ProjectBean>() {
                                    @Override
                                    public void accept(ProjectBean projectBean) throws Throwable {
                                        for (ProjectBean.Data dataBean : projectBean.getData()) {
                                            wanAndroidApi.getProjectItem(1,dataBean.getId())
                                                    .subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribe(data -> {
                                                        Log.d("YGQ",data.toString());
                                                    });
                                        }

                                    }
                                });
                    }
                });*/
    }

    private void getProjectItem() {
        wanAndroidApi.getProjectItem(1,294)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.d("YGQ",data.toString());
                });
    }

    private void getProject() {
        wanAndroidApi.getProject()
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.d("YGQ",data.toString());
                });
    }
}