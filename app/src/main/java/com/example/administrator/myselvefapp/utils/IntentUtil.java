package com.example.administrator.myselvefapp.utils;

import android.content.Context;
import android.content.Intent;

import com.example.administrator.myselvefapp.Activity.RegActivity;

/**
 * Created by Administrator on 2018/1/20.
 */

public class IntentUtil {
    //注册
    public static Intent getRegIntent(Context context){
       return new Intent(context,RegActivity.class);
    }
}
