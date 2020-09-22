package com.example.buidemapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GestorDatasource {
    public static final String table_MAQUINAS = "maquinas";
    public static final String MAQUINAS_ID = "_id";
    public static final String MAQUINAS_CLIENT = "client";
    public static final String MAQUINAS_ADRECA = "adreca";
    public static final String MAQUINAS_CODI_POSTAL = "codi_postal";
    public static final String MAQUINAS_POBLACIO = "poblacio";
    public static final String MAQUINAS_TELEFON = "telefon";
    public static final String MAQUINAS_EMAIL = "email";
    public static final String MAQUINAS_NUMERO = "numero_maquina";
    public static final String MAQUINAS_FECHA = "fecha";
    public static final String MAQUINAS_TIPO = "tipo_maquina";
    public static final String MAQUINAS_ZONA = "zona";

    public static final String table_ZONAS = "zonas";
    public static final String ZONAS_ID = "_id";
    public static final String ZONAS_DESCRIPCION = "descripcion";

    public static final String table_TIPO_MAQUINA = "tipos_maquinas";
    public static final String TIPO_MAQUINA_ID = "_id";
    public static final String TIPO_MAQUINA_DESCRIPCION = "descripcion";

    private GestorHelper dbHelper;
    private static SQLiteDatabase dbW, dbR;

    // CONSTRUCTOR
    public GestorDatasource(Context ctx) {
        // En el constructor directament obro la comunicació amb la base de dades
        dbHelper = new GestorHelper(ctx);

        // amés també construeixo dos databases un per llegir i l'altre per alterar
        open();
    }

    public long insertDataMaquinas(/*String _id,*/ String client, String adreca, String codi_postal, String poblacio, String telefon, String email, String numero_maquina, String fecha, String tipo_maquina, String zona){
        ContentValues values = new ContentValues();
        //values.put(MAQUINAS_ID, _id);  //textoCodigo.getText().toString()
        values.put(MAQUINAS_CLIENT, client);  //textoDescripcion.getText().toString()
        values.put(MAQUINAS_ADRECA, adreca);  //textoPvp.getText().toString()
        values.put(MAQUINAS_CODI_POSTAL, codi_postal);  //textoStock.getText().toString()
        values.put(MAQUINAS_POBLACIO, poblacio);  //textoCodigo.getText().toString()
        values.put(MAQUINAS_TELEFON, telefon);  //textoDescripcion.getText().toString()
        values.put(MAQUINAS_EMAIL, email);  //textoPvp.getText().toString()
        values.put(MAQUINAS_NUMERO, numero_maquina);  //textoStock.getText().toString()
        values.put(MAQUINAS_FECHA, fecha);  //textoPvp.getText().toString()
        values.put(MAQUINAS_TIPO, tipo_maquina);  //textoPvp.getText().toString()
        values.put(MAQUINAS_ZONA, zona);  //textoStock.getText().toString()

        long result = dbW.insert(table_MAQUINAS, null, values);

        return result;
    }

    public void updateMaquinas(long id, String client, String adreca, String codi_postal, String poblacio, String telefon, String email, String numero_maquina, String fecha, String tipo_maquina, String zona) {
        // Modifiquem els valors de las tasca amb clau primària "id"
        ContentValues values = new ContentValues();
        values.put(MAQUINAS_CLIENT, client);  //textoDescripcion.getText().toString()
        values.put(MAQUINAS_ADRECA, adreca);  //textoPvp.getText().toString()
        values.put(MAQUINAS_CODI_POSTAL, codi_postal);  //textoStock.getText().toString()
        values.put(MAQUINAS_POBLACIO, poblacio);  //textoCodigo.getText().toString()
        values.put(MAQUINAS_TELEFON, telefon);  //textoDescripcion.getText().toString()
        values.put(MAQUINAS_EMAIL, email);  //textoPvp.getText().toString()
        values.put(MAQUINAS_NUMERO, numero_maquina);  //textoStock.getText().toString()
        values.put(MAQUINAS_FECHA, fecha);  //textoPvp.getText().toString()
        values.put(MAQUINAS_TIPO, tipo_maquina);  //textoPvp.getText().toString()
        values.put(MAQUINAS_ZONA, zona);  //textoStock.getText().toString()

        dbW.update(table_MAQUINAS,values, MAQUINAS_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Cursor viewDataMaquinas(){
        //String query = "Select Codigo, Descripcion, Estoc from "+ TABLE_NAME;
        //Cursor cursor = db.rawQuery(query, null);
        //return cursor;
        return dbR.query(table_MAQUINAS, new String[]{MAQUINAS_ID,MAQUINAS_CLIENT,MAQUINAS_ADRECA,MAQUINAS_CODI_POSTAL, MAQUINAS_POBLACIO, MAQUINAS_TELEFON, MAQUINAS_EMAIL, MAQUINAS_NUMERO, MAQUINAS_FECHA, MAQUINAS_TIPO, MAQUINAS_ZONA},
                null, null,
                null, null, MAQUINAS_CLIENT);

    }

    public Cursor taskMaquinas(long id) {
        // Retorna un cursor només amb el id indicat
        // Retornem les tasques que el camp DONE = 1
        return dbR.query(table_MAQUINAS, new String[]{MAQUINAS_ID,MAQUINAS_CLIENT,MAQUINAS_ADRECA,MAQUINAS_CODI_POSTAL,MAQUINAS_POBLACIO, MAQUINAS_TELEFON, MAQUINAS_EMAIL, MAQUINAS_NUMERO, MAQUINAS_FECHA, MAQUINAS_TIPO, MAQUINAS_ZONA},
                MAQUINAS_ID+ "=?", new String[]{String.valueOf(id)},
                null, null, null);

    }

    public Cursor cargaMaquinas() {
        // Retornem tots els llocs
        Cursor cursor = dbR.rawQuery("SELECT client, tipo_maquina, zona FROM maquinas",null);
        return cursor;
    }

    public Cursor checkIfMaquinaEnZona(long id) {
        /*return dbR.query(table_ZONAS, new String[]{ZONAS_ID, ZONAS_DESCRIPCION},
                MAQUINAS_ZONA + "=?", new String[]{String.valueOf(id)}, null, null, null);*/
        return dbR.query(table_MAQUINAS, new String[]{MAQUINAS_ID},
                MAQUINAS_ZONA + "=?", new String[]{String.valueOf(id)}, null, null, null);
    }

    public Cursor checkIfMaquinaEnTipo(long id) {
        /*return dbR.query(table_ZONAS, new String[]{ZONAS_ID, ZONAS_DESCRIPCION},
                MAQUINAS_ZONA + "=?", new String[]{String.valueOf(id)}, null, null, null);*/
        return dbR.query(table_MAQUINAS, new String[]{MAQUINAS_ID},
                MAQUINAS_TIPO + "=?", new String[]{String.valueOf(id)}, null, null, null);
    }

    public long insertDataZonas(/*String _id,*/ String descripcion){
        ContentValues values = new ContentValues();
        values.put(ZONAS_DESCRIPCION, descripcion);  //textoDescripcion.getText().toString()

        long result = dbW.insert(table_ZONAS, null, values);

        return result;
    }

    public void updateZonas(long id, String descripcion) {
        // Modifiquem els valors de las tasca amb clau primària "id"
        ContentValues values = new ContentValues();
        values.put(ZONAS_DESCRIPCION, descripcion);

        dbW.update(table_ZONAS,values, ZONAS_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Cursor viewDataZonas(){
        //String query = "Select Codigo, Descripcion, Estoc from "+ TABLE_NAME;
        //Cursor cursor = db.rawQuery(query, null);
        //return cursor;
        return dbR.query(table_ZONAS, new String[]{ZONAS_ID,ZONAS_DESCRIPCION},
                null, null,
                null, null, ZONAS_DESCRIPCION);

    }

    public Cursor viewIdZonas(){
        //String query = "Select Codigo, Descripcion, Estoc from "+ TABLE_NAME;
        //Cursor cursor = db.rawQuery(query, null);
        //return cursor;
        return dbR.query(table_ZONAS, new String[]{ZONAS_ID},
                null, null,
                null, null, null);

    }

    public Cursor taskZonas(long id) {
        // Retorna un cursor només amb el id indicat
        // Retornem les tasques que el camp DONE = 1
        return dbR.query(table_ZONAS, new String[]{ZONAS_ID,ZONAS_DESCRIPCION},
                ZONAS_ID+ "=?", new String[]{String.valueOf(id)},
                null, null, null);

    }

    public long insertDataTipoMaquina(/*String _id,*/ String descripcion){
        ContentValues values = new ContentValues();
        //values.put(MAQUINAS_ID, _id);  //textoCodigo.getText().toString()
        values.put(TIPO_MAQUINA_DESCRIPCION, descripcion);  //textoDescripcion.getText().toString()

        long result = dbW.insert(table_TIPO_MAQUINA, null, values);

        return result;
    }

    public void updateTipoMaquina(long id, String descripcion) {
        // Modifiquem els valors de las tasca amb clau primària "id"
        ContentValues values = new ContentValues();
        values.put(TIPO_MAQUINA_DESCRIPCION, descripcion);

        dbW.update(table_TIPO_MAQUINA,values, TIPO_MAQUINA_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Cursor viewDataTipoMaquina(){
        //String query = "Select Codigo, Descripcion, Estoc from "+ TABLE_NAME;
        //Cursor cursor = db.rawQuery(query, null);
        //return cursor;
        return dbR.query(table_TIPO_MAQUINA, new String[]{TIPO_MAQUINA_ID,TIPO_MAQUINA_DESCRIPCION},
                null, null,
                null, null, TIPO_MAQUINA_DESCRIPCION);

    }

    public Cursor taskTipoMaquina(long id) {
        // Retorna un cursor només amb el id indicat
        // Retornem les tasques que el camp DONE = 1
        return dbR.query(table_TIPO_MAQUINA, new String[]{TIPO_MAQUINA_ID,TIPO_MAQUINA_DESCRIPCION},
                TIPO_MAQUINA_ID+ "=?", new String[]{String.valueOf(id)},
                null, null, null);

    }

    public void deleteMaquinas(long id){
        String[] args = new String[] {String.valueOf(id)};
        dbW.delete(table_MAQUINAS,MAQUINAS_ID + " = ?", args);

    }

    public void deleteZonas(long id){
        String[] args = new String[] {String.valueOf(id)};
        dbW.delete(table_ZONAS,ZONAS_ID + " = ?", args);

    }

    public void deleteTipoMaquina(long id){
        String[] args = new String[] {String.valueOf(id)};
        dbW.delete(table_TIPO_MAQUINA,TIPO_MAQUINA_ID + " = ?", args);

    }

    // DESTRUCTOR
    protected void finalize () {
        // Cerramos los databases
        dbW.close();
        dbR.close();
    }

    private void open() {
        dbW = dbHelper.getWritableDatabase();
        dbR = dbHelper.getReadableDatabase();
    }
}
