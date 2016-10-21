package com.zhibo.jufan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhibo.jufan.bean.MyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王鹏龙 on 2016/10/12.
 */
public class MySqliteDao {

    private MySQLiteOpenHelpe mySQLiteOpenHelpe;
    private SQLiteDatabase sqLiteDatabase;

    public MySqliteDao(Context context) {
        //帮助类
        mySQLiteOpenHelpe = new MySQLiteOpenHelpe(context);

    }

    /**
     * 添加数据
     * @param bstu
     */
    public void insert(MyBean bstu) {

        //得到数据库
        sqLiteDatabase = mySQLiteOpenHelpe.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("midheadimg", bstu.getMidheadimg());
        values.put("name", bstu.getName());
        values.put("place", bstu.getPlace());
        values.put("video", bstu.getVideo());
        sqLiteDatabase.insert("student", null, values);
        sqLiteDatabase.close();

    }

    /**
     * 查询
     * @return
     */
    public List<MyBean> selecte() {
        List<MyBean> list = new ArrayList<>();
        //得到数据库
        sqLiteDatabase = mySQLiteOpenHelpe.getReadableDatabase();
        Cursor cuursor = sqLiteDatabase.query("student", new String[]{"id", "midheadimg", "name", "place", "video"}, null, null, null, null, null);
        while (cuursor.moveToNext()) {
            int id = cuursor.getInt(cuursor.getColumnIndex("id"));
            String image = cuursor.getString(cuursor.getColumnIndex("midheadimg"));
            String name = cuursor.getString(cuursor.getColumnIndex("name"));
            String price = cuursor.getString(cuursor.getColumnIndex("place"));
            String nuber = cuursor.getString(cuursor.getColumnIndex("video"));
            MyBean student = new MyBean();
            student.setId(id);
            student.setMidheadimg(image);
            student.setName(name);
            student.setPlace(price);
            student.setVideo(nuber);
            list.add(student);

        }
        sqLiteDatabase.close();
        return list;
    }

    /**
     * 删除
     * @param student
     * @return
     */
    public long delete(MyBean student) {
        //得到数据库
        sqLiteDatabase = mySQLiteOpenHelpe.getReadableDatabase();
        long reisert = sqLiteDatabase.delete("student", "id = ?", new String[]{student.getId() + ""});
        sqLiteDatabase.close();
        return reisert;
    }
}
