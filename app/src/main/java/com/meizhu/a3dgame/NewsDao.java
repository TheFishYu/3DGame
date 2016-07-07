package com.meizhu.a3dgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.meizhu.a3dgame.utils.SqliteDatabaseOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kun Yu on 2016/6/24.
 */

public class NewsDao {
    private SqliteDatabaseOpenHelper helper;

    public NewsDao(Context context) {
        helper = new SqliteDatabaseOpenHelper(context);
    }

    /**
     * 增加数据
     * @param news
     * @return
     */
    public  boolean insert(News news){
            SQLiteDatabase db = null;
        try {
            db = helper.getReadableDatabase();
            //创建内容值集合

            ContentValues values = new ContentValues();
            values.put("newid",news.getId());
            values.put("typeid",news.getTypeid());
            values.put("title",news.getTitle());
            values.put("shorttitle",news.getShorttitle());
            values.put("writer",news.getWriter());
            values.put("source",news.getSource());
            values.put("litpic",news.getLitpic());
            values.put("description",news.getDescription());
            values.put("typename",news.getTypename());

            long id_ = db.insert("news",null,values);
            Log.i("aaa",id_+"------------");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(db!=null&&db.isOpen()){
                db.close();
            }
        }
        return false;
    }
    /**
     * 获取所有的news list集合
     */

    public List<Map<String,Object>> getAllNewsList(){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = null;
        //获取数据库对象
        db = helper.getReadableDatabase();
        cursor = db.query("news",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            map = new HashMap<String,Object>();
            String shorttitle = cursor.getString(cursor.getColumnIndex("shorttitle"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String litpic = cursor.getString(cursor.getColumnIndex("litpic"));
            map.put("shorttitle",shorttitle);
            map.put("description",description);
            //图片以bitmap形式存到map
            map.put("litpic",litpic);
            data.add(map);
        }

        return data;
    }


}
