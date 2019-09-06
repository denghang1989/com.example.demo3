package com.example.demo3.base;


import com.example.demo3.mvp.presenter.BasePresenter;
import com.example.demo3.mvp.view.BaseView;

import androidx.annotation.CallSuper;
import androidx.databinding.ViewDataBinding;

public abstract class BasePresenterFragment<T extends ViewDataBinding, P extends BasePresenter> extends BaseFragment<T> implements BaseView {
    private static final String TAG = "BasePresenterFragment";

    protected P mPresenter;

    @CallSuper
    @Override
    protected void init() {
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    protected P initPresenter() {
        return null;
    }
}
