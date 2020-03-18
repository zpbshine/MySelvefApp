package com.example.administrator.myselvefapp.contents;

import android.app.Application;

import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by win on 2018/11/26.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Hawk存储初始化
       // Hawk.init(this).build();
        // 不耗时，做一些简单初始化准备工作，不会启动下载进程
        FileDownloader.setup(this);
    }
}
