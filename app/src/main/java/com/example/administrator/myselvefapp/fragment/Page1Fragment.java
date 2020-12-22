package com.example.administrator.myselvefapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myselvefapp.R;

/**
 * Created by 张鹏博 on 2018/1/27.
 * 首页Fragment
 */

public class Page1Fragment extends BaseFragment{
    private View view;
    private TabLayout tab_index;
    private ViewPager vp_index;

    private  String[]  TITLES={"特惠","新闻","新政","交友"};
    private  Fragment[]   FRAGMENTS=new Fragment[]{new BenefitFragment(),new Benefit1Fragment(),new Benefit2Fragment(),new Benefit3Fragment()};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.page1_fragment,null);
        intView();
        intData();
        return view;
    }

    @Override
    public void intView() {
        tab_index = view.findViewById(R.id.tab_index);
        vp_index = view.findViewById(R.id.vp_index);

        CustomerViewPagerAdapter viewPagerAdapter=new CustomerViewPagerAdapter(getActivity().getSupportFragmentManager());
        vp_index.setAdapter(viewPagerAdapter);
        vp_index.setOffscreenPageLimit(2);
        for(int i=0;i<TITLES.length;i++){
            TabLayout.Tab tab=tab_index.newTab();
            tab.setText(TITLES[i]);
            tab_index.addTab(tab);
        }
        vp_index.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_index));
        //tab_index.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vp_index));
        tab_index.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vp_index));

    }

    @Override
    public void intData() {

    }




    protected  class CustomerViewPagerAdapter extends FragmentPagerAdapter {

        public CustomerViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FRAGMENTS[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }

}
