package com.example.demo3.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo3.base.BaseWebViewFragment;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import es.dmoral.toasty.Toasty;

@Route(path = "/app/CockpitFragment")
public class CockpitFragment extends BaseWebViewFragment {
    private static final String TAG = "CockpitFragment";

    public static CockpitFragment newInstance() {
        return new CockpitFragment();
    }

    @Override
    protected void inject() {
        super.inject();
    }

    @Override
    protected void initData() {
        super.initData();
        mWebView.loadUrl("http://192.168.199.22:57772/dthealth/web/scripts/dhemrjs/weui/demos/actionsheet.csp");
    }

    @Override
    public void handler(String data, CallBackFunction function) {
        Toasty.info(_mActivity,"jsToJava:"+data).show();
    }

    @Override
    public <E> void handleActivityResult(E e) {
        super.handleActivityResult(e);
        sendMessageToJs(e);
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        super.setSwipeBackEnable(false);
    }
}
