package com.example.demo3.mvp.presenter;

import android.util.Log;

import com.example.demo3.entity.MedicalRecord;
import com.example.demo3.mvp.contract.MedicalContract;
import com.example.demo3.remote.Api;
import com.example.demo3.remote.RxPresenter;
import com.example.demo3.remote.RxUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MedicalPresenter extends RxPresenter<MedicalContract.View, MedicalContract.Model> implements MedicalContract.Presenter {
    private static final String TAG = "MedicalPresenter";

    private Api mApi;

    @Inject
    public MedicalPresenter(Api api) {
        mApi = api;
    }

    @Override
    public void getMedicalRecord(String eposideId) {
        mApi.getMedicalRecord(eposideId)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<MedicalRecord>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<MedicalRecord> medicalRecords) {
                        Log.d(TAG, "onNext: " + medicalRecords.size());
                        if (medicalRecords.size() > 0) {
                            mView.setData(medicalRecords);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
