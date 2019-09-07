package com.example.demo3.fragment;

import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo3.base.BaseWebViewPresenterFragment;
import com.example.demo3.mvp.contract.PdfWebviewContract;
import com.example.demo3.mvp.presenter.PdfWebviewPresenter;

import java.io.File;

import es.dmoral.toasty.Toasty;

@Route(name = "pdfWeb", path = "/app/PdfWebViewFragment")
public class PdfWebViewFragment extends BaseWebViewPresenterFragment<PdfWebviewPresenter> implements PdfWebviewContract.View {
    private static final String TAG = "PdfWebViewFragment";

    @Autowired(name = "pdfPath")
    public String mPdfPath;

    @Override
    protected PdfWebviewPresenter initPresent() {
        return new PdfWebviewPresenter();
    }

    @Override
    public void setData(File pdfPath) {
        if (mWebView != null) {
            mWebView.loadUrl("file:///android_asset/index.html?file://" + pdfPath.getAbsolutePath());
        }
    }

    @Override
    public void showError(Exception e) {
        Toasty.error(_mActivity, e.getMessage(), Toast.LENGTH_SHORT, true).show();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.downloadPdf(mPdfPath);
    }

}
