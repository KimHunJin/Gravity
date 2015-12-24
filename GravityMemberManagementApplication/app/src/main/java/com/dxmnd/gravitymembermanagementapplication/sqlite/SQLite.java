package com.dxmnd.gravitymembermanagementapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HunJin on 2015-12-11.
 */
public class SQLite extends SQLiteOpenHelper {
    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table `member` (number INTEGER PRIMARY KEY, name VARCHAR(30), gender VARCHAR(10), key VARCHAR(30), hp VARCHAR(30), email VARCHAR(150), position VARCHAR(50), introduce VARCHAR(200));");
        db.insert("member", null, insertValues("김헌진", "남", "20130911", "", "", "개발", "개발 못해요"));
        db.insert("member", null, insertValues("김용훈", "남", "20", "", "", "개발 디자인", "디발자 입니다"));
        db.insert("member", null, insertValues("강광용", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("곽웅휘", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("권동효", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("김경희", "여", "20", "", "", "", ""));
        db.insert("member", null, insertValues("김기범", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("김대준", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("김우진", "여", "20", "", "", "", ""));
        db.insert("member", null, insertValues("박지희", "여", "20", "", "", "", ""));
        db.insert("member", null, insertValues("박현경", "여", "20", "", "", "", ""));
        db.insert("member", null, insertValues("방정훈", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("백아름", "여", "20", "", "", "", ""));
        db.insert("member", null, insertValues("엄정일", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("윤재하", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("이동욱", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("이정환", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("이지연", "여", "20", "", "", "", ""));
        db.insert("member", null, insertValues("이철진", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("이태연", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("이혜원", "여", "20", "", "", "", ""));
        db.insert("member", null, insertValues("은지혜", "여", "20", "", "", "", ""));
        db.insert("member", null, insertValues("차석기", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("최민영", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("최재은", "여", "20", "", "", "", ""));
        db.insert("member", null, insertValues("최형민", "남", "20", "", "", "", ""));
        db.insert("member", null, insertValues("최솔빈", "여", "20", "", "", "", ""));
        db.insert("member", null, insertValues("김상국", "남", "20", "", "", "", ""));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS device";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(String query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public Cursor select(String query) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void update(String query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void delete(String query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public ContentValues insertValues(String name, String gender, String key, String hp, String email, String position, String introduce) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("gender", gender);
        contentValues.put("key", key);
        contentValues.put("hp", hp);
        contentValues.put("email", email);
        contentValues.put("position", position);
        contentValues.put("introduce", introduce);
        return contentValues;
    }
}
