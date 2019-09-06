package com.example.demo3.base;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.demo3.mvp.presenter.BasePresenter;
import com.example.demo3.mvp.view.BaseView;

import androidx.annotation.CallSuper;

public abstract class BaseRecyclerViewPresenterFragment<P extends BasePresenter, A extends BaseQuickAdapter> extends BaseRecyclerViewFragment<A> implements BaseView {
    private static final String TAG = "BaseRecyclerViewPresent";

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
    @CallSuper
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    protected abstract P initPresent();

}
