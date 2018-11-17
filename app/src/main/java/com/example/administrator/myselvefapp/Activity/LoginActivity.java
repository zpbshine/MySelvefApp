package com.example.administrator.myselvefapp.Activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myselvefapp.R;
import com.example.administrator.myselvefapp.contents.AppInterface;
import com.example.administrator.myselvefapp.utils.IntentUtil;
import com.example.administrator.myselvefapp.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText etPhone,etPwd;
    private ImageView ivDelPhone,ivDelPwd;
    private TextView tvLogin,tvReg,tvForPwd;


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

        ivDelPhone.setOnClickListener(this);
        ivDelPwd.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvReg.setOnClickListener(this);
        tvForPwd.setOnClickListener(this);
    }

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
