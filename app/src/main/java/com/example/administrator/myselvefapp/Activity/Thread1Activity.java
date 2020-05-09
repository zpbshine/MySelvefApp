package com.example.administrator.myselvefapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myselvefapp.R;
import com.example.administrator.myselvefapp.utils.MyTimerTask;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by win on 2020/5/8.
 */

public class Thread1Activity extends Activity {
    private TextView tv_ble;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitity_thread1);
        tv_ble=findViewById(R.id.tv_ble);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thread1Activity.this,Thread2Activity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("timer=="+timer+"==timertask==="+timerTask);
//        if(timer==null||timerTask==null){
//            timer = new Timer("timer1");
//            timerTask = new MyTimerTask(tv_ble,this,1);
////            timerTask = new TimerTask() {
////                @Override
////                public void run() {
////                    System.out.println("11111111111111111");
////                }
////            };
//            timer.schedule(timerTask, 0, 2000);
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ondestory1=========");
    }
}
