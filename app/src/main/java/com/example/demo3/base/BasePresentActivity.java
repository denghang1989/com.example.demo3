package com.example.demo3.base;

import com.example.demo3.mvp.presenter.BasePresenter;
import com.example.demo3.mvp.view.BaseView;

import androidx.annotation.CallSuper;
import androidx.databinding.ViewDataBinding;

public abstract class BasePresentActivity<T extends ViewDataBinding, P extends BasePresenter> extends BaseActivity<T> implements BaseView {

    protected P mPresenter;

    @CallSuper
    @Override
    protected void init() {
        super.init();
        mPresenter = initPresent();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract P initPresent();
}
