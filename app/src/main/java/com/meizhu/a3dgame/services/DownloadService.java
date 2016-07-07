package com.meizhu.a3dgame.services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.meizhu.a3dgame.News;
import com.meizhu.a3dgame.NewsDao;
import com.meizhu.a3dgame.R;
import com.meizhu.a3dgame.utils.FileUtils;
import com.meizhu.a3dgame.utils.HttpUtils;
import com.meizhu.a3dgame.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kun Yu on 2016/7/5.
 */
public class DownloadService extends Service{

    String urlStr = "http://www.3dmgame.com/sitemap/api.php?row=20&typeid=1&paging=1&page=";
    int pageIndex = 1;
    NewsDao newsDao;
    NotificationManager ntfManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //使用线程进行网络下载
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<News> data = new ArrayList<>();

                byte[] result = HttpUtils.request(urlStr + pageIndex);
                if (result == null) {
                    Log.i("aaa","网络解析错误，result为空");
                }
                data = JsonUtils.jsontTranslate(new String(result));

                //保存图片到本地路径
                for (int i = 0; i < data.size(); i++) {
                    //图片网络地址
                    String imgurl = data.get(i).getLitpic();
                    //图片子节流文件
                    byte[] imgdata =HttpUtils.request(imgurl);
                    //文件保存图片的名字
                    String[] imgarr = imgurl.split("/");
                    String filename = imgarr[imgarr.length-1];
                    FileUtils.saveFile(imgdata,filename);
                    String imgpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+"/"+filename;
                    data.get(i).setLitpic(imgpath);
                    Log.i("aaa","图片存储路径"+imgpath);
                    //判断SD卡是否存在该文件！！！！！！
                    //添加数据到数据库，添加成功后，发送通知
                    newsDao.insert(data.get(i));

                    if(i==19){
                        //发送通知
                        ntfManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
                        mBuilder.setContentText("数据库数据保存完毕");
                        mBuilder.setContentTitle("标题");
                        mBuilder.setTicker("来通知了");
                        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                        ntfManager.notify(1,mBuilder.build());
                    }
                }
            }
        }).start();
        Log.i("aaa","数据下载Service");
        return super.onStartCommand(intent, flags, startId);
    }
}
