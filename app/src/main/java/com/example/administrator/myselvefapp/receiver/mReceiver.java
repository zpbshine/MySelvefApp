package com.example.administrator.myselvefapp.receiver;

/**
 * Created by win on 2020/3/18.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.administrator.myselvefapp.service.StepService;

/**
 * 开机完成广播
 */

public class mReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Intent mIntent = new Intent(context,StepService.class);
        context.startService(mIntent);
    }
}
