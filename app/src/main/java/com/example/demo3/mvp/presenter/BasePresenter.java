package com.example.demo3.mvp.presenter;

import com.example.demo3.mvp.view.BaseView;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
