package com.example.administrator.myselvefapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myselvefapp.R;

/**
 * Created by Administrator on 2018/1/27.
 */

public class Page4Fragment extends BaseFragment{
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.page4_fragment,null);
        intView();
        intData();
        return view;
    }

    @Override
    public void intView() {

    }

    @Override
    public void intData() {

    }
}
