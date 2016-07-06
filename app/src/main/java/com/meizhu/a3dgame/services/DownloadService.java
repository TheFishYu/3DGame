package com.meizhu.a3dgame.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Kun Yu on 2016/7/5.
 */
public class DownloadService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //使用线程进行网络下载
        Log.i("aaa","数据下载Service");
        return super.onStartCommand(intent, flags, startId);
    }
}
