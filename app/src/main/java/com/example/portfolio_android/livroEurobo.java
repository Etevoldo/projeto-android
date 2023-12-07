package com.example.portfolio_android;

import static com.example.portfolio_android.FuncoesAjuda.fazerPedido;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    Button reservar, voltar;
    private static final String euRoboIsbn = "00001";
    @SuppressLint("MissingInflatedId")
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

        reservar = (Button) findViewById(R.id.btReservarlivro);
        voltar = (Button) findViewById(R.id.btVoltarBiblioteca);

        reservar.setOnClickListener(this);
        voltar.setOnClickListener(this);
    }

    public void onClick(View view){
        Bundle parameters = new Bundle();

        parameters.putString("ra", ra);
        parameters.putString("nome", nome);
        parameters.putString("email", email);
        parameters.putString("senha", senha);
        parameters.putInt("numeroDePedidos", numeroDePedidos);

        if (view.getId() == R.id.btVoltarBiblioteca){
            Intent intent = new Intent(this, bibliotecaLivros.class);
            intent.putExtras(parameters);
            startActivity(intent);
        }
        else if (view.getId() == R.id.btReservarlivro){
            fazerPedido(euRoboIsbn, ra, getBaseContext());
        }
    }
}