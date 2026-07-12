package br.edu.ifsp.sbv.exemplosqlite.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO <T extends Object> extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "arkham.sqlite3";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_VILAO = "viloes";

    protected Context context;
    protected String[] campos;
    protected String tableName;

    private static final String CREATE_TABLE_VILAO = "CREATE TABLE viloes ( "
            + " id integer primary key autoincrement NOT NULL,"
            + " nome varchar(100),"
            + " perigo integer);";

    public DAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_VILAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_VILAO);
        onCreate(db);
    }

    protected void closeDatabase(SQLiteDatabase db) {
        if(db != null && db.isOpen())
            db.close();
    }
}