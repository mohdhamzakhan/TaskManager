package com.example.admin.projectacad.businessLogic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Hamza on 13-05-2016.
 */
public class Resource extends SQLiteOpenHelper implements IResource {
    private static final String DB_NAME = "TO_DO_TASK";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "Task";
    private static final String KEY_ID = "KEY_ID";
    private static final String KEY_DESCRIPTION = "KEY_DESCRIPTION";
    private static final String KEY_TITLE = "KEY_TITLE";
    private static final String KEY_STATUS = "KEY_STATUS";
    private static final String KEY_DATE = "KEY_DATE";
    SQLiteDatabase db;

    public Resource(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public String CreateDatabase(String[] ColumnName, String[] DataTypes) {
        return null;
    }

    @Override
    public ArrayList<To_Do> GetResultDatabase(int Status) {
        ArrayList<To_Do>task=new ArrayList<To_Do>();
        db=getReadableDatabase();
        String query = "SELECT * FROM "+DB_TABLE + " WHERE "+KEY_STATUS+" ="+Status+" ORDER BY strftime('%Y',KEY_DATE) , strftime('%m',KEY_DATE) , strftime('%d',KEY_DATE) ASC";
        Cursor cursor=db.rawQuery(query,null);
        Log.e("Query",query);
        if(cursor.moveToFirst())
        {
            do {
                int id=cursor.getInt(0);
                String desc=cursor.getString(2);
                String title= cursor.getString(1);
                String date=cursor.getString(3);
                int status=cursor.getInt(4);

                To_Do td=new To_Do(id,title,desc,date,status);
                task.add(td);
            }while (cursor.moveToNext());
        }
        return task;
    }

    @Override
    public String InsertDatabase(To_Do values) {
        int res=0;
        db=getWritableDatabase();
        ContentValues Cvalues=new ContentValues();
        Cvalues.put(KEY_TITLE,values.getTitle());
        Cvalues.put(KEY_DESCRIPTION,values.getDescription());
        Cvalues.put(KEY_DATE,values.getDate());
        try {
            db.insert(DB_TABLE,null,Cvalues);
        }
        catch (Exception e)
        {
            res=1;
        }
        finally {
            if(res==0)
            {
                return "Success";
            }
                else
            {
                return "Some problem while inserting the values into the database";
            }
        }
    }

    @Override
    public String DeleteDatabase(int id) {
        int res=0;
        db=getWritableDatabase();
        String query="DELETE FROM "+DB_TABLE+" WHERE KEY_ID="+id;
        try {
            db.execSQL(query);
        }
        catch (Exception e)
        {
            res=1;
        }
        finally {
            if(res==0)
                return "Success";
            else
                return "The Details with id="+id+" has been deleted";
        }
    }

    @Override
    public String UpdateDatabase(int id, int Status) {
        int res=0;
        db=getWritableDatabase();
        String query;
        if(Status==0) {
            query = "UPDATE " + DB_TABLE + " SET "+KEY_STATUS+"=1  WHERE "+KEY_ID+"=" + id;
        }
        else
        {
            query = "UPDATE " + DB_TABLE + " SET "+KEY_STATUS+"=1  WHERE "+KEY_ID+"=" + id;
        }
        try {
            db.execSQL(query);
        }
        catch (Exception e)
        {
            res=1;
        }
        finally {
            if(res==0)
                return "Success";
            else
                return "The Details with id="+id+" has been deleted";
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table="create table "+ DB_TABLE+ "("+KEY_ID+" integer primary key, "+KEY_TITLE+" text, "+KEY_DESCRIPTION+" text, "+KEY_DATE+" text, "+KEY_STATUS+" integer default 0)";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
