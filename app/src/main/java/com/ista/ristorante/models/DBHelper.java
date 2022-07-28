package com.ista.ristorante.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Ristorante.db";

    public static abstract class PlatoEntry implements BaseColumns {
        public static final String TABLE_NAME ="platos";
        public static final String CODIGO = "codigo";
        public static final String NOMBRE = "nombre";
        public static final String TIPO = "tipo";
        public static final String INGREDIENTE = "ingrediente";
        public static final String COSTO = "costo";
        public static final String PVP = "pvp";
    }

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PlatoEntry.TABLE_NAME + " ("
                + PlatoEntry.CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PlatoEntry.NOMBRE + " TEXT NOT NULL,"
                + PlatoEntry.TIPO + " TEXT NOT NULL,"
                + PlatoEntry.INGREDIENTE + " TEXT NOT NULL,"
                + PlatoEntry.COSTO + " REAL NOT NULL,"
                + PlatoEntry.PVP + " REAL NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Plato> listAll() {
        List<Plato> people = new ArrayList<>();
        String query = "SELECT * FROM " + PlatoEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Plato person = new Plato(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
                people.add(person);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return people;
    }

    public boolean insert(Plato p)  {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PlatoEntry.NOMBRE, p.getNombre());
        cv.put(PlatoEntry.TIPO, p.getTipo());
        cv.put(PlatoEntry.INGREDIENTE, p.getIngredientes());
        cv.put(PlatoEntry.COSTO, p.getCosto());
        cv.put(PlatoEntry.PVP, p.getPvp());
        long result = DB.insert(PlatoEntry.TABLE_NAME, null, cv);
        return result != -1;
    }

    public boolean delete(Plato p) {
        SQLiteDatabase db = this.getWritableDatabase();
        String noquery = "DELETE FROM " + PlatoEntry.TABLE_NAME
                + " WHERE " + PlatoEntry.CODIGO + " = " + p.getCodigo();
        Cursor cursor = db.rawQuery(noquery, null);
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

    public int update(Plato p) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        cv.put(PlatoEntry.NOMBRE, p.getNombre());
        cv.put(PlatoEntry.TIPO, p.getTipo());
        cv.put(PlatoEntry.INGREDIENTE, p.getIngredientes());
        cv.put(PlatoEntry.COSTO, p.getCosto());
        cv.put(PlatoEntry.PVP, p.getPvp());
        String[] str = {String.valueOf(p.getCodigo())};
        int result = db.update(PlatoEntry.TABLE_NAME, cv, PlatoEntry.CODIGO + "= ?", str);
        return result;
    }

}
