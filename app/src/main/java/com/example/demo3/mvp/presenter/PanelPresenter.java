package com.example.demo3.mvp.presenter;

import com.example.demo3.mvp.contract.PanelContract;
import com.example.demo3.remote.Api;
import com.example.demo3.remote.RxPresenter;

import javax.inject.Inject;

public class PanelPresenter extends RxPresenter<PanelContract.View,PanelContract.Model> implements PanelContract.Presenter {

    Api mApi;

    @Inject
    public PanelPresenter(Api api) {
        mApi = api;
    }
}
