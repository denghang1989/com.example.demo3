package com.example.demo3.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo3.event.NetConnectChangedEvent;
import com.example.demo3.event.QRCodeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

public abstract class BaseFragment<T extends ViewDataBinding> extends SwipeBackFragment {
    private static final String TAG = "BaseFragment";

    protected T mDataBinding;

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
        return mDataBinding.getRoot();
    }

    public abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initEvent();
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

    /**
     * @param event 网络 变化的回调函数
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleNetConnectChanged(NetConnectChangedEvent event) {

    }

    /**
     * @param event
     * @deprecated 监听二维码的变化
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(QRCodeEvent event) {

    }

}
