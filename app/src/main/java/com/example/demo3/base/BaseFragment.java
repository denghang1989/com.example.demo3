package com.example.demo3.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo3.R;
import com.example.demo3.event.NetConnectChangedEvent;
import com.example.demo3.event.WifiChangedEvent;
import com.example.demo3.status.OnNetworkListener;
import com.example.demo3.status.OnRetryListener;
import com.example.demo3.status.OnShowHideViewListener;
import com.example.demo3.status.StateLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

public abstract class BaseFragment<T extends ViewDataBinding> extends SwipeBackFragment implements OnRetryListener,OnNetworkListener, OnShowHideViewListener {
    private static final String TAG = "BaseFragment";

    protected StateLayoutManager statusLayoutManager;
    protected T                  mDataBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        init();
    }

    protected void inject() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        if (statusLayoutManager == null) {
            initStatusLayout(mDataBinding.getRoot());
        }
        return statusLayoutManager.getRootLayout();
    }

    public abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initStatusLayout(View view) {
        statusLayoutManager = StateLayoutManager.newBuilder(_mActivity,false)
                .setContentView(view)
                .emptyDataView(R.layout.activity_emptydata)
                .errorView(R.layout.activity_error)
                .loadingView(R.layout.activity_loading2)
                .netWorkErrorView(R.layout.activity_networkerror)
                .onRetryListener(this)
                .onNetworkListener(this)
                .onShowHideViewListener(this)
                .build();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initEvent();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    protected void init() {

    }

    protected void initEvent() {

    }

    protected void initData() {

    }

    protected void initView() {

    }

    public <E> void handleActivityResult(E e) {

    }

    /**
     * 加载成功
     */
    protected void showContent() {
        if (statusLayoutManager != null) {
            statusLayoutManager.showContent();
        }
    }

    /**
     * 加载无数据
     */
    protected void showEmptyData() {
        if (statusLayoutManager != null) {
            statusLayoutManager.showEmptyData();
        }
    }

    /**
     * 加载异常
     */
    protected void showError() {
        if (statusLayoutManager != null) {
            statusLayoutManager.showError();
        }
    }

    /**
     * 加载网络异常
     */
    protected void showNetWorkError() {
        if (statusLayoutManager != null) {
            statusLayoutManager.showNetWorkError();
        }
    }

    /**
     * 加载loading
     */
    protected void showLoading() {
        if (statusLayoutManager != null) {
            statusLayoutManager.showLoading();
        }
    }

    /**
     * 注册eventbus
     */
    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    /**
     * 取消注册eventbus
     */
    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onNetwork() {
        Log.d(TAG, "onNetwork: ");
        showLoading();
        initData();
    }

    @Override
    public void onRetry() {
        Log.d(TAG, "onRetry: ");
        showLoading();
        initData();
    }

    @Override
    public void onShowView(View view, int id) {
        Log.d(TAG, "onShowView: "+id);
    }

    @Override
    public void onHideView(View view, int id) {
        Log.d(TAG, "onHideView: "+id);
    }

    /**
     * @param event
     * wifi 切换时候的回调函数
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleWifiChanged(WifiChangedEvent event){
        if (!event.wifiConnected) {
            showNetWorkError();
        } else {
            onNetwork();
        }
    }

    /**
     * @param event
     * 网络 变化的回调函数
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleNetConnectChanged(NetConnectChangedEvent event){
        if (!event.isConnected) {
            showNetWorkError();
        } else {
            onNetwork();
        }
    }
}
