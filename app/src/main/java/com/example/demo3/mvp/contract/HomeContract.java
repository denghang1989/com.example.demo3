package com.example.demo3.mvp.contract;

import com.example.demo3.mvp.model.BaseModel;
import com.example.demo3.mvp.presenter.BasePresenter;
import com.example.demo3.mvp.view.BaseView;

public interface HomeContract {

    interface View extends BaseView {
        void showProgress();

        void hideProgress();

    }

    interface Model extends BaseModel {

    }


    interface Presenter extends BasePresenter<View> {
        void getPatientList(String ctLocId);
    }

}
