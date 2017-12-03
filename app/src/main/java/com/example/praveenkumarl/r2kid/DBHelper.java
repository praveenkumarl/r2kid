package com.example.praveenkumarl.r2kid;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "r2Kid.db";
    private static final int    DB_VERSION  = 1;
    public static final String KIDS_TABLE_NAME = "kids";
    public static final String KIDS_COLUMN_ID = "id";
    public static final String KIDS_COLUMN_NAME = "name";
    public static final String KIDS_COLUMN_DOB = "dob";
    public static final String KIDS_COLUMN_GENDER = "gender";
    public static final String KIDS_COLUMN_PHOTO = "photo";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table IF NOT EXISTS kids " +
                        "(id integer primary key autoincrement, name text,dob text,gender text,photo blob)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS kids");
        onCreate(db);
    }

    public void clearDB (){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS kids");
    }

    public boolean insertKid (String name, String dob, String gender,byte[] photo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KIDS_COLUMN_NAME, name);
        contentValues.put(KIDS_COLUMN_DOB, dob);
        contentValues.put(KIDS_COLUMN_GENDER, gender);
        contentValues.put(KIDS_COLUMN_PHOTO,photo);
        db.insert("kids", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from kids where id="+id+"", null );
        return res;
    }

    public Cursor getName(String dob) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor nameList =  db.rawQuery( "select name from kids where dob='"+dob+"'", null);
        return nameList;
    }

    public Cursor getPhoto(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor photo =  db.rawQuery( "select photo from kids where id='"+id+"'", null);
        return photo;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, KIDS_TABLE_NAME);
        return numRows;
    }

    public boolean updateKids (Integer id, String name, String dob, String gender, byte[] photo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KIDS_COLUMN_NAME, name);
        contentValues.put(KIDS_COLUMN_DOB, dob);
        contentValues.put(KIDS_COLUMN_GENDER, gender);
        contentValues.put(KIDS_COLUMN_PHOTO,photo);
        db.update("kids", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public void deleteRecord (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM kids WHERE id ='" + id + "'");
    }

    public void deleteRecord (String inTableName, String inDob) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + inTableName + " WHERE dob ='" + inDob + "'");

    }

    public void deleteAllRecords () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ KIDS_TABLE_NAME);
    }

    public ArrayList<kid_struct> getAllRecords() {
        ArrayList<kid_struct> array_list = new ArrayList<kid_struct>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from kids", null );

        if(res.getCount()>0) {
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                kid_struct kidStructRec = new kid_struct();
                kidStructRec.id = res.getInt(res.getColumnIndex(KIDS_COLUMN_ID));
                kidStructRec.name = res.getString(res.getColumnIndex(KIDS_COLUMN_NAME));
                kidStructRec.dob = res.getString(res.getColumnIndex(KIDS_COLUMN_DOB));
                kidStructRec.gender = res.getString(res.getColumnIndex(KIDS_COLUMN_GENDER));
                kidStructRec.photo = res.getBlob(res.getColumnIndex(KIDS_COLUMN_PHOTO));

                array_list.add(kidStructRec);
                res.moveToNext();
            }
        }

        return array_list;

    }
}