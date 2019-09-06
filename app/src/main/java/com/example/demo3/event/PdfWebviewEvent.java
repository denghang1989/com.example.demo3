package com.example.demo3.event;

import java.io.File;

public class PdfWebviewEvent {
    private File mFile;

    public PdfWebviewEvent(File file) {
        mFile = file;
    }

    public File getFile() {
        return mFile;
    }

    public void setFile(File file) {
        mFile = file;
    }
}
