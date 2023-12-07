package com.example.portfolio_android;

import static com.example.portfolio_android.FuncoesAjuda.fazerPedido;
import static com.example.portfolio_android.FuncoesAjuda.showToast;

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
public class livroUmLugar extends AppCompatActivity implements View.OnClickListener{
    String ra, nome, email, senha;
    int numeroDePedidos;
    Button reservar, voltar;

    private static final String umLugarIsbn = "00003";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_um_lugar);

        Intent intent = getIntent();
        Bundle parametros = intent.getExtras();
        ra = parametros.getString("ra");
        nome = parametros.getString("nome");
        email = parametros.getString("email");
        senha = parametros.getString("senha");
        numeroDePedidos = parametros.getInt("numeroDePedidos");


        reservar = (Button) findViewById(R.id.btReservarUmlugar);
        voltar = (Button) findViewById(R.id.btVoltarBiblioteca3);

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

        if (view.getId() == R.id.btVoltarBiblioteca3){
            Intent intent = new Intent(this, bibliotecaLivros.class);
            intent.putExtras(parameters);
            startActivity(intent);
        }
        else if (view.getId() == R.id.btReservarUmlugar){
            fazerPedido(umLugarIsbn, ra, getBaseContext());
        }
    }
}