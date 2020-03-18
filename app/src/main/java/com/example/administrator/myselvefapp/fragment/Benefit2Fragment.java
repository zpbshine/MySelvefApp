package com.example.administrator.myselvefapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myselvefapp.R;
import com.example.administrator.myselvefapp.bean.BenifitBean;
import com.example.administrator.myselvefapp.contents.AppInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 张鹏博 on 2018/1/30.
 * 优惠页面
 */

public class Benefit2Fragment extends BaseFragment {
    private View view;
    private ListView listView;
    private Gson gson;
    private ArrayList<BenifitBean> benifitBeens = new ArrayList<>();
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.benefit_fragment,null);

        intView();
        intData();
        return view;
    }

    @Override
    public void intView() {
        listView = view.findViewById(R.id.listview);
        if (myAdapter == null){
            myAdapter = new MyAdapter();
        }
        listView.setAdapter(myAdapter);
    }

    @Override
    public void intData() {

        getDataFromNet();

    }
    /**
     从网络获取数据
     */
    private void getDataFromNet() {
        benifitBeens.clear();

        OkHttpUtils.post()
                .url(AppInterface.GET_BENIFIT_GOODS)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        String json = response.toString();
                        try {
                            JSONArray jsonArray = new JSONArray(json);
                            System.out.println(jsonArray.length());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (gson == null){
                            gson = new Gson();
                        }
                        Type lt=new TypeToken<List<BenifitBean>>(){}.getType();
                        List<BenifitBean> beans=gson.fromJson(json,lt);
                        benifitBeens.addAll(beans);
                        if (myAdapter != null){
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return benifitBeens.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null){
                view = View.inflate(getActivity(),R.layout.benifit_item,null);
                viewHolder = new ViewHolder();
                viewHolder.tvTitle = view.findViewById(R.id.tv_bebifit_title);
                viewHolder.tvContent = view.findViewById(R.id.tv_benifit_content);
                viewHolder.tvOldPrice = view.findViewById(R.id.tv_benifit_oldprice);
                viewHolder.tvNowPrice = view.findViewById(R.id.tv_benifit_nowprice);
                view.setTag(viewHolder);

            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            BenifitBean benifitBean = benifitBeens.get(i);
            viewHolder.tvTitle.setText(benifitBean.getMarket());
            viewHolder.tvContent.setText(benifitBean.getContent());
            viewHolder.tvOldPrice.setText("原价 : "+benifitBean.getOlderPrice()+"/元");
            viewHolder.tvNowPrice.setText("原价 : "+benifitBean.getNowPrice()+"/元");
            return view;
        }
    }
    class ViewHolder{
        TextView tvTitle,tvContent,tvOldPrice,tvNowPrice;
    }
}
