package com.example.demo3.utils;

import android.os.Environment;

import java.io.File;

public interface Contants {
    String PDF_DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "pdf";
}
