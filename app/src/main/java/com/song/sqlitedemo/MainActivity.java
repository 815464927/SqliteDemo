package com.song.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DemoHelper mDemoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void createDabase(){
        //mDemoHelper = new DemoHelper(this, "demo_db", null, DemoHelper.VERSION);
        Log.d("song--->","create db");
        mDemoHelper = new DemoHelper(this, "demo_db", null, DemoHelper.VERSION);
    }

    public void clickCreate(View v){
        createDabase();
    }

    public void clickInsert(View v){
        //id int,name varchar(20),tell varchar(20)
        if(null == mDemoHelper) createDabase();
        SQLiteDatabase db = mDemoHelper.getWritableDatabase();

        //方法一
//        ContentValues values = new ContentValues();
//        values.put("id","1");
//        values.put("tellname","song");
//        values.put("tell","110");
//        db.insert(DemoHelper.DB_TABLE,null,values);
//        Log.d("song--->","insert values id = 1,tellname = song,tell = 110");

        //方法二：
        String sqlInsert = "insert into "+DemoHelper.DB_TABLE+"(id,tellname,tell) values('3','InsertSong','111')";
        db.execSQL(sqlInsert);
        Log.d("song--->","insert values id = 3,song = InsertSong,tell = 111");

        db.close();
    }

    public void clickDelete(View v){
        if(null == mDemoHelper) createDabase();
        SQLiteDatabase db = mDemoHelper.getWritableDatabase();

        //方法一：
//        db.delete(DemoHelper.DB_TABLE,"id=?",new String[]{"1"});
//        Log.d("song--->","delete values id = 1");

        //方法二：
        String sqlDelete = "delete from "+DemoHelper.DB_TABLE+" where id = 3";
        db.execSQL(sqlDelete);
        Log.d("song--->","delete values id = 3");

        db.close();
    }

    public void clickquery(View v){
        if(null == mDemoHelper) createDabase();
        SQLiteDatabase db = mDemoHelper.getReadableDatabase();
        Cursor cursor = db.query(DemoHelper.DB_TABLE, /*new String[]{"id","tellname","tell"}*/null, /*"id=?"*/null, /*new String[]{"1"}*/null, null, null, null);
        //Cursor cursor = db.query(DemoHelper.DB_TABLE, new String[]{"id","tellname","tell"}, "id=?", new String[]{"1"}, null, null, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String tellname = cursor.getString(cursor.getColumnIndex("tellname"));
            String tell = cursor.getString(cursor.getColumnIndex("tell"));
            Log.d("song--->","query message id = "+id+",tellname = "+tellname+",tell = "+tell);
        }
        cursor.close();
        db.close();

    }

    public void clickUpdate(View v){
        if(null == mDemoHelper) createDabase();
        SQLiteDatabase db = mDemoHelper.getWritableDatabase();

        //方法一：
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("tellname","song111");
//        db.update(DemoHelper.DB_TABLE, contentValues, "id=?", new String[]{"1"});

        //方法二：
        String sqlUpdate = "update "+DemoHelper.DB_TABLE+" set tellname = 'songUpdate' where id = 3";
        db.execSQL(sqlUpdate);

        Log.d("song--->","update...");
        clickquery(v);
        db.close();
    }

}
