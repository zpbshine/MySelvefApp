package com.example.administrator.myselvefapp.Activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/1/27.
 */

public class BaseActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());
        initView();
        initData();
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


}
