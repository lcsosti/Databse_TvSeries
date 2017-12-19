package com.example.lucasosti.database_serie_tv;

/**
 * Created by LucaSosti on 20/11/2017.
 */

//Librerie
import android.provider.BaseColumns;
//Codice
public interface TabellaSerie extends BaseColumns {
    //Struttura Tabella Database
    String TABLE_NAME = "serietv";
    String CODICE_SERIE = "cod_serie";
    String NOME_SERIE = "nome_serie";
    String[] COLUMNS = new String[]
            {CODICE_SERIE, NOME_SERIE};

}
