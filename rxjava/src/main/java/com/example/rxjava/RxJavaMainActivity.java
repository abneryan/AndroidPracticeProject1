package com.example.rxjava;

import static com.example.common.constant.ARouterConstants.RXJAVA_MAIN_ACTIVITY;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.rxjava.databinding.ActivityRxjavaBinding;

import org.reactivestreams.Subscriber;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Auther: yanguoqing
 * @Date: 2023/8/10 17:42
 * @Description:
 */
@Route(path = RXJAVA_MAIN_ACTIVITY)
public class RxJavaMainActivity extends AppCompatActivity {
    private ActivityRxjavaBinding binding;
    private ProgressDialog progressDialog;
    private final static String PATH = "https://t7.baidu.com/it/u=1956604245,3662848045&fm=193&f=GIF";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rxjava);
        intView();
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                emitter.onNext(1);
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });











        Observable.just(PATH)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String path) throws Throwable {
                        final URL url = new URL(path);
                        final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                        httpURLConnection.setConnectTimeout(500);
                        final int responseCode = httpURLConnection.getResponseCode();
                        if(responseCode == HttpURLConnection.HTTP_OK){
                            final InputStream inputStream = httpURLConnection.getInputStream();
                            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            return bitmap;
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        progressDialog = new ProgressDialog(RxJavaMainActivity.this);
                        progressDialog.setTitle("开始下载");
                        progressDialog.show();
                    }

                    @Override
                    public void onNext(@NonNull Bitmap bitmap) {
                        binding.image.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                    }
                });
    }

    private void intView() {
        binding.commonTitel.tvTitel.setText(R.string.app_name);
        binding.commonTitel.ivBack.setOnClickListener(view -> finish());
    }
}
