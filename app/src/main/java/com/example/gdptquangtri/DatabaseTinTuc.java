package com.example.gdptquangtri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTinTuc extends SQLiteOpenHelper {
    public DatabaseTinTuc(Context context) {
        super(context, "DBTinTuc", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GDPT_TABLE = "create table tb_GDPT(id Integer primary key autoincrement, title text,link text, pubdate text, hinhanh blob )";
        String CREATE_PhatSu_TABLE = "create table tb_PhatSu(id Integer primary key autoincrement, title text,link text, pubdate text, hinhanh blob )";
        String CREATE_VPGDPT_TABLE = "create table tb_VPGDPT(id Integer primary key autoincrement, title text,link text, pubdate text, hinhanh blob )";
        String CREATE_VPPhatSu_TABLE = "create table tb_VPPhatSu(id Integer primary key autoincrement, title text,link text, pubdate text,hinhanh blob )";
        db.execSQL(CREATE_GDPT_TABLE);
        db.execSQL(CREATE_PhatSu_TABLE);
        db.execSQL(CREATE_VPGDPT_TABLE);
        db.execSQL(CREATE_VPPhatSu_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_GDPT");
        db.execSQL("DROP TABLE IF EXISTS tb_PhatSu");
        db.execSQL("DROP TABLE IF EXISTS tb_VPGDPT");
        db.execSQL("DROP TABLE IF EXISTS tb_VPPhatSu");
        onCreate(db);
    }

    ///----------------------------------------------------------------------------------------
    public long insertGDPT(String title, String link, String pubdate, byte[] hinhanh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("link", link);
        values.put("pubdate", pubdate);
        values.put("hinhanh", hinhanh);
        long id = db.insert("tb_GDPT", null, values);
        db.close();
        return id;
    }

    public TinTucGDPT getGDPT(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("tb_GDPT", new String[]{"id", "title", "link", "pubdate", "hinhanh"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        TinTucGDPT contacts = new TinTucGDPT(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4));
        cursor.close();
        db.close();
        return contacts;
    }

    public List<TinTucGDPT> getAllGDPT() {
        List arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM tb_GDPT";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                arrayList.add(new TinTucGDPT(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4)));
            } while (cursor.moveToNext());

        }
        return arrayList;
    }
///----------------------------------------------------------------------------------------

    public long insertVPGDPT(String title, String link, String pubdate, byte[] hinhanh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("link", link);
        values.put("pubdate", pubdate);
        values.put("hinhanh", hinhanh);
        long id = db.insert("tb_VPGDPT", null, values);
        db.close();
        return id;
    }

    public TinTucGDPT getVPGDPT(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("tb_VPGDPT", new String[]{"id", "title", "link", "pubdate", "hinhanh"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            
            cursor.moveToFirst();
        }

        TinTucGDPT contacts = new TinTucGDPT(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4));
        cursor.close();
        db.close();
        return contacts;
    }

    public List<TinTucGDPT> getAllVPGDPT() {
        List arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM tb_VPGDPT";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                arrayList.add(new TinTucGDPT(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4)));
            } while (cursor.moveToNext());

        }
        return arrayList;
    }

    public void deleteVPGDPT(String title1) {


        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tb_VPGDPT", "title" + " = ?",
                new String[]{String.valueOf(title1)});
        db.close();
    }
///----------------------------------------------------------------------------------------


    public long insertPS(String title, String link, String pubdate, byte[] hinhanh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("link", link);
        values.put("pubdate", pubdate);
        values.put("hinhanh", hinhanh);
        long id = db.insert("tb_PhatSu", null, values);
        db.close();
        return id;
    }

    public NewsPhatSu getPS(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("tb_PhatSu", new String[]{"id", "title", "link", "pubdate", "hinhanh"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        NewsPhatSu contacts = new NewsPhatSu(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4));
        cursor.close();
        db.close();
        return contacts;
    }

    public List<NewsPhatSu> getAllPS() {
        List arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM tb_PhatSu";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                arrayList.add(new NewsPhatSu(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4)));
            } while (cursor.moveToNext());

        }
        return arrayList;
    }

    ///----------------------------------------------------------------------------------------

    public long insertVPPS(String title, String link, String pubdate, byte[] hinhanh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("link", link);
        values.put("pubdate", pubdate);
        values.put("hinhanh", hinhanh);
        long id = db.insert("tb_VPPhatSu", null, values);
        db.close();
        return id;
    }

    public NewsPhatSu getVPPS(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("tb_VPPhatSu", new String[]{"id", "title", "link", "pubdate", "hinhanh"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        NewsPhatSu contacts = new NewsPhatSu(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4));
        cursor.close();
        db.close();
        return contacts;
    }

    public List<NewsPhatSu> getAllVPPS() {
        List arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM tb_VPPhatSu";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                arrayList.add(new NewsPhatSu(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4)));
            } while (cursor.moveToNext());

        }
        return arrayList;
    }

    public void deleteVPPS(String title1) {


        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tb_VPPhatSu", "title" + " = ?",
                new String[]{String.valueOf(title1)});
        db.close();
    }
}
