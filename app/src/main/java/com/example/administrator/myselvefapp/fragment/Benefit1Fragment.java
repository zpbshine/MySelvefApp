package com.example.administrator.myselvefapp.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myselvefapp.Activity.MainActivity;
import com.example.administrator.myselvefapp.R;
import com.example.administrator.myselvefapp.bean.BenifitBean;
import com.example.administrator.myselvefapp.contents.AppInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Created by 张鹏博 on 2018/1/30.
 * 优惠页面
 */

public class Benefit1Fragment extends BaseFragment {
    private View view;
    private ListView listView;
    private Gson gson;
    private ArrayList<BenifitBean> benifitBeens = new ArrayList<>();
    private MyAdapter myAdapter;

    public static final String TAG = MainActivity.class.getName();
    public  final static int VEDIO_KU = 101;
    private String path = "";//文件路径
    private ProgressBar post_progress;
    private TextView post_text;
    EditText video_name;

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

        video_name = (EditText)view.findViewById(R.id.upload_video_name);
        post_progress = (ProgressBar) view.findViewById(R.id.post_progress);
        post_text = (TextView)view. findViewById(R.id.post_text);
        view.findViewById(R.id.video_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("dianjil ma =========");
                seleteVedio();

            }
        });
        view.findViewById(R.id.video_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(VideoUploadActivity.this, "路径："+basePath, Toast.LENGTH_LONG).show();
                if(path.equals(""))
                    Toast.makeText(getContext(), "请选择视频后，再点击上传！", Toast.LENGTH_LONG).show();
                else {
                    File file = new File( path);
                    String postUrl = "http://47.100.79.150:1803/driver_video/";

                    OkHttpUtils.postFile()
                            .addHeader("Content-Type", "multipart/x-mixed-replace")
                            .file(file)
                            .url(postUrl)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    System.out.println("1======="+e.getMessage().toString());
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    System.out.println("========"+response);
                                }
                            });



                }

            }
        });


        listView = view.findViewById(R.id.listview);
        if (myAdapter == null){
            myAdapter = new MyAdapter();
        }
        listView.setAdapter(myAdapter);


    }

    @SuppressLint("NewApi")
    public void seleteVedio() {
        // TODO 启动相册
        System.out.println("选视频==="+(getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED));
        if (getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码 // 第二个参数是一个字符串数组，里面是你需要申请的权限 可以设置申请多个权限 // 最后一个参数是标志你这次申请的权限，该常量在onRequestPermissionsResult中使用到
            requestPermissions( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 11);
        }else {
            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent,VEDIO_KU);
        }

    }

    /**
     * 选择回调
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // TODO 视频
            case VEDIO_KU:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        Uri uri = data.getData();
                        uri = geturi(getContext(), data);
                        File file = null;
                        if (uri.toString().indexOf("file") == 0) {
                            file = new File(new URI(uri.toString()));
                            path = file.getPath();
                        } else {
                            path = getPath(uri);
                            file = new File(path);
                        }
                        if (!file.exists()) {
                            break;
                        }
                        if (file.length() > 100 * 1024 * 1024) {
//                            "文件大于100M";
                            break;
                        }

                        video_name.setText(path);

                        //fileString = fileBase64String(uri,this);

                        //视频播放
//                        mVideoView.setVideoURI(uri);
//                        mVideoView.start();
                        //开始上传视频，
//                        submitVedio();
                    } catch (Exception e) {
                        String  a=e+"";
                    } catch (OutOfMemoryError e) {
                        String  a=e+"";
                    }
                }
                break;
        }

    }

    public static Uri geturi(Context context, android.content.Intent intent) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.ImageColumns._ID },
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                        Log.i("urishi", uri.toString());
                    }
                }
            }
        }
        return uri;
    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContext().getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
