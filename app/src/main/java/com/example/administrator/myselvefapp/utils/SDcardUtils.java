package com.example.administrator.myselvefapp.utils;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import com.example.administrator.myselvefapp.Activity.BaseActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.sql.SQLOutput;

/**
 * Created by win on 2019/2/23.
 */

public class SDcardUtils {
    public static String TAG = "hahaha";
    private DownloadManager downloadManager;
    private long mTaskId;

    public static void downFile(Context context, final String url) {
        final ProgressDialog pBar = new ProgressDialog(context);    //进度条，在下载的时候实时更新进度，提高用户友好度
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setTitle("正在下载");
        pBar.setMessage("请稍候...");
        pBar.setProgress(0);
        pBar.setCancelable(false);
        pBar.show();
        new Thread() {
            public void run() {/*
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    int length = (int) entity.getContentLength();   //获取文件大小
                    pBar.setMax(length);                            //设置进度条的总长度
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory(),
                                "Test.apk");
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[10];   //这个是缓冲区，即一次读取10个比特，我弄的小了点，因为在本地，所以数值太大一 下就下载完了，看不出progressbar的效果。
                        int ch = -1;
                        int process = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            process += ch;
                            pBar.setProgress(process);       //这里就是关键的实时更新进度了！
                        }

                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */
                java.net.URL httpurl = null;
                HttpURLConnection conn = null;
                InputStream iStream = null;
                try {
                    httpurl = new java.net.URL(url);
                    conn = (HttpURLConnection) httpurl.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(20000);
                    iStream = conn.getInputStream();
                } catch (MalformedURLException e) {
                    Log.i(TAG, "MalformedURLException");
                    e.printStackTrace();
                } catch (Exception e) {
                    Log.i(TAG, "获得输入流失败");
                    e.printStackTrace();
                }
                FileOutputStream fos = null;
                try {
                    File apkFile = new File(
                            Environment.getExternalStorageDirectory(),
                            //app.getFilesDir(),
                            "haha.zip");

                    fos = new FileOutputStream(apkFile);
                } catch (FileNotFoundException e) {
                    Log.i(TAG, "获得输出流失败：new FileOutputStream(apkFile);");
                    e.printStackTrace();
                }
                BufferedInputStream bis = new BufferedInputStream(iStream);
                byte[] buffer = new byte[1024];
                int len;
                // 获取文件总长度
                int length = conn.getContentLength();

                Log.i("threadStatus", "开始下载" + length);

                double rate=(double)100/length;  //最大进度转化为100
                int total = 0;
                int times=0;//设置更新频率，频繁操作ＵＩ线程会导致系统奔溃
                try {
                    Log.i("threadStatus", "开始下载");
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        // 获取已经读取长度

                        total += len;
                        int p=(int)(total*rate);
                        Log.i("num", rate + "," + total + "," + p);

                        pBar.setProgress(p);
                        if(times>=512 || p==100)
                        {/*
                    这是防止频繁地更新通知，而导致系统变慢甚至崩溃。
                                                             非常重要。。。。。*/
                            Log.i("time", "time");
                            times=0;

                            //Message msg = Message.obtain();
                            //msg.what =p ;
                            //mHandler.sendMessage(msg);
                        }
                        times++;
                    }
                    fos.close();
                    bis.close();
                    iStream.close();
                    if(total==length)
                    {
                        //isFinished=true;
                        //mHandler.sendEmptyMessage(DOWNLOAD_COMPLETE);
                        Log.i(TAG, "下载完成结束");
                        //获取图并展示

                        pBar.cancel();


                    }

                    //mhandler.sendEmptyMessage(4);
                } catch (IOException e) {
                    Log.i(TAG, "异常中途结束");
                    //mHandler.sendEmptyMessage(DOWNLOAD_FAIL);
                    e.printStackTrace();

                    return;
                }
            }

        }.start();
    }

    public static void jieya(ImageView imageView) {
        //解压ZIP压缩包
         String savePath =Environment.getExternalStorageDirectory() + "/haha.zip";//下载文件的存储绝对路径
         String unZipPath = Environment.getExternalStorageDirectory() + "/zzsq";//解压的zip文件路径
        try {
            ZipUtils.UnZipFolder(savePath, unZipPath);
        } catch (Exception e) {
            System.out.println("exception===="+e.getMessage().toString());
            e.printStackTrace();
        }
        File file = new File(unZipPath + "/test/jinlan.png");
        System.out.println("存在吗==="+file.exists());
        if (file.exists()) {
            imageView.setImageURI(Uri.parse(unZipPath + "/test/jinlan.png"));
        }

    }


    public void downloadAPK(Context mContext, String versionUrl) {

        //创建下载任务

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionUrl));

        request.setAllowedOverRoaming(false);//漫游网络是否可以下载



        //设置文件类型，可以在下载结束后自动打开该文件

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        String mimeString =mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionUrl));

        request.setMimeType(mimeString);



        //在通知栏中显示，默认就是显示的

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);

        request.setVisibleInDownloadsUi(true);



        //sdcard的目录下的download文件夹，必须设置

        request.setDestinationInExternalPublicDir("/download/", "testfile");

        //request.setDestinationInExternalFilesDir(),也可以自己制定下载路径



        //将下载请求加入下载队列

        downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);

        //加入下载队列后会给该任务返回一个long型的id，

        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法

        mTaskId = downloadManager.enqueue(request);



        //注册广播接收者，监听下载状态

        mContext.registerReceiver(receiver,

                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    //广播接受者，接收下载状态

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override

        public void onReceive(Context context, Intent intent) {

            checkDownloadStatus();//检查下载状态

        }

    };

    //检查下载状态

    private void checkDownloadStatus() {

        DownloadManager.Query query = new DownloadManager.Query();

        query.setFilterById(mTaskId);//筛选下载任务，传入任务ID，可变参数

        Cursor c = downloadManager.query(query);

        if (c.moveToFirst()) {

            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));

            switch (status) {

                case DownloadManager.STATUS_PAUSED:

                    //MLog.i(">>>下载暂停");

                case DownloadManager.STATUS_PENDING:

                    //MLog.i(">>>下载延迟");

                case DownloadManager.STATUS_RUNNING:

                   // MLog.i(">>>正在下载");
                    System.out.println(">>>正在下载");

                    break;

                case DownloadManager.STATUS_SUCCESSFUL:

                    //MLog.i(">>>下载完成");
                    String downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "testfile";

                    System.out.println(">>>下载完成"+downloadPath);
                    //下载完成安装APK



                    break;

                case DownloadManager.STATUS_FAILED:

                   // MLog.i(">>>下载失败");
                    System.out.println(">>>下载失败");
                    break;

            }

        }

    }



}
