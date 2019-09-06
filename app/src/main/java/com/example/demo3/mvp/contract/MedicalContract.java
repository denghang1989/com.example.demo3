package com.example.demo3.mvp.contract;

import com.example.demo3.mvp.model.BaseModel;
import com.example.demo3.mvp.presenter.BasePresenter;
import com.example.demo3.mvp.view.BaseView;
import com.example.demo3.entity.MedicalRecord;

import java.util.List;

public interface MedicalContract {

    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void setData(List<MedicalRecord> list);
    }

    interface Model extends BaseModel {

    }


    interface Presenter extends BasePresenter<View> {
        void getMedicalRecord(String eposideId);
    }

}
