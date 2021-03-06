package com.example.demo3.remote;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static OkHttpClient okHttpClient = null;

    private RetrofitHelper() {
    }

    public static Retrofit getRetrofit(String baseUrl,Context context) {
        initOkHttp(context);
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static OkHttpClient initOkHttp(Context context) {
        if (okHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (okHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    builder.addInterceptor(loggingInterceptor);
                    /*builder.addInterceptor(chain -> {
                        Request originalRequest = chain.request();
                        Request authorised = originalRequest.newBuilder().header("Cookie", cookie).build();
                        return chain.proceed(authorised);
                    });*/
                    File cacheFile = new File(context.getCacheDir(), "http");
                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
                    builder.cache(cache);
                    builder.connectTimeout(10, TimeUnit.SECONDS);
                    builder.readTimeout(20, TimeUnit.SECONDS);
                    builder.writeTimeout(20, TimeUnit.SECONDS);
                    builder.retryOnConnectionFailure(true);
                    okHttpClient = builder.build();
                }
            }
        }
        return okHttpClient;
    }

}
