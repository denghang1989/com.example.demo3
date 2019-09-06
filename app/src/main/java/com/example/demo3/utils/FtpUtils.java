package com.example.demo3.utils;

import com.blankj.utilcode.util.FileUtils;
import com.example.demo3.callback.PdfCallback;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FtpUtils {

    public static final String HOST     = "192.168.199.93";
    public static final int    POST     = 21;
    public static final String USE_NAME = "anonymous";
    public static final String PSW      = "denghang";

    private                 FTPClient mFtpClient;
    private volatile static FtpUtils  instance;

    private FtpUtils() {
        mFtpClient = new FTPClient();
        mFtpClient.setConnectTimeout(10 * 1000);
    }

    public static FtpUtils getInstance() {
        if (instance == null) {
            synchronized (FtpUtils.class) {
                if (instance == null) {
                    instance = new FtpUtils();
                }
            }
        }
        return instance;
    }

    public boolean connect() {
        boolean reply = false;
        try {
            mFtpClient.connect(HOST, POST);
            mFtpClient.login(USE_NAME, PSW);
            int replyCode = mFtpClient.getReplyCode();
            if (FTPReply.isPositiveCompletion(replyCode)) {
                reply = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reply;
    }

    public boolean isConnect() {
        return mFtpClient.isConnected();
    }

    public void disconnect() {
        try {
            if (isConnect()) {
                mFtpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downLoad(String pdfPath, File downLoadPath, PdfCallback callback) {
        try {
            if (!isConnect()) {
                connect();
            }
            FileUtils.createOrExistsFile(downLoadPath);
            mFtpClient.retrieveFile(pdfPath, new FileOutputStream(downLoadPath));
            callback.success(downLoadPath);
        } catch (IOException e) {
            e.printStackTrace();
            callback.error(e);
        }
    }

}
