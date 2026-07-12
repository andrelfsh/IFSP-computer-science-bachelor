package br.edu.ifsp.sbv.webservices.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.edu.ifsp.sbv.webservices.modelo.Personagem;

public class BatmanDatabaseDAO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "batcaverna_intel.sqlite3";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_INTEL = "fichas_taticas";

    public BatmanDatabaseDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTable = "CREATE TABLE " + TABLE_INTEL + " ( "
                + " id integer primary key autoincrement NOT NULL,"
                + " nome varchar(50) UNIQUE,"
                + " inteligenca varchar(20),"
                + " nome_real varchar(100));";
        database.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTEL);
        onCreate(db);
    }

    public Personagem buscarLocal(String nome) {
        SQLiteDatabase db = getReadableDatabase();
        Personagem p = null;
        Cursor cursor = db.query(TABLE_INTEL, new String[]{"nome", "inteligenca", "nome_real"},
                "nome = ?", new String[]{nome.toLowerCase().trim()}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            p = new Personagem();
            p.setNome(cursor.getString(0));
            p.setInteligenca(cursor.getString(1));
            p.setNomeReal(cursor.getString(2));
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return p;
    }

    public boolean salvarLocal(Personagem p) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", p.getNome().toLowerCase().trim());
        values.put("inteligenca", p.getInteligenca());
        values.put("nome_real", p.getNomeReal());
        return db.insert(TABLE_INTEL, null, values) > 0;
    }
}