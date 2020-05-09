package com.example.administrator.myselvefapp.service;

/**
 * Created by win on 2020/3/18.
 */

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 主进程 双进程通讯
 * Created by db on 2018/1/11.
 */

public class StepService extends Service {

    private PowerManager.WakeLock mWakeLock;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ProcessConnection.Stub() {
        };
    }

    @Override
    public void onCreate() {
        System.out.println("StepService===onCreate");
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("StepService===onStartCommand");
        Timer m_timer = new Timer();
        TimerTask m_timerTask = new TimerTask() {
            @Override
            public void run() {

                //System.out.println("连接蓝牙成功===");

            }
        };
        m_timer.schedule(m_timerTask, 0, 3000);

        startForeground(1, new Notification());
        getLock(getApplicationContext());
        //绑定建立链接
        bindService(new Intent(this, GuardService.class),
                mServiceConnection, Context.BIND_IMPORTANT);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        System.out.println("StepService===onDestroy");
        releaseLock();
        super.onDestroy();
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //链接上
            Log.d("test", "StepService:建立链接");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("StepService===onServiceDisconnected");
            //断开链接
            startService(new Intent(StepService.this, GuardService.class));
            //重新绑定
            bindService(new Intent(StepService.this, GuardService.class),
                    mServiceConnection, Context.BIND_IMPORTANT);
        }
    };

    /**
     * 同步方法   得到休眠锁
     *
     * @param context
     * @return
     */
    synchronized private void getLock(Context context) {
        if (mWakeLock == null) {
            PowerManager mgr = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, StepService.class.getName());
            mWakeLock.setReferenceCounted(true);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis((System.currentTimeMillis()));
            int hour = c.get(Calendar.HOUR_OF_DAY);
            if (hour >= 23 || hour <= 6) {
                mWakeLock.acquire(5000);
            } else {
                mWakeLock.acquire(300000);
            }
        }

    }

    synchronized private void releaseLock() {
        if (mWakeLock != null) {
            if (mWakeLock.isHeld()) {
                mWakeLock.release();
            }

            mWakeLock = null;
        }
    }

}
