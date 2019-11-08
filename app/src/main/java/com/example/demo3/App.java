package com.example.demo3;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.demo3.ioc.component.AppComponent;
import com.example.demo3.ioc.component.DaggerAppComponent;
import com.example.demo3.ioc.module.AppModule;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import androidx.multidex.MultiDexApplication;
import me.yokeyword.fragmentation.Fragmentation;

public class App extends MultiDexApplication {
    private static AppComponent mAppComponent;

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new ClassicsHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) ->
                new ClassicsFooter(context).setDrawableSize(20)
        );
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);
        Fragmentation.builder().install();
        initARouter();
    }

    private void initARouter() {
        if (isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private boolean isDebug() {
        return true;
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}
