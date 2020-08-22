package com.proyek.fahrulrizky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.proyek.fahrulrizky.adapter.PresensiModel;

import java.util.ArrayList;


public class SQLiteDBHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME="presensi_db";

    public static final String TABLE_NAME="cart";

    public static final String COL_ID ="id";
    public static final String COl_NAME ="name";
    public static final String COl_DATE ="date";


    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" +
                ""+COL_ID +" INTEGER PRIMARY KEY, " +
                ""+COl_NAME+" VARCHAR, " +
                ""+COl_DATE+" VARCHAR " +
                ")";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(PresensiModel model){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, model.getId());
        contentValues.put(COl_NAME, model.getName());
        contentValues.put(COl_DATE, model.getDate());
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public ArrayList<PresensiModel> getData() {
        ArrayList<PresensiModel> items = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                PresensiModel model = new PresensiModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                items.add(model);
            } while (cursor.moveToNext());
        }
        return items;
    }

    public int updateCart(PresensiModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, model.getId());
        contentValues.put(COl_NAME, model.getName());
        contentValues.put(COl_DATE, model.getDate());
        return db.update(TABLE_NAME,contentValues,COL_ID+"=?", new String[] {String.valueOf(model.getId())});
    }

    public void deleteCart(PresensiModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID+ " = ?",
                new String[]{String.valueOf(model.getId())});
        db.close();
    }

    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void deleteDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}
