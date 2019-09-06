package com.example.demo3.ioc.component;

import android.app.Application;
import android.os.Handler;

import com.baidu.tts.client.SpeechSynthesizer;
import com.blankj.utilcode.util.SPUtils;
import com.example.demo3.ioc.module.AppModule;
import com.example.demo3.ioc.qualifier.AsyncHandler;
import com.example.demo3.ioc.qualifier.SyncHandler;
import com.example.demo3.remote.Api;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(Application application);

    Api getApi();

    SPUtils getSPUtils();

    @AsyncHandler
    Handler getHandle();

    @SyncHandler
    Handler getSyncHandle();

    Application getApp();

    Gson getGson();

    SpeechSynthesizer getSpeechSynthesizer();
}