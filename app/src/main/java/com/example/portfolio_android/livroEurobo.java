package com.example.portfolio_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class livroEurobo extends AppCompatActivity implements View.OnClickListener{

    String ra, nome, email, senha;
    int numeroDePedidos;

    Button reservar;
    private static final String euRoboIsbn = "00001";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_eurobo);

        Intent intent = getIntent();
        Bundle parametros = intent.getExtras();
        ra = parametros.getString("ra");
        nome = parametros.getString("nome");
        email = parametros.getString("email");
        senha = parametros.getString("senha");
        numeroDePedidos = parametros.getInt("numeroDePedidos");

        reservar = (Button) findViewById(R.id.btReservar1);

        reservar.setOnClickListener(this);
    }

    public void onClick(View view){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        String pedidoData = sdf.format(cal.getTime());

        cal.add(Calendar.DATE, + 14);
        String entregaData = sdf.format(cal.getTime());

        boolean resultado = false;
        int idExemplar;

        BancoController bd =  new BancoController(getBaseContext());

        Cursor dados = bd.consultarDisponibilidadeExemplar(euRoboIsbn);

        if (dados.moveToFirst()){
            idExemplar = dados.getInt(0);
            resultado = bd.inserirPedido(idExemplar, ra, entregaData, pedidoData);
        }
        else {
            showToast("Não há exemplares disponíveis");
        }

        if (resultado)
            showToast("Pedido: " + pedidoData + "\nEntrega: " + entregaData);
        else
            showToast("Erro ao inserir pedido");
    }

    public void showToast(String msg){
        Context context = getApplicationContext();
        int duracao = Toast.LENGTH_LONG;
        Toast.makeText(context,msg, duracao).show();
    }
}