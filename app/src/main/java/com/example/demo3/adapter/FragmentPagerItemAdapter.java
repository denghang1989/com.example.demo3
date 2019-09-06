package com.example.demo3.adapter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.demo3.entity.MedicalRecord;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentPagerItemAdapter extends FragmentPagerAdapter {
    private static final String TAG = "FragmentPagerItemAdapte";

    private List<MedicalRecord> mList;

    public FragmentPagerItemAdapter(FragmentManager fm, List<MedicalRecord> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        MedicalRecord record = mList.get(position);
        return (Fragment) ARouter.getInstance().
                build("/app/PdfWebViewFragment").
                withString("pdfPath", record.getPdf()).
                navigation();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        MedicalRecord record = mList.get(position);
        return record.getName();
    }
}
