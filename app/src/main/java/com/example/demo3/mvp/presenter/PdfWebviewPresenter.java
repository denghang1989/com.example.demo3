package com.example.demo3.mvp.presenter;

import com.blankj.utilcode.util.FileUtils;
import com.example.demo3.callback.PdfCallback;
import com.example.demo3.mvp.contract.PdfWebviewContract;
import com.example.demo3.remote.RxPresenter;
import com.example.demo3.remote.RxUtil;
import com.example.demo3.utils.Contants;
import com.example.demo3.utils.FtpUtils;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PdfWebviewPresenter extends RxPresenter<PdfWebviewContract.View, PdfWebviewContract.Model> implements PdfWebviewContract.Presenter {
    private static final String TAG = "PdfPresenter";

    @Override
    public void downloadPdf(String pdfPath) {
        File downLoadPath = new File(Contants.PDF_DOWNLOAD_PATH + pdfPath);
        if (FileUtils.isFileExists(downLoadPath)) {
            mView.setData(downLoadPath);
        } else {
            Observable.create((ObservableOnSubscribe<File>) emitter -> FtpUtils.getInstance().downLoad(pdfPath, downLoadPath, new PdfCallback() {
                @Override
                public void success(File file) {
                    emitter.onNext(file);
                    emitter.onComplete();
                }

                @Override
                public void error(Exception e) {
                    emitter.onError(e);
                    emitter.onComplete();
                }
            })).compose(RxUtil.rxSchedulerHelper())
                    .subscribe(new Observer<File>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            addSubscribe(d);
                        }

                        @Override
                        public void onNext(File file) {
                            mView.setData(file);
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
}
