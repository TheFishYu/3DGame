package com.meizhu.a3dgame.utils;

import android.util.Log;

import com.meizhu.a3dgame.News;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kun Yu on 2016/6/24.
 * private int id;
 private int srotrank;
 private String title;
 private String shorttitle;
 private String writer;
 private String source;
 private String litpic ;
 private String keyword;
 private String description;
 private String typedir;
 private String typename;
 private String arcurl;
 */
public class JsonUtils {
    public static List<News> jsontTranslate(String json){
        News news = null;
        List<News> data = new ArrayList<News>();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject object1 = root.getJSONObject("data");
            Log.i("aaa",""+object1.length());
            for (int i = 0; i < 20; i++) {
                news = new News();
                JSONObject jsonObject = object1.getJSONObject(i+"");

                news.setId(jsonObject.getString("id"));
                news.setTypeid(jsonObject.getString("typeid"));
                news.setTitle(jsonObject.getString("title"));
                news.setShorttitle(jsonObject.getString("shorttitle"));
                news.setTypename(jsonObject.getString("typename"));
                news.setWriter(jsonObject.getString("writer"));
                news.setSource( jsonObject.getString("source"));
                news.setLitpic("http://www.3dmgame.com"+jsonObject.getString("litpic"));
                news.setDescription(jsonObject.getString("description"));

                data.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return data;
    }


}
