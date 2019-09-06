package com.example.demo3.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.demo3.R;
import com.example.demo3.entity.PatientInfo;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class PatientListAdapter extends BaseQuickAdapter<PatientInfo, BaseViewHolder> {

    @Inject
    public PatientListAdapter(){
        super(R.layout.item_patinet);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PatientInfo item) {

    }
}
