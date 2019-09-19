package com.example.demo3.base;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.demo3.R;
import com.example.demo3.databinding.FragmentRecyclerviewBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public abstract class BaseRecyclerViewFragment<A extends BaseQuickAdapter> extends BaseFragment<FragmentRecyclerviewBinding> implements
        BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemChildLongClickListener, OnRefreshListener, OnLoadMoreListener {

    protected A mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    @CallSuper
    protected void initView() {
        super.initView();
        mAdapter = initAdapter();
        if (mAdapter == null) {
            throw new NullPointerException("RecyclerView.Adapter 必须提前初始化");
        }
        initRecyclerView();
    }

    protected abstract A initAdapter();

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = initLayoutManager();
        if (layoutManager == null) {
            layoutManager = defaultLayoutManager();
        }
        mDataBinding.recyclerView.setLayoutManager(layoutManager);
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.HORIZONTAL));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    protected RecyclerView.LayoutManager initLayoutManager() {
        return null;
    }

    @Override
    @CallSuper
    protected void initEvent() {
        super.initEvent();
        mDataBinding.refreshLayout.setOnRefreshListener(this);
        mDataBinding.refreshLayout.setOnLoadMoreListener(this);
        mAdapter.setOnItemChildLongClickListener(this);
    }

    protected RecyclerView.LayoutManager defaultLayoutManager() {
        return new LinearLayoutManager(_mActivity);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    public void showHeaderProgress() {
        mDataBinding.refreshLayout.autoRefresh();
    }

    public void hideHeaderProgress() {
        mDataBinding.refreshLayout.finishRefresh(100);
    }

    public void hideFooterProgress(){
        mDataBinding.refreshLayout.finishLoadMore(100);
    }
}
