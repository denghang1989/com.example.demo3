package com.example.demo3.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.demo3.R;

import java.io.File;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class PdfImageAdapter extends BaseQuickAdapter<File, BaseViewHolder> {

    @Inject
    public PdfImageAdapter() {
        super(R.layout.item_pdf);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, File item) {
        ImageView imageView = helper.getView(R.id.photo_view);
        Glide.with(mContext).load(item).into(imageView);
    }
}
