package com.example.administrator.myselvefapp.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myselvefapp.R;
import com.example.administrator.myselvefapp.contents.AppInterface;
import com.example.administrator.myselvefapp.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;


/**
 * Created by Administrator on 2018/1/20.
 */

public class RegActivity extends BaseActivity implements View.OnClickListener {
    private EditText etRegPhone, etRegPwd;
    private TextView tvReg;
    private ImageView ivDelPhone, ivDelPwd;
    private static String test ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View getViewLayout() {
        View view = View.inflate(myApp,R.layout.activity_reg,null);
        return view;
    }
@Override
    public void initView() {
        etRegPhone = (EditText) findViewById(R.id.et_reg_phone);
        etRegPwd = (EditText) findViewById(R.id.et_reg_pwd);
        tvReg = (TextView) findViewById(R.id.tv_reg);
        ivDelPhone = (ImageView) findViewById(R.id.iv_reg_delPhone);
        ivDelPwd = (ImageView) findViewById(R.id.iv_reg_delPwd);


        ivDelPhone.setOnClickListener(this);
        ivDelPwd.setOnClickListener(this);
        tvReg.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_reg_delPhone:
                etRegPhone.setText("");
                break;
            case R.id.iv_reg_delPwd:
                etRegPwd.setText("");
                break;
            case R.id.tv_reg:
                //访问注册接口
               // myApp.testif = "no";
                //showWaitingDialog();
                finish();
                String phone = etRegPhone.getText().toString();
                String pwd = etRegPwd.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
                OkHttpUtils.post()
                        .addParams("phoneNum", phone)
                        .addParams("pwd", pwd)
                        .url(AppInterface.USER_REG)
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
                                    if ("000".equals(statu)) {
                                        ToastUtil.showShortToast(RegActivity.this, "注册成功");
                                    } else if ("001".equals(statu)){
                                        ToastUtil.showShortToast(RegActivity.this, "账号已存在");
                                    }else {
                                        ToastUtil.showShortToast(RegActivity.this,"注册失败");
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
        }
    }


}
