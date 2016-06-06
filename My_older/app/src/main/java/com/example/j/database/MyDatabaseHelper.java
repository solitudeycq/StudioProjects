package com.example.j.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by solitudeycq on 16/4/24.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context mcontext;
    public static final String CREATE_USERS = "create table Users ("
            +"id integer primary key autoincrement, "
            +"username text, "
            +"password text)";
    public static final String CREATE_CONTACTS = "create table Contacts ("
            +"id integer primary key autoincrement, "
            +"name text, "
            +"phone text, "
            +"remarks text)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USERS);
        Toast.makeText(mcontext,"Users create succeeded",Toast.LENGTH_SHORT).show();
        sqLiteDatabase.execSQL(CREATE_CONTACTS);
        Toast.makeText(mcontext,"Contacts create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
