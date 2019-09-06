package com.example.demo3.callback;

import java.io.File;

public interface PdfCallback {

    void success(File file);

    void error(Exception e);
}
