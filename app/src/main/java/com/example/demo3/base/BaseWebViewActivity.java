package com.example.demo3.base;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.blankj.utilcode.util.GsonUtils;
import com.example.demo3.R;
import com.example.demo3.databinding.FragmentWebBinding;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;

public class BaseWebViewActivity extends BaseActivity<FragmentWebBinding> implements OnRefreshListener, BridgeHandler {
    protected BridgeWebView mWebView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_web;
    }

    @Override
    protected void initView() {
        initWebViewSettings();
    }

    private void initWebViewSettings() {
        mWebView = mDataBinding.webView;
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSetting.setAllowFileAccessFromFileURLs(true);
        webSetting.setAllowUniversalAccessFromFileURLs(true);
        mWebView.setWebViewClient(new BridgeWebViewClient(mWebView) {
            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                onHandleError(webView, i, s, s1);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                mDataBinding.progressBar.setProgress(i);
                if (i == 100) {
                    mDataBinding.progressBar.setVisibility(View.GONE);
                }
            }

        });

    }

    protected void onHandleError(WebView webView, int i, String s, String s1) {

    }


    @Override
    protected void initEvent() {
        mDataBinding.refreshLayout.setOnRefreshListener(this);
        mWebView.registerHandler("jsToJava", this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
            mWebView.resumeTimers();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
            mWebView.pauseTimers();
        }
    }

    @Override
    public void onDestroy() {
        ViewGroup parent = (ViewGroup) mWebView.getParent();
        if (parent != null) {
            parent.removeView(mWebView);
        }
        if (mWebView != null) {
            mWebView.loadUrl("about:blank");
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (mWebView != null) {
            mWebView.reload();
        }
        hideHeaderProgress();
    }

    protected void showHeaderProgress() {
        mDataBinding.refreshLayout.autoRefresh();
    }

    protected void hideHeaderProgress() {
        mDataBinding.refreshLayout.finishRefresh(100);
    }

    protected void hideFooterProgress() {
        mDataBinding.refreshLayout.finishLoadMore(100);
    }

    /**
     * @param t
     * @param <T>
     * @desc 发送数据到js层，需要与web层定义数据接口
     */
    public <T> void sendMessageToJs(T t) {
        String json = GsonUtils.toJson(t);
        mWebView.callHandler("javaToJs", json, null);
    }

    /**
     * @param data     js
     * @param function 回调接口
     * @desc 返回数据到native层
     */
    @Override
    public void handler(String data, CallBackFunction function) {

    }
}
