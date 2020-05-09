package com.example.administrator.myselvefapp.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.myselvefapp.R;
import com.example.administrator.myselvefapp.fragment.Page1Fragment;
import com.example.administrator.myselvefapp.fragment.Page2Fragment;
import com.example.administrator.myselvefapp.fragment.Page3Fragment;
import com.example.administrator.myselvefapp.fragment.Page4Fragment;
import com.example.administrator.myselvefapp.service.GuardService;
import com.example.administrator.myselvefapp.service.JobWakeUpService;
import com.example.administrator.myselvefapp.service.MyService;
import com.example.administrator.myselvefapp.service.StepService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
//    private LinearLayout llTabHome,llTabCart,llTabOder,llTabUser;
    private FrameLayout flContainer;
    Page1Fragment page1Fragment;
    Page2Fragment page2Fragment;
    Page3Fragment page3Fragment;
    Page4Fragment page4Fragment;
    List<Fragment> fragments = new ArrayList<>();
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    @Override
    public int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        flContainer = (FrameLayout) findViewById(R.id.contanierMain);
        radioGroup = (RadioGroup) findViewById(R.id.radioGrop);
        radioButton = (RadioButton) findViewById(R.id.main_home);
        radioGroup.setOnCheckedChangeListener(this);
        initTab();

    }
    private void initTab() {
        page1Fragment=new Page1Fragment();
        page2Fragment=new Page2Fragment();
        page3Fragment=new Page3Fragment();
        page4Fragment=new Page4Fragment();
        fragments.add(page1Fragment);
        fragments.add(page2Fragment);
        fragments.add(page3Fragment);
        fragments.add(page4Fragment);
        radioButton.setChecked(true);
        switchFragments(fragments.get(0));
    }

    private void switchFragments(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //先隐藏已显示的Fragment
        for (int i = 0; i < fragments.size(); i++) {
            if (!fragments.get(i).isHidden()) {
                transaction.hide(fragments.get(i));
            }
        }
        //判断要显示的Fragment是否已经添加，添加过的话直接显示，否则先添加再显示
        if (!fragment.isAdded()) {
            transaction.add(R.id.contanierMain, fragment).show(fragment).commit();
        } else {
            transaction.show(fragment).commit();
        }
    }

    /**
     * 防止fragment 切换时页面堆叠
     *
     * @param fragment
     */

    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        if (page1Fragment == null && fragment instanceof Page1Fragment) {
            page1Fragment = (Page1Fragment) fragment;
        } else if (page2Fragment == null && fragment instanceof Page2Fragment) {
            page2Fragment = (Page2Fragment) fragment;
        } else if (page3Fragment == null && fragment instanceof Page3Fragment) {
            page3Fragment = (Page3Fragment) fragment;
        } else if (page4Fragment == null && fragment instanceof Page4Fragment) {
            page4Fragment = (Page4Fragment) fragment;
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        int position = 0;
        switch (i) {
            case R.id.main_home: // 首页
                // 显示第一个Fragment并关闭动画效果
                position = 0;
                break;
            case R.id.main_tuan: // 购物车
                position = 1;
                break;
            case R.id.main_search: // 订单
                position = 2;
                break;
            case R.id.main_my: // 我的
                position = 3;
                break;

        }
        switchFragments(fragments.get(position));
    }

    @Override
    public void initData() {
        //这里测试一下service保活
//        startAllServices();
        startService(new Intent(this, MyService.class));
        //构建绑定服务的Intent对象
//        Intent bindIntent = new Intent(this, MyService.class);
//        //调用bindService()方法,以此停止服务
//
//        bindService(bindIntent,connection,BIND_AUTO_CREATE);
        //参数说明
        //第一个参数:Intent对象
        //第二个参数:上面创建的Serviceconnection实例
        //第三个参数:标志位
        //这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service
        //这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
    }

    /**
     * 开启所有Service
     */
    private void startAllServices()
    {
        System.out.println("startAllServices=======");
//        Intent intent = new Intent("com.example.administrator.myselvefapp.service.ProcessConnection");
//        intent.setPackage("com.example.administrator.myselvefapp.service");
        startService(new Intent(this,StepService.class));
        startService(new Intent(this, GuardService.class));
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {
            //版本必须大于5.0
            startService(new Intent(this, JobWakeUpService.class));
        }
    }
    //创建ServiceConnection的匿名类
    private ServiceConnection connection = new ServiceConnection() {

        //重写onServiceConnected()方法和onServiceDisconnected()方法
        //在Activity与Service建立关联和解除关联的时候调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        //在Activity与Service解除关联的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //实例化Service的内部类myBinder
            //通过向下转型得到了MyBinder的实例
//            myBinder = (MyService.MyBinder) service;
//            //在Activity调用Service类的方法
//            myBinder.service_connect_Activity();
        }
    };
}
