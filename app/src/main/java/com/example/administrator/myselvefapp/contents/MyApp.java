package com.example.administrator.myselvefapp.contents;

import android.app.Application;

import com.example.administrator.myselvefapp.utils.MyTimerTask;
import com.liulishuo.filedownloader.FileDownloader;

import java.util.Timer;

/**
 * Created by win on 2018/11/26.
 */

public class MyApp extends Application {
    public Timer timer;
    public MyTimerTask timerTask;
    @Override
    public void onCreate() {
        super.onCreate();
        //Hawk存储初始化
       // Hawk.init(this).build();
        // 不耗时，做一些简单初始化准备工作，不会启动下载进程
        FileDownloader.setup(this);
        if(timer==null||timerTask==null){
            timer = new Timer("timer1");
            timerTask = new MyTimerTask(getApplicationContext(),2);
            timer.schedule(timerTask, 0, 2000);
        }
    }

}
