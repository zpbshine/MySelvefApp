package com.example.administrator.myselvefapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2018/1/27.
 */

public abstract class BaseFragment extends Fragment {
    private Context context;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    public abstract void intView() ;
    public abstract void intData() ;

}
