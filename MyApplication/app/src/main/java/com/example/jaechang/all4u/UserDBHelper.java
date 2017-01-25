package com.example.jaechang.all4u;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jaechang on 2017-01-25.
 */

public class UserDBHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "";
    private static String DB_NAME = "APYUser.db";
    private static String TABLE_NAME = "USERTABLE";
    private static String TABLE_DEPT = "LIKETABLE";

    private final Context context;

    public UserDBHelper(Context context){
        super(context, DB_NAME, null, 1);
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        this.context = context;
        create();
    }


    public void create(){
        SQLiteDatabase checkDB = null;

        File dir = new File(DB_PATH);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String path = DB_PATH + DB_NAME;
        File file = new File(path);
        if(!file.exists()) {
            checkDB = SQLiteDatabase.openOrCreateDatabase(path, null);

            String query = "CREATE TABLE '" + TABLE_NAME + "'( " +
                    "'name' TEXT DEFAULT 'NONAME', " +
                    "'deptNum' INTEGER DEFAULT 0, " +
                    "'recomDept1' TEXT, " +
                    "'recomDept2' TEXT, " +
                    "'recomDept3' TEXT " +
                    ")";
            checkDB.execSQL(query);

            query = "CREATE TABLE '" + TABLE_DEPT + "'( " +
                    "'name' TEXT " +
                    ")";
            checkDB.execSQL(query);

            checkDB.close();
        }
    }

    public boolean isLiked(String dept){
        String query = "SELECT name FROM " + TABLE_DEPT + " WHERE name = '" + dept +"';";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public void setLike(String dept){
        String query;
        if(isLiked(dept)) {
            query = "DELETE FROM " + TABLE_DEPT + " WHERE name = '" + dept +"';";
        }
        else
            query = "INSERT INTO "+ TABLE_DEPT + " (name) VALUES('" + dept+"');";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL(query);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        create();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
