package com.example.demo3.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo3.App;
import com.example.demo3.adapter.PatientListAdapter;
import com.example.demo3.base.BaseRecyclerViewPresenterFragment;
import com.example.demo3.ioc.component.DaggerFragmentComponent;
import com.example.demo3.mvp.presenter.HomePresenter;

import javax.inject.Inject;

@Route(path = "/app/homeFragment")
public class HomeFragment extends BaseRecyclerViewPresenterFragment<HomePresenter, PatientListAdapter> {

    @Inject
    PatientListAdapter mAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected void inject() {
        DaggerFragmentComponent.builder().appComponent(App.getAppComponent()).build().inject(this);
    }

    @Override
    protected PatientListAdapter initAdapter() {
        return mAdapter;
    }

    @Override
    protected void initData() {
        super.initData();
        showContent();
    }

    @Override
    protected HomePresenter initPresent() {
        return null;
    }
}
