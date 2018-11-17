package com.example.administrator.myselvefapp.utils;


import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/1/20.
 */

public class ToastUtil {
    public static void showShortToast(Context context,String content){
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }
    public static void showLongToast(Context context, String content){
        Toast.makeText(context,content,Toast.LENGTH_LONG).show();
    }
}
