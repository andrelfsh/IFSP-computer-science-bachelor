package br.edu.ifsp.sbv.exemplosqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.sbv.exemplosqlite.modelo.Vilao;

public class VilaoDAO extends DAO<Vilao> {

    private SQLiteDatabase database;

    public VilaoDAO(Context context) {
        super(context);
        campos = new String[]{"id", "nome", "perigo"};
        tableName = "viloes";
        database = getWritableDatabase();
    }

    public List<Vilao> listAll() {
        List<Vilao> list = new ArrayList<>();
        Cursor cursor = database.query(tableName, campos, null, null, null, null, "id DESC");

        if(cursor != null && cursor.moveToFirst()) {
            do {
                Vilao v = new Vilao();
                v.setId(cursor.getInt(0));
                v.setNome(cursor.getString(1));
                v.setPerigo(cursor.getInt(2));
                list.add(v);
            } while(cursor.moveToNext());
        }
        if(cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }

    public boolean salvar(Vilao vilao) {
        ContentValues values = new ContentValues();
        values.put("nome", vilao.getNome());
        values.put("perigo", vilao.getPerigo());
        return database.insert(tableName, null, values) > 0;
    }

    public boolean deletar(Integer id) {
        return database.delete(tableName, "id = ?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean atualizar(Vilao vilao) {
        ContentValues values = new ContentValues();
        values.put("nome", vilao.getNome());
        values.put("perigo", vilao.getPerigo());
        return database.update(tableName, values, "id = ?", new String[]{String.valueOf(vilao.getId())}) > 0;
    }
}