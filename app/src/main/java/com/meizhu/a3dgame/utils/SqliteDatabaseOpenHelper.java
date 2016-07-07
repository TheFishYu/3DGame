package com.meizhu.a3dgame.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SqliteDatabaseOpenHelper extends SQLiteOpenHelper {

    public SqliteDatabaseOpenHelper(Context context) {
        super(context,
                "news.db",//数据库名称
                null,
                1);
        Log.i("aaa", "构造方法执行了...........");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists news(id integer primary key autoincrement," +
                "newid varchar(20)," +
                "typeid varchar(20)," +
                "title varchar(50)," +
                "shorttitle varchar(50)," +
                "writer varchar(50)," +
                "source varchar(50)," +
                "litpic varchar(50)," +
                "description varchar(50)," +
                "typedir varchar(50)," +
                "typename varchar(50)," +
                "arcurl varchar(50))");
        Log.i("aaa", db.hashCode()+"---"+"onCreate执行了...........");
    }

    //升级数据库时，调用的方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
