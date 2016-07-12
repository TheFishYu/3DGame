package com.meizhu.a3dgame.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.meizhu.a3dgame.News;
import com.meizhu.a3dgame.adapter.GameGridViewAdapter;
import com.meizhu.a3dgame.utils.HttpUtils;
import com.meizhu.a3dgame.utils.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kun Yu on 2016/7/9.
 */
public class GameDownDataAsynctast extends AsyncTask<String,Void,List<HashMap<String,Object>>>{

    Context context;
    List<HashMap<String,Object>> mdata,data;
    GameGridViewAdapter gameGridViewAdapter;
    HashMap<String,Object> map;

    public GameDownDataAsynctast(Context context, GameGridViewAdapter gameGridViewAdapter, List<HashMap<String,
            Object>> mdata) {
        this.context = context;
        this.mdata = mdata;
        this.gameGridViewAdapter = gameGridViewAdapter;
    }

    @Override
    protected List<HashMap<String, Object>> doInBackground(String... params) {
        String strPath = params[0];
        byte[] request = HttpUtils.request(strPath);
        if(request!=null){
            List<News> newses = JsonUtils.jsontTranslate(new String(request));
            data = new ArrayList<>();
            for (int i = 0; i < newses.size(); i++) {
                map = new HashMap<String,Object>();
                map.put("title",newses.get(i).getTitle());
                String picUrl = newses.get(i).getLitpic();
                Log.i("aaa",picUrl+"");
                byte[] picB = HttpUtils.request(picUrl);
                Bitmap bitmap =  BitmapFactory.decodeByteArray(picB,0,
                        picB.length);
                map.put("pic",bitmap);
                data.add(map);
            }
        }

        return data;
    }

    @Override
    protected void onPostExecute(List<HashMap<String, Object>> hashMaps) {
        mdata.addAll(hashMaps);
        gameGridViewAdapter.notifyDataSetChanged();
        super.onPostExecute(hashMaps);
    }
}
