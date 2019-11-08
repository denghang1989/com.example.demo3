package com.example.demo3.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo3.App;
import com.example.demo3.R;
import com.example.demo3.base.BasePresenterFragment;
import com.example.demo3.databinding.FragmentPanelBinding;
import com.example.demo3.ioc.component.DaggerFragmentComponent;
import com.example.demo3.mvp.contract.PanelContract;
import com.example.demo3.mvp.presenter.PanelPresenter;

@Route(path = "/app/PanelFragment")
public class PanelFragment extends BasePresenterFragment<FragmentPanelBinding, PanelPresenter> implements PanelContract.View {

    @Override
    protected void inject() {
        DaggerFragmentComponent.builder().appComponent(App.getAppComponent()).build().inject(this);
    }

    public static PanelFragment newInstance() {
        return new PanelFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_panel;
    }

    @Override
    protected void initData() {
        super.initData();
        showContent();
    }

}
