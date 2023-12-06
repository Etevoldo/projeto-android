package com.example.portfolio_android;

import static com.example.portfolio_android.FuncoesAjuda.showToast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class pedidosAdm extends AppCompatActivity implements View.OnClickListener {

    String ra, nome, email, senha;
    int numeroDePedidos;
    EditText edtEntregaConfirma;
    Button btnConfEntrega;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_adm);

        Intent intent = getIntent();
        Bundle parametros = intent.getExtras();
        ra = parametros.getString("ra");
        nome = parametros.getString("nome");
        email = parametros.getString("email");
        senha = parametros.getString("senha");
        numeroDePedidos = parametros.getInt("numeroDePedidos");

        edtEntregaConfirma = (EditText) findViewById(R.id.edtEntregaConfirma);
        btnConfEntrega = (Button) findViewById(R.id.btnConfEntrega);
        lista = (ListView) findViewById(R.id.listPedidos);

        btnConfEntrega.setOnClickListener(this);

        List<pedido> listaPedidos;
        listaPedidos = consultarPedidos();

        PedidosListaAdapter adaptador = new PedidosListaAdapter(this, listaPedidos);
        lista.setAdapter(adaptador);

    }

    public List<pedido> consultarPedidos(){
        List<pedido> lista= new LinkedList<pedido>();
        BancoController bd = new BancoController(getBaseContext());
        Cursor dados = bd.consultarTodosPedido();

        if (dados != null) {
            do {
                pedido item = new pedido();
                item.setIdPedido(dados.getInt(0));
                item.setIdExemplar(dados.getInt(1));
                item.setRa(dados.getString(2));
                item.setDataEntrega(dados.getString(3));
                item.setDataPedido(dados.getString(4));
                lista.add(item);
            } while (dados.moveToNext()); //retorna 0 quando acaba
        }
        else {
            showToast("Não há nenhum exemplar cadastrado", getBaseContext());
        }
        return lista;
    }

    public void onClick(View view){

        int idExemplar = Integer.parseInt(edtEntregaConfirma.getText().toString());

        BancoController bd = new BancoController(getBaseContext());

        Cursor data = bd.carregaPedidosPorIdExemplar(idExemplar);

        if (data.moveToFirst()){
            bd.encerrarPedidosPorId(idExemplar); // perigo: encerra todos!
            bd.resetarDisponibilidade(idExemplar);
            showToast("Pedido encerrado com Sucesso!", getBaseContext());
        }
        else {
            showToast("Não encontrado nenhum pedido com esse exemplar", getBaseContext());
        }
    }
}