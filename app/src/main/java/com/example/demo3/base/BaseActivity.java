package com.example.demo3.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

public abstract class BaseActivity<T extends ViewDataBinding> extends SwipeBackActivity {

    protected T mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        inject();
        init();
        initView();
        initData();
        initEvent();
    }

    protected void inject() {

    }

    public abstract int getLayoutId();

    protected void initEvent() {

    }

    protected void initData() {

    }

    protected void initView() {

    }

    protected void init() {

    }

}
