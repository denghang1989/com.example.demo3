package com.example.demo3.mvp.presenter;

import com.example.demo3.mvp.contract.BoardContract;
import com.example.demo3.remote.Api;
import com.example.demo3.remote.RxPresenter;

import javax.inject.Inject;

public class BoardPresenter extends RxPresenter<BoardContract.View,BoardContract.Model> implements BoardContract.Presenter {

    Api mApi;

    @Inject
    public BoardPresenter(Api api) {
        mApi = api;
    }
}
