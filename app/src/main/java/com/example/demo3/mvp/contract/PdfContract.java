package com.example.demo3.mvp.contract;

import com.example.demo3.mvp.model.BaseModel;
import com.example.demo3.mvp.presenter.BasePresenter;
import com.example.demo3.mvp.view.BaseView;

import java.io.File;
import java.util.List;

public interface PdfContract {

    interface View extends BaseView {
        void setData(List<File> pdfPath);
    }

    interface Model extends BaseModel {

    }


    interface Presenter extends BasePresenter<View> {
        void downloadPdf(String pdfPath);
    }

}
