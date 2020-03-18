package com.example.administrator.myselvefapp.Activity;


import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.myselvefapp.bean.UserBean;
import com.example.administrator.myselvefapp.contents.MyApp;
import com.example.administrator.myselvefapp.dialog.DialogUtils;

/**
 * Created by Administrator on 2018/1/27.
 */

public class BaseActivity  extends AppCompatActivity {
    public MyApp myApp;
    public Context basecurt;
    private Dialog dialog;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());
        myApp = (MyApp) getApplication();

        basecurt =this;
        //注册异地登录的广播接收器

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.administrator.myselvefapp");
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, intentFilter);

        System.out.println("myreciver注册了吗======"+myReceiver);
        initView();
        initData();
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, final Intent intent) {
            //接收MainActivity传过来的数据
            //Toast.makeText(context, intent.getStringExtra("hello"), Toast.LENGTH_SHORT).show();
            Log.e("aabbcc",""+ intent.getStringExtra("hello"));
            String type = intent.getStringExtra("hello");
            if(type.contains("kick"))
            {
                String expiremsg = "您的帐号在其他设备登陆，如非本人操作，请修改密码！";
                //谈个吐司,提示用户
                dialog = DialogUtils.showpublicDialog(basecurt, expiremsg, "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                });
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Intent intent1 = new Intent(basecurt,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent1);
                        finish();
                    }
                });
            }


        }

    }
    /*
    初始化数据
     */

    public void initData() {
    }

    /*
    初始化布局
     */
    public void initView() {

    }
    /*
    关联布局
     */

    public int getViewLayout() {
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

       // if (myReceiver != null)
        //unregisterReceiver(myReceiver);
    }
}
