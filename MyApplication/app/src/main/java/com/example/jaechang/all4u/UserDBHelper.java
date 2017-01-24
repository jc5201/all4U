package com.example.jaechang.all4u;

import android.content.Context;
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
    private static String DB_NAME = "AllPoYou.db";

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
        checkDB = SQLiteDatabase.openOrCreateDatabase(path, null);
        checkDB.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
