package com.example.administrator.myselvefapp.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.administrator.myselvefapp.R;
import com.example.administrator.myselvefapp.utils.MyTimerTask;

import java.util.Timer;

/**
 * Created by win on 2020/5/8.
 */

public class Thread2Activity extends Activity {
    private TextView tv_ble;
    private Timer timer;
    private MyTimerTask timerTask;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitity_thread2);
        tv_ble=findViewById(R.id.tv_ble);
    }
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("timer=="+timer+"==timertask==="+timerTask);
        System.out.println("timer=="+timer+"==timertask==="+timerTask);
        System.out.println("timer=="+timer+"==timertask==="+timerTask);
//        if(timer==null||timerTask==null){
//            timer = new Timer("timer1");
//            timerTask = new MyTimerTask(tv_ble,this,2);
//            timer.schedule(timerTask, 0, 2000);
//        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ondestory2=========");
    }
}
