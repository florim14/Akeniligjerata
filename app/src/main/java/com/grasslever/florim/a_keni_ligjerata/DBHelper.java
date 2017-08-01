package com.grasslever.florim.a_keni_ligjerata;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

// References:
// http://www.androidauthority.com/use-sqlite-store-data-app-599743/

/**
 * Created by ${USER} on ${DATE}
 */

class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "akeniligjerata.db";
    private static final int DB_VERSION = 1;

    private static final String SCHEDULE_TABLE_NAME = "schedule";
    private static final String SCHEDULE_COLUMN_ID = "_id";
    private static final String SCHEDULE_COLUMN_DAY = "day";
    private static final String SCHEDULE_COLUMN_CLASSNUMBER = "classnumber";
    private static final String SCHEDULE_COLUMN_CLASSNAME = "classname";
    private static final String SCHEDULE_COLUMN_STARTTIME = "starttime";
    private static final String SCHEDULE_COLUMN_ENDTIME = "endtime";

    public DBHelper(Context context) {
        super(context, DB_NAME , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE schedule (_id INT PRIMARY KEY NOT NULL, day TEXT NOT NULL, classnumber TEXT NOT NULL, " +
                "classname TEXT NOT NULL, starttime TEXT NOT NULL, endtime TEXT NOT NULL)");
        db.execSQL("CREATE TABLE comments (_id INT PRIMARY KEY NOT NULL, classroom TEXT NOT NULL, commentcontent TEXT NOT NULL, reg_date TEXT NOT NULL)");
    }

    public void insertLecture(int id, String day, String classnumber, String classname, String starttime, String endtime) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHEDULE_COLUMN_ID, id);
        contentValues.put(SCHEDULE_COLUMN_DAY, day);
        contentValues.put(SCHEDULE_COLUMN_CLASSNUMBER, classnumber);
        contentValues.put(SCHEDULE_COLUMN_CLASSNAME, classname);
        contentValues.put(SCHEDULE_COLUMN_STARTTIME, starttime);
        contentValues.put(SCHEDULE_COLUMN_ENDTIME, endtime);
        db.insert(SCHEDULE_TABLE_NAME, null, contentValues);
    }

    public void insertLectureOrIgnore(int id, String day, String classnumber, String classname, String starttime, String endtime){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT OR IGNORE INTO schedule(_id, day, classnumber, classname, starttime, endtime) VALUES('"+id+"','"+day+"','"+classnumber+"','"+classname+"','"+starttime+"','"+endtime+"')");
    }

    public void insertCommentOrIgnore(int id, String classroom, String content, String reg_date){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT OR IGNORE INTO comments(_id, classroom, commentcontent, reg_date) VALUES('"+id+"','"+classroom+"','"+content+"','"+reg_date+"')");
    }

    public Cursor getAllLectures() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "SELECT * FROM " + SCHEDULE_TABLE_NAME, null );
    }

    public Cursor getTodayLecturesTimes(String classnumber) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String day = new SimpleDateFormat("EEEE",Locale.ENGLISH).format(date.getTime());
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select starttime,endtime from schedule where day='"+day+"' and classnumber='"+classnumber+"'",null);
    }

    public Cursor getClassComments(String classroom) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from comments where classroom='"+classroom+"' order by reg_date desc",null);
    }

    public Cursor getTodayLectures(String classnumber) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from schedule where day='"+day+"' and classnumber='"+classnumber+"'",null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // In case the db needs to upgraded, we just drop and recreate
        db.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_TABLE_NAME);
        onCreate(db);
    }
}