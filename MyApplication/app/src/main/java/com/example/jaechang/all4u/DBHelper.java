package com.example.jaechang.all4u;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.util.Pair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaechang on 2017-01-24.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "";
    private static String DB_NAME = "AllPoYou.db";
    private static String TABLE_NAME = "department";
    private static String TABLE_HOLLAND = "holland";

    private static Boolean flag = false;

    private final Context context;

    public DBHelper(Context context){
        super(context, DB_NAME, null, 1);
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        this.context = context;
        create();
    }


    public List<String> getDepartment(){
        List<String> depart = new ArrayList<>();
        try{
            String query = "SELECT name FROM " + TABLE_NAME + ";";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);

            while(cursor.moveToNext()){
                String str = cursor.getString(0);
                depart.add(str);
            }
            db.close();
        }catch (Exception e){
            Log.d("DB", e.getMessage());
        }

        return depart;
    }

    public String getContentsPath(String dept){
        String path = "";
        try{
            String query = "SELECT path FROM " + TABLE_NAME + " WHERE name = \"" + dept + "\";";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);

            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                path = cursor.getString(0);
            }
            db.close();
        }catch (Exception e){
            Log.d("DB", e.getMessage());
        }
        return path;
    }

    public int getNum(String dept){
        int num=0;
        try{
            String query = "SELECT length FROM " + TABLE_NAME + " WHERE name = \"" + dept + "\";";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);

            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                num = cursor.getInt(0);
            }
            db.close();
        }catch (Exception e){
            Log.d("DB", e.getMessage());
        }
        return num;
    }

    public List<Pair<String, String>> getHolland(){
        List<Pair<String, String>> holland = new ArrayList<>();
        try{
            String query = "SELECT content, type FROM " + TABLE_HOLLAND + ";";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor = db.rawQuery(query, null);

            while(cursor.moveToNext()){
                String con = cursor.getString(0);
                String type = cursor.getString(1);
                holland.add(new Pair<String, String>(con, type));
            }
            db.close();
        }catch (Exception e){
            Log.d("DB", e.getMessage());
        }

        return holland;
    }


    public void create(){

        //if (!checkDataBase()) {
            try {
                if(flag == false) {
                    copyDataBase();
                    flag = true;
                }
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        //}
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //e.printStackTrace();
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = context.getAssets().open(DB_NAME);

        String outFileName = DB_PATH;
        File dir = new File(outFileName);
        outFileName += DB_NAME;
        File file = new File(outFileName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //if(!file.exists()) {

            file.createNewFile();
            FileOutputStream myOutput = new FileOutputStream(outFileName);


            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
        //}
        myInput.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
