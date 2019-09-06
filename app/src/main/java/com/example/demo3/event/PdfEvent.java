package com.example.demo3.event;

import java.io.File;
import java.util.List;

public class PdfEvent {
    private List<File> mFiles;

    public PdfEvent(List<File> files) {
        mFiles = files;
    }

    public List<File> getFiles() {
        return mFiles;
    }

    public void setFiles(List<File> files) {
        mFiles = files;
    }
}
