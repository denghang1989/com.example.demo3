package com.example.demo3.mvp.contract;

import com.example.demo3.mvp.model.BaseModel;
import com.example.demo3.mvp.presenter.BasePresenter;
import com.example.demo3.mvp.view.BaseView;

public interface PanelContract {

    interface View extends BaseView {

    }

    interface Model extends BaseModel {

    }


    interface Presenter extends BasePresenter<View> {

    }
}
