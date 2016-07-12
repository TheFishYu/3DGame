package com.meizhu.a3dgame.asynctask;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;

import com.meizhu.a3dgame.News;
import com.meizhu.a3dgame.adapter.MyAdapter;
import com.meizhu.a3dgame.utils.FileUtils;
import com.meizhu.a3dgame.utils.HttpUtils;
import com.meizhu.a3dgame.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kun Yu on 2016/7/8.
 */
public class DownDataAscytast extends AsyncTask<String, Void, List<News>> {
    String urlPath;
    List<News> data, data0;
    ListView listView;
    MyAdapter myAdapter;

    public DownDataAscytast(ListView listView, MyAdapter myAdapter, List<News> data0) {
        this.listView = listView;
        this.myAdapter = myAdapter;
        this.data0 = data0;
    }


    @Override
    protected List<News> doInBackground(String... params) {
        urlPath = params[0];
        Log.i("aaa", "网址：" + urlPath);
        data = new ArrayList<>();

        byte[] result = HttpUtils.request(urlPath);
        if (result == null) {
            Log.i("aaa", "网络解析错误，result为空");
        }
        data = JsonUtils.jsontTranslate(new String(result));


        //保存图片到本地路径
        for (int i = 0; i < data.size(); i++) {
            //图片网络地址
            String imgurl = data.get(i).getLitpic();
            //图片子节流文件
            byte[] imgdata = HttpUtils.request(imgurl);
            //文件保存图片的名字
            String[] imgarr = imgurl.split("/");
            String filename = imgarr[imgarr.length - 1];
            FileUtils.saveFile(imgdata, filename);
            String imgpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + filename;
            data.get(i).setLitpic(imgpath);
        }
        Log.i("aaa","请求的数据大小"+data.size());
        return data;
    }

    @Override
    protected void onPostExecute(List<News> newses) {
        if (newses.size() == 0) {
            Log.i("aaa", "未知错误");
        }
        data0.addAll(newses);
        myAdapter.notifyDataSetChanged();
        Log.i("aaa","数据唤醒成功");
        super.onPostExecute(newses);
    }
}
