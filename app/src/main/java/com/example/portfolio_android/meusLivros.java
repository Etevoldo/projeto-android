package com.example.portfolio_android;

import static com.example.portfolio_android.FuncoesAjuda.showToast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class meusLivros extends AppCompatActivity {
    String ra, nome, email, senha;
    int numeroDePedidos;

    TextView txtnome;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_livros);

        Intent intent = getIntent();
        Bundle parametros = intent.getExtras();
        ra = parametros.getString("ra");
        nome = parametros.getString("nome");
        email = parametros.getString("email");
        senha = parametros.getString("senha");
        numeroDePedidos = parametros.getInt("numeroDePedidos");

        listView = (ListView) findViewById(R.id.listMeusLivros);
        txtnome = (TextView) findViewById(R.id.txtNomeMeusLivros);

        txtnome.setText(nome);

        List<LivroEPedidos> listaLivroPedidos = consultarMeusLivros();
        MeusLivrosListaAdapter adaptador =
                new MeusLivrosListaAdapter(this, listaLivroPedidos);

        listView.setAdapter(adaptador);
    }

    public List<LivroEPedidos> consultarMeusLivros(){
        List<LivroEPedidos> lista= new LinkedList<LivroEPedidos>();
        BancoController bd = new BancoController(getBaseContext());
        Cursor dadosPedido = bd.consultarPedidosRa(ra);

        if (dadosPedido.moveToFirst()) {
                do {
                    LivroEPedidos item = new LivroEPedidos();
                    consultarLivroPorIdExemplar(dadosPedido.getInt(1), bd, item);
                    item.setIdPedido(dadosPedido.getInt(0));
                    item.setIdExemplar(dadosPedido.getInt(1));
                    item.setRa(dadosPedido.getString(2));
                    item.setDataEntrega(dadosPedido.getString(3));
                    item.setDataPedido(dadosPedido.getString(4));
                    lista.add(item);
                } while (dadosPedido.moveToNext()); //retorna 0 quando acaba
        }
        else {
            showToast("Você não pegou nenhum livro", getBaseContext());
        }
        return lista;
    }

    public void consultarLivroPorIdExemplar(int idExemplar, BancoController bd, LivroEPedidos item){
        Cursor dadosExemplar = bd.consultarExemplar(idExemplar);
        if (dadosExemplar.moveToFirst()){
            Cursor dadosLivro = bd.consultarLivroPorIsbn(dadosExemplar.getString(1));
            if (dadosLivro.moveToFirst()){
                item.setIsbn(dadosLivro.getString(0));
                item.setTitulo(dadosLivro.getString(1));
            }
        }
    }
}