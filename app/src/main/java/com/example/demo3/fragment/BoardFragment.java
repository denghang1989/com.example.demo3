package com.example.demo3.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo3.App;
import com.example.demo3.R;
import com.example.demo3.base.BasePresenterFragment;
import com.example.demo3.databinding.FragmentBoardBinding;
import com.example.demo3.ioc.component.DaggerFragmentComponent;
import com.example.demo3.mvp.contract.BoardContract;
import com.example.demo3.mvp.presenter.BoardPresenter;

@Route(path = "/app/BoardFragment")
public class BoardFragment extends BasePresenterFragment<FragmentBoardBinding, BoardPresenter> implements BoardContract.View {


    @Override
    protected void inject() {
        DaggerFragmentComponent.builder().appComponent(App.getAppComponent()).build().inject(this);
    }

    public static BoardFragment newInstance() {
        return new BoardFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_board;
    }

}
