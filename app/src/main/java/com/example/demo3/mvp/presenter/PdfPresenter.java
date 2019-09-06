package com.example.demo3.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Handler;
import android.os.ParcelFileDescriptor;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.example.demo3.callback.PdfCallback;
import com.example.demo3.event.PdfEvent;
import com.example.demo3.ioc.qualifier.AsyncHandler;
import com.example.demo3.mvp.contract.PdfContract;
import com.example.demo3.remote.RxPresenter;
import com.example.demo3.utils.Contants;
import com.example.demo3.utils.FtpUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PdfPresenter extends RxPresenter<PdfContract.View, PdfContract.Model> implements PdfContract.Presenter {
    private static final String TAG = "PdfPresenter";

    private Handler mHandler;

    @Inject
    public PdfPresenter(@AsyncHandler Handler handler) {
        mHandler = handler;
    }

    @Override
    public void downloadPdf(String pdfPath) {
        File downLoadPath = new File(Contants.PDF_DOWNLOAD_PATH + pdfPath);
        if (FileUtils.isFileExists(downLoadPath)) {
            List<File> files = getJpgs(downLoadPath);
            mView.setData(files);
        } else {
            mHandler.post(() -> FtpUtils.getInstance().downLoad(pdfPath, downLoadPath, new PdfCallback() {
                @Override
                public void success(File file) {
                    List<File> files = pdf2Jpg(file);
                    EventBus.getDefault().post(new PdfEvent(files));
                }

                @Override
                public void error(Exception e) {

                }
            }));
        }
    }

    private List<File> pdf2Jpg(File file) {
        List<File> files = new ArrayList<>();
        PdfRenderer pdfRenderer = null;
        try {
            pdfRenderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));
            for (int i = 0; i < pdfRenderer.getPageCount(); i++) {
                PdfRenderer.Page page = pdfRenderer.openPage(i);
                Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                String parent = file.getParent();
                String name = file.getName();
                String newName = name.substring(0, name.lastIndexOf(".")) + "-" + i + ".jpg";
                File jpgFile = new File(parent, newName);
                ImageUtils.save(bitmap, jpgFile, Bitmap.CompressFormat.JPEG);
                files.add(jpgFile);
                page.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pdfRenderer != null) {
                pdfRenderer.close();
            }
        }
        return files;
    }

    private List<File> getJpgs(File file) {
        return FileUtils.listFilesInDirWithFilter(file.getParentFile(), pathname -> pathname.getName().contains(".jpg"));
    }
}
