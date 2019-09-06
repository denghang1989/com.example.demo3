package com.example.demo3.mvp.presenter;

import com.example.demo3.mvp.contract.HomeContract;
import com.example.demo3.remote.Api;
import com.example.demo3.remote.RxPresenter;

import javax.inject.Inject;

public class HomePresenter extends RxPresenter<HomeContract.View,HomeContract.Model> implements HomeContract.Presenter {

    Api mApi;

    @Inject
    public HomePresenter(Api api) {
        mApi = api;
    }

    @Override
    public void getPatientList(String ctLocId) {

    }
}
