package com.example.lucasosti.database_serie_tv;

import com.example.lucasosti.database_serie_tv.TabellaSerieHelper;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;

public class database extends AppCompatActivity {

    //variabili glbali
    int  codice_serie;
    int cliccato;
    String nome=new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        //al lancio dell'app carico la lista con i record della tabella
        CaricaLista();
    }

    public void CaricaLista(){
        TabellaSerieHelper DH = new TabellaSerieHelper(this);
        Cursor c = DH.getSerie();
        int tot = c.getCount();

        final ArrayList<String> lista = new ArrayList<String>();
        final int[] codici = new int[tot];
        int i = 0;
        try {
            while (c.moveToNext()) {
                lista.add(c.getString(1));
                codici[i]=c.getInt(0);
                i++;
            }
        }
        finally {
            c.close();
        }
        final ListView LV = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lista);
        LV.setAdapter(adapter);
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int indice = position;
                String Valore = (String) LV.getItemAtPosition(position);
                TextView N = (TextView) findViewById(R.id.txtNome);
                N.setText(lista.get(indice));
                nome=lista.get(indice);
                codice_serie=codici[indice];
            }
        });

    }

    public void elimina(View v){
        TabellaSerieHelper DH = new TabellaSerieHelper(this);
        DH.elimina(codice_serie);
        TextView N = (TextView) findViewById(R.id.txtNome);
        N.setText("");
        CaricaLista();
    }

    public void setButton(int a) {
        Button B1 = (Button) findViewById(R.id.btnNew);
        Button B2 = (Button) findViewById(R.id.btnDelete);
        Button B3 = (Button) findViewById(R.id.btnUpdate);
        Button B4 = (Button) findViewById(R.id.btnSalva);
        Button B5 = (Button) findViewById(R.id.btnCancella);
        TextView T1 = (TextView) findViewById(R.id.txtNome);
        EditText T2 = (EditText) findViewById(R.id.txtEdit);

        if (a == 1) {
            B1.setVisibility(View.INVISIBLE);
            B2.setVisibility(View.INVISIBLE);
            B3.setVisibility(View.INVISIBLE);
            B4.setVisibility(View.VISIBLE);
            B5.setVisibility(View.VISIBLE);
            T1.setVisibility(View.INVISIBLE);
            T2.setVisibility(View.VISIBLE);
            if (cliccato == 1) {
                T2.setText("Inserisci una Serie Tv");
            } else {
                T2.setText(nome);
            }

            if (a == 2) {
                B1.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B3.setVisibility(View.VISIBLE);
                B4.setVisibility(View.INVISIBLE);
                B5.setVisibility(View.INVISIBLE);
                T1.setVisibility(View.VISIBLE);
                T2.setVisibility(View.INVISIBLE);
                T1.setText("");
            }
        }
    }
    public void nuovo(View v){
        cliccato = 1;
        setButton(1);
        }

    public void aggiorna(View v){
        cliccato = 2;
        setButton(1);
    }

    public void cancella(View v){
        setButton(2);
        CaricaLista();
    }

    public void btnSalva(View v){
        TabellaSerieHelper DH = new TabellaSerieHelper(this);
        EditText T = (EditText) findViewById(R.id.txtEdit);
        if(cliccato==1){
            DH.inserisci(T.getText().toString());
        }else{
            DH.modifica(codice_serie, T.getText().toString());
            T.setText("");
            setButton(2);
            CaricaLista();
        }
    }

}
