package com.example.administrator.myselvefapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myselvefapp.R;
import com.example.administrator.myselvefapp.defineview.DragFloatActionButton;
import com.example.administrator.myselvefapp.utils.ToastUtil;

/**
 * Created by Administrator on 2018/1/27.
 * 这里写个自定义的悬浮按钮
 */

public class Page2Fragment extends BaseFragment{
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.page2_fragment,null);
        intView();
        intData();
        return view;
    }

    @Override
    public void intView() {
        DragFloatActionButton floatActionButton = view.findViewById(R.id.float_button);
        floatActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showShortToast(getActivity(),"点我干嘛=======");

            }

        });

        String result = "http://sj.qq.com/myapp/detail.htm?apkName=com.bxs.zchs.app?mid=1807040001&sid=1&pid=22324500&pri=0.1";
        String MachineID = getParamByName(result,"mid");
        String ProductID = getParamByName(result,"pid");
        String SlotNo = getParamByName(result,"sid");
        String Price = getParamByName(result,"pri");
        //联网去获取参数
        System.out.println("获得了吗这里====="+MachineID+"===="+ProductID+"==="+SlotNo+"==="+Price);
    }

    private   String getParamByName(String url, String name) {
        String result = "";
        int index = url.indexOf("?");
        String temp = url.substring(index + 1);
        String[] keyValue = temp.split("&");
        for (String str : keyValue) {
            if (str.contains(name)) {
                result = str.replace(name + "=", "");
                break;
            }
        }
        return result;
    }
    @Override
    public void intData() {

    }
}
