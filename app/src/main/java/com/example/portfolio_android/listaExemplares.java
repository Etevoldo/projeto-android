package com.example.portfolio_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class listaExemplares extends AppCompatActivity {
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_exemplares);

        List<exemplar> listaDeExemplares;
        listaDeExemplares = consultarTodos();

        exemplarListaAdapter adaptador = new exemplarListaAdapter(this, listaDeExemplares);

        lista = (ListView) findViewById(R.id.listExemplares);
        lista.setAdapter(adaptador);

    }

    public List<exemplar> consultarTodos() {
        List<exemplar> lista = new LinkedList<exemplar>();
        BancoController bd = new BancoController(getBaseContext());
        Cursor dados = bd.consultarTodosExemplar();

        if (dados != null) {
            do {
                exemplar item = new exemplar();
                item.setIdExemplar(dados.getInt(0));
                item.setIsbn(dados.getString(1));
                item.setDataRecebida(dados.getString(2));
                item.setDisponivel("1".equals(dados.getString(3)));
                lista.add(item);
            } while (dados.moveToNext()); //retorna 0 quando acaba
        }
        else {
            String msg = "Não há nenhum exemplar cadastrado";
            mensagem(msg);
        }
        return lista;
    }

    public void mensagem(String msg) {
        Context contexto = getApplicationContext();
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(contexto, msg, duracao);
        toast.show();
    }
}