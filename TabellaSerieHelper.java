package com.example.lucasosti.database_serie_tv;

/**
 * Created by LucaSosti on 20/11/2017.
 */

//Librerie
import java.text.MessageFormat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TabellaSerieHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME ="serietv.db";
    private static final int SCHEMA_VERSION = 1;

    public TabellaSerieHelper(Context context){
        super(context, DATABASE_NAME, null,SCHEMA_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    String sql = "CREATE TABLE {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} TEXT);";
        db.execSQL(MessageFormat.format(sql, TabellaSerie.TABLE_NAME, TabellaSerie.CODICE_SERIE, TabellaSerie.NOME_SERIE));
    }

    //Metodi di inserimento di una serie e relativo metodo con query
    public void inserisci(String nome){
        SQLiteDatabase db = getReadableDatabase();
        inserisciSerie (db, nome);
    }
    //query
    private void inserisciSerie(SQLiteDatabase db, String nome) {
        //ContentValues v = new ContentValues();
        //v.put(TabellaSerie.NOME_SERIE, NOME);
        //db.insert(TabellaSerie.TABLE_NAME, null, v);

        //query messa dentro una stringa
        String s = "insert into serietv (nome_serie) values ('" + nome + "')";
        //esecuzione query contenuta in nell'oggetto s nel bd
        db.execSQL(s);

    }

    public Cursor getSerie(){
        String s = "select cod_serie, nome_serie from serietv order by nome_serie";
        return (getReadableDatabase().rawQuery(s,null));
    }

    //Metodi di eliminazione di una serie e relativo metodo con query
    public void elimina (int cod){
        SQLiteDatabase db = getReadableDatabase();
        elimina_cod(db, cod);
    }
    //query
    private void elimina_cod(SQLiteDatabase db, int cod) {
        String s = "delete from serietv where cod_serie="+ cod;
        db.execSQL(s);
    }

    //Metodi di modificazione di una serie e relativo metodo con query
    public void modifica(int cod, String nome){
        SQLiteDatabase db = getReadableDatabase();
        modifica_cod(db, cod, nome);
    }
    //query
    private void modifica_cod(SQLiteDatabase db, int cod, String nome) {
        String s = "update serietv set nome_serie='" + nome + "' where cod_serie=" + cod;
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}
