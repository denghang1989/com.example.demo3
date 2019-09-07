package com.example.demo3.base;

import com.example.demo3.mvp.presenter.BasePresenter;
import com.example.demo3.mvp.view.BaseView;

import androidx.annotation.CallSuper;

public abstract class BaseWebViewPresenterActivity<P extends BasePresenter> extends BaseWebViewActivity implements BaseView {

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
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected abstract P initPresent();

}
