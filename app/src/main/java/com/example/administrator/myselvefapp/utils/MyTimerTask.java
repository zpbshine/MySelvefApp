package com.example.administrator.myselvefapp.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by win on 2020/5/8.
 */

public class MyTimerTask extends TimerTask{
   // private TextView ble_rssi;
    private Context activity;
    private int i;
    private int whichac;
    public MyTimerTask(Context activity, int w){
       // this.ble_rssi=ble_rssi;
        this.activity = activity;
        this.whichac = w;
    }
    @Override
    public void run() {


        i++;
        System.out.println("i======"+i);
    }
}
