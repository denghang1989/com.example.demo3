package com.example.demo3.ioc.module;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;

import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;
import com.blankj.utilcode.util.SPUtils;
import com.example.demo3.ioc.qualifier.AsyncHandler;
import com.example.demo3.ioc.qualifier.SyncHandler;
import com.example.demo3.remote.Api;
import com.example.demo3.remote.RetrofitHelper;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApp;

    public AppModule(Application app) {
        mApp = app;
    }

    @Provides
    @Singleton
    Application provideApp() {
        return mApp;
    }

    @Provides
    @Singleton
    Api provideApi() {
        return RetrofitHelper.getRetrofit(Api.BASE_URL, mApp).create(Api.class);
    }

    @Provides
    @Singleton
    SPUtils provideSp() {
        return SPUtils.getInstance();
    }

    @Provides
    @Singleton
    @AsyncHandler
    Handler provideAsyncHandle() {
        HandlerThread handlerThread = new HandlerThread("app");
        handlerThread.start();
        return new Handler(handlerThread.getLooper());
    }

    @Provides
    @Singleton
    @SyncHandler
    Handler provideSyncHandle(){
        return new Handler();
    }

    @Provides
    @Singleton
    Gson provideGson(){
        return new Gson();
    }

    @Provides
    @Singleton
    SpeechSynthesizer provideSpeechSynthesizer(){
        SpeechSynthesizer mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(mApp);
        mSpeechSynthesizer.setAppId("17157279");
        mSpeechSynthesizer.setApiKey("h241rNsg9eLLSUZR29Pcz1nC","nyTtTNzimZhvj8w5If3cG3yqxEIUtZWl");
        mSpeechSynthesizer.auth(TtsMode.MIX);
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        mSpeechSynthesizer.initTts(TtsMode.MIX);
        return mSpeechSynthesizer;
    }
}
