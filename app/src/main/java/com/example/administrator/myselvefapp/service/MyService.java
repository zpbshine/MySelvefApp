package com.example.administrator.myselvefapp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.administrator.myselvefapp.Activity.MainActivity;
import com.example.administrator.myselvefapp.R;

/**
 * Created by win on 2020/4/18.
 */

public class MyService extends Service {


    //启动Service之后，就可以在onCreate()或onStartCommand()方法里去执行一些具体的逻辑
//由于这里作Demo用，所以只打印一些语句
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("执行了onCreat()");

        //添加下列代码将后台Service变成前台Service
        //构建"点击通知后打开MainActivity"的Intent对象
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);

        //新建Builer对象
        Notification.Builder builer = new Notification.Builder(this);
        builer.setContentTitle("前台服务通知的标题");//设置通知的标题
        builer.setContentText("前台服务通知的内容");//设置通知的内容
        builer.setSmallIcon(R.mipmap.ic_launcher);//设置通知的图标
        builer.setContentIntent(pendingIntent);//设置点击通知后的操作

        Notification notification = builer.getNotification();//将Builder对象转变成普通的notification
        startForeground(1, notification);//让Service变成前台Service,并在系统的状态栏显示出来

    }

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        System.out.println("执行了onCreat()");
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("执行了onStartCommand()");
        return START_STICKY;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("执行了onDestory()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("执行了onBind()");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("执行了onUnbind()");
        return super.onUnbind(intent);
    }
}


