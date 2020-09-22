package com.example.buidemapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GestorHelper extends SQLiteOpenHelper {
    // database version
    private static final int database_VERSION = 1;

    // database name
    private static final String database_NAME = "GestorArticulosDataBase";

    public GestorHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    private String LLISTA_MAQUINAS =
            "CREATE TABLE maquinas ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "client TEXT NOT NULL," +    //obligatorio
                    "adreca TEXT NOT NULL," +    //obligatorio
                    "codi_postal TEXT NOT NULL," +   //obligatorio
                    "poblacio TEXT NOT NULL," +      //obligatorio
                    "telefon TEXT," +
                    "email TEXT," +
                    "numero_maquina INTEGER NOT NULL," +     //obligatorio
                    "fecha TEXT," +
                    "tipo_maquina INTEGER NOT NULL," +          //obligatorio
                    "zona INTEGER NOT NULL," +               //obligatorio
                    "FOREIGN KEY(zona) REFERENCES zonas(_id) ON DELETE RESTRICT," +         //CASCADE
                    "FOREIGN KEY(tipo_maquina) REFERENCES tipos_maquinas(_id) ON DELETE RESTRICT)";     //CASCADE

    private String LLISTA_ZONAS =
            "CREATE TABLE zonas ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descripcion TEXT)";

    private String LLISTA_TIPO_MAQUINAS =
            "CREATE TABLE tipos_maquinas ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descripcion TEXT)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LLISTA_MAQUINAS);
        db.execSQL(LLISTA_ZONAS);
        db.execSQL(LLISTA_TIPO_MAQUINAS);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Database schema upgrade code goes here

        /*String buildSQL = "DROP TABLE IF EXISTS  articulos";
        String buildSQL2 = "DROP TABLE IF EXISTS  historial";


        sqLiteDatabase.execSQL(buildSQL);       // drop previous table
        sqLiteDatabase.execSQL(buildSQL2);

        onCreate(sqLiteDatabase); */              // create the table from the beginning*/

        /*if(oldVersion < 2) {
            sqLiteDatabase.execSQL(VIEW_HISTORIAL);
        }*/

    }
}
