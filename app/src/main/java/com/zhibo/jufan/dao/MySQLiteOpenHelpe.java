package com.zhibo.jufan.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 王鹏龙 on 2016/10/12.
 */
/*
* 1.数据库帮助类参数
* 2.创建表格
* 3.数据库升级
* */
public class MySQLiteOpenHelpe extends SQLiteOpenHelper {
    public MySQLiteOpenHelpe(Context context) {
        super(context, "1409K", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(id integer primary key autoincrement,midheadimg varchar(100),name varchar(50),place varchar(50),video varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
