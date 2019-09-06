package com.example.demo3.mvp.contract;

import com.example.demo3.mvp.model.BaseModel;
import com.example.demo3.mvp.presenter.BasePresenter;
import com.example.demo3.mvp.view.BaseView;

import java.io.File;

public interface PdfWebviewContract {

    interface View extends BaseView {

        void setData(File pdfPath);

        void showError(Exception e);
    }

    interface Model extends BaseModel {

    }


    interface Presenter extends BasePresenter<View> {
        void downloadPdf(String pdfPath);
    }

}
