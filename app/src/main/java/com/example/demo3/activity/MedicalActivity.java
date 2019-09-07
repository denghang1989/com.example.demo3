package com.example.demo3.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo3.App;
import com.example.demo3.R;
import com.example.demo3.adapter.FragmentPagerItemAdapter;
import com.example.demo3.base.BasePresenterActivity;
import com.example.demo3.databinding.ActivityMedicalBinding;
import com.example.demo3.entity.MedicalRecord;
import com.example.demo3.ioc.component.DaggerActivityComponent;
import com.example.demo3.mvp.contract.MedicalContract;
import com.example.demo3.mvp.presenter.MedicalPresenter;

import java.util.List;

import javax.inject.Inject;

@Route(name = "medical", path = "/app/MedicalActivity")
public class MedicalActivity extends BasePresenterActivity<ActivityMedicalBinding, MedicalPresenter> implements MedicalContract.View {
    private static final String TAG = "MedicalActivity";

    @Inject
    public MedicalPresenter mPresenter;

    @Override
    protected MedicalPresenter initPresent() {
        return mPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_medical;
    }

    @Override
    public void showProgress() {
        mDataBinding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.progress.setVisibility(View.GONE);
    }

    @Override
    public void setData(List<MedicalRecord> list) {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), list);
        mDataBinding.viewPager.setAdapter(adapter);
        mDataBinding.viewpagertab.setViewPager(mDataBinding.viewPager);
    }

    @Override
    protected void initData() {
        mPresenter.getMedicalRecord("");
    }

    @Override
    protected void inject() {
        DaggerActivityComponent.builder().appComponent(App.getAppComponent()).build().inject(this);
    }
}
