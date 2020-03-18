package com.example.administrator.myselvefapp.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myselvefapp.R;
import com.example.administrator.myselvefapp.contents.AppInterface;
import com.example.administrator.myselvefapp.utils.IntentUtil;
import com.example.administrator.myselvefapp.utils.SDcardUtils;
import com.example.administrator.myselvefapp.utils.ToastUtil;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import okhttp3.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText etPhone,etPwd;
    private ImageView ivDelPhone,ivDelPwd;
    private TextView tvLogin,tvReg,tvForPwd;
    private SeekBar sb_test;
    private TextView tv_percent;
    final String[] PERMISSIONS_STORAGE = { "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };
    private ImageView imageView;
    private ArrayList<Byte> arrBytes = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public int getViewLayout() {
        return R.layout.activity_login;
    }
    @Override
    public void initView() {
        etPhone = (EditText) findViewById(R.id.EditText_phone);
        etPwd = (EditText) findViewById(R.id.EditText_pwd);
        ivDelPwd = (ImageView) findViewById(R.id.iv_delPwd);
        ivDelPhone = (ImageView) findViewById(R.id.iv_delPhone);
        tvLogin = (TextView) findViewById(R.id.Btn_login);
        tvReg = (TextView) findViewById(R.id.Btn_reg);
        tvForPwd = (TextView) findViewById(R.id.Btn_forget_pwd);

        sb_test = findViewById(R.id.sb_test);
        tv_percent = findViewById(R.id.tv_percent);
        imageView = findViewById(R.id.iv);

        final byte[] mybytes = new byte[1024];
        mybytes[0]=0x00;
        mybytes[1]=0x01;
        mybytes[2]=0x02;

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrBytes.clear();
                for (int i = 0; i < 3; i++) {
                    arrBytes.add(mybytes[i]);
                }
                System.out.println(arrBytes.size());
                System.out.println(arrBytes.get(0));
                System.out.println(arrBytes.get(1));
                System.out.println(arrBytes.get(2));
                boolean istrue=arrBytes.get(0)==mybytes[0];
                System.out.println(istrue);


//                if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions((BaseActivity) LoginActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
//                }else {
//                    SDcardUtils.jieya(imageView);
//                }

            }
        });
        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                if (Environment.getExternalStorageState().equals(
//                        Environment.MEDIA_MOUNTED)) {
//                    SDcardUtils sDcardUtils = new SDcardUtils();
//                    sDcardUtils.downloadAPK(LoginActivity.this,"http://192.168.3.6:8080/MyFirstApp/test.zip");
//                   // SDcardUtils.downFile(LoginActivity.this,"http://192.168.3.6:8080/MyFirstApp/test.zip");     //在下面的代码段
//                } else {
//                    Toast.makeText(LoginActivity.this, "SD卡不可用，请插入SD卡",
//                            Toast.LENGTH_SHORT).show();
//                }

                //检查权限
                //检查版本是否大于M
//                System.out.println("咋了这是======"+Build.VERSION.SDK_INT);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) { //进入到这里代表没有权限.
//
//                        ActivityCompat.requestPermissions(LoginActivity.this, PERMISSIONS_STORAGE, 111);
//
//                    }
//                    else {
//                    SDcardUtils.downFile(LoginActivity.this,"http://192.168.3.6:8080/MyFirstApp/test.zip");
//                    }
//
//                }else {
//                    SDcardUtils.downFile(LoginActivity.this,"http://192.168.3.6:8080/MyFirstApp/test.zip");
//                }


            }
        });



        sb_test.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_percent.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ivDelPhone.setOnClickListener(this);
        ivDelPwd.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvReg.setOnClickListener(this);
        tvForPwd.setOnClickListener(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 111:
                if(grantResults.length >0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){ //用户同意授权

        SDcardUtils.downFile(LoginActivity.this,"http://192.168.3.6:8080/MyFirstApp/test.zip");
    }else{ //用户拒绝授权
    } break;
            case 1:
                if(grantResults.length >0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){ //用户同意授权

                    SDcardUtils.jieya(imageView);}
    } }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_delPhone:
                etPhone.setText("");
                break;
            case R.id.iv_delPwd:
                etPwd.setText("");
                break;
            case R.id.Btn_login:
                //点击登录

                //发送异地登录广播
                Intent intent=new Intent("com.example.administrator.myselvefapp");
                //发送本地广播。
                intent.putExtra("hello","kick");
                LocalBroadcastManager.getInstance(basecurt).sendBroadcast(intent);

                String phone = etPhone.getText().toString();
                String pwd = etPwd.getText().toString();

                if (!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(pwd)){
                    //访问登录接口
                    OkHttpUtils.post()
                            .addParams("phoneNum",phone)
                            .addParams("pwd",pwd)
                            .url(AppInterface.USER_LOGIN)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {

                                    try {
                                        JSONObject obj = new JSONObject(response);
                                        String statu = obj.getString("statu");
                                        if ("000".equals(statu)){
                                            ToastUtil.showShortToast(LoginActivity.this,"登录成功");
//                                            //发送异地登录广播
//                                            Intent intent=new Intent("com.example.administrator.myselvefapp");
//                                            //发送本地广播。
//                                            intent.putExtra("hello","kick");
//                                            LocalBroadcastManager.getInstance(basecurt).sendBroadcast(intent);
                                        }else {
                                            ToastUtil.showShortToast(LoginActivity.this,"登录失败");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }else {
                    ToastUtil.showShortToast(this,"账号密码不能为空");
                }
                break;
            case R.id.Btn_reg:
                //跳到注册页面
                startActivity(IntentUtil.getRegIntent(this));
                break;
            case R.id.Btn_forget_pwd:
                ToastUtil.showShortToast(this,"跳到忘记密码页面");
                break;
        }
    }

}
