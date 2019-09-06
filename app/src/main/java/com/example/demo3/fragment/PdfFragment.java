package com.example.demo3.fragment;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo3.App;
import com.example.demo3.R;
import com.example.demo3.adapter.PdfImageAdapter;
import com.example.demo3.base.BaseRecyclerViewPresenterFragment;
import com.example.demo3.event.PdfEvent;
import com.example.demo3.ioc.component.DaggerFragmentComponent;
import com.example.demo3.mvp.contract.PdfContract;
import com.example.demo3.mvp.presenter.PdfPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import javax.inject.Inject;


@Route(name = "PdfFragment", path = "/app/PdfFragment")
public class PdfFragment extends BaseRecyclerViewPresenterFragment<PdfPresenter,PdfImageAdapter> implements PdfContract.View {
    private static final String TAG = "PdfFragment";

    @Autowired(name = "pdfPath")
    public String mPdfPath;

    @Inject
    PdfPresenter mPresenter;

    @Inject
    PdfImageAdapter mAdapter;

    @Override
    protected void init() {
        super.init();
        EventBus.getDefault().register(this);
    }

    @Override
    protected PdfPresenter initPresent() {
        return mPresenter;
    }

    @Override
    protected PdfImageAdapter initAdapter() {
        return mAdapter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pdf;
    }


    @Override
    public void setData(List<File> pdfPath) {
        mAdapter.setNewData(pdfPath);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.downloadPdf(mPdfPath);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showPdf(PdfEvent event) {
        List<File> files = event.getFiles();
        setData(files);
    }

    @Override
    protected void inject() {
        DaggerFragmentComponent.builder().appComponent(App.getAppComponent()).build().inject(this);
    }
}
