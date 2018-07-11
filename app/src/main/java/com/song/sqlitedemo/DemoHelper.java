package com.song.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by song on 2018/7/2.
 * Email：815464927@qq.com
 */
public class DemoHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DB_TABLE = "demo_table";

    public DemoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+DB_TABLE+"(id int,tellname varchar(20),tell varchar(20))";
        Log.d("song--->","create table demo_table");
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("song--->","update table demo_table");
    }
}
