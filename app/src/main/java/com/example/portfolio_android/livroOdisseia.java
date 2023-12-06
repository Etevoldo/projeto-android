package com.example.portfolio_android;

import static com.example.portfolio_android.FuncoesAjuda.fazerPedido;

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

public class livroOdisseia extends AppCompatActivity implements View.OnClickListener{

    String ra, nome, email, senha;
    int numeroDePedidos;
    private static final String umaOdisseiaIsbn = "00002";
    Button reservar, voltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_odisseia);

        Intent intent = getIntent();
        Bundle parametros = intent.getExtras();
        ra = parametros.getString("ra");
        nome = parametros.getString("nome");
        email = parametros.getString("email");
        senha = parametros.getString("senha");
        numeroDePedidos = parametros.getInt("numeroDePedidos");


        reservar = (Button) findViewById(R.id.btReservarOdisseia);
        voltar = (Button) findViewById(R.id.btVoltarBiblioteca2);

        reservar.setOnClickListener(this);
    }

    public void onClick(View view){
        Bundle parameters = new Bundle();

        parameters.putString("ra", ra);
        parameters.putString("nome", nome);
        parameters.putString("email", email);
        parameters.putString("senha", senha);
        parameters.putInt("numeroDePedidos", numeroDePedidos);

        if (view.getId() == R.id.btVoltarBiblioteca2){
            Intent intent = new Intent(this, bibliotecaLivros.class);
            intent.putExtras(parameters);
            startActivity(intent);
        }
        else {
            fazerPedido(umaOdisseiaIsbn, ra, getBaseContext());
        }
    }
}