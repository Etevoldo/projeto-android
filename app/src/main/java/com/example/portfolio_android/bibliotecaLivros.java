package com.example.portfolio_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class bibliotecaLivros extends AppCompatActivity implements View.OnClickListener {
    String ra, nome, email, senha , msg;
    int numeroDePedidos;
    ImageView btImg1, btImg2, btImg3;
    Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca_livros);

        Intent intent = getIntent();
        Bundle parametros = intent.getExtras();
        ra = parametros.getString("ra");
        nome = parametros.getString("nome");
        email = parametros.getString("email");
        senha = parametros.getString("senha");
        numeroDePedidos = parametros.getInt("numeroDePedidos");

        btImg1 = (ImageView) findViewById(R.id.imgEurobo);
        btImg2 = (ImageView) findViewById(R.id.imgOdisseia);
        btImg3 = (ImageView) findViewById(R.id.imgUmlugar);
        voltar = (Button) findViewById(R.id.btVoltar);

        btImg1.setOnClickListener(this);
        btImg2.setOnClickListener(this);
        btImg3.setOnClickListener(this);
        voltar.setOnClickListener(this);
    }

    public void onClick(View view){
        Bundle parameters = new Bundle();

        parameters.putString("ra", ra);
        parameters.putString("nome", nome);
        parameters.putString("email", email);
        parameters.putString("senha", senha);
        parameters.putInt("numeroDePedidos", numeroDePedidos);

        if (view.getId() == R.id.imgEurobo){
            Intent intent = new Intent(this, livroEurobo.class);
            intent.putExtras(parameters);
            startActivity(intent);

        }
        else if (view.getId() == R.id.imgOdisseia) {
            Intent intent = new Intent(this, livroOdisseia.class);
            intent.putExtras(parameters);
            startActivity(intent);
        }
        else if (view.getId() == R.id.imgUmlugar) {
            Intent intent = new Intent(this, livroUmLugar.class);
            intent.putExtras(parameters);
            startActivity(intent);
        }
        else if (view.getId() == R.id.btVoltar) {
            Intent intent = new Intent(this, bibliotecaLivros.class);
            intent.putExtras(parameters);
            startActivity(intent);
        }
        else {
            msg = "Erro";
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
        }
    }
}