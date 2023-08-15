package com.example.rxjava.use.utils;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Auther: yanguoqing
 * @Date: 2023/8/12 01:22
 * @Description:
 */
public class HttpUtil {
    private static String BASE_URL= "https://www.wanandroid.com/";
    public static void setBaseUrl(String baseUrl){
        BASE_URL = baseUrl;
    }

    public static Retrofit getOnlineRetrofit(){
        final OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        final OkHttpClient okHttpClient = builder
                .readTimeout(10000, TimeUnit.SECONDS)
                .connectTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                //json 解析工具
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                //rxjava 处理工具
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
