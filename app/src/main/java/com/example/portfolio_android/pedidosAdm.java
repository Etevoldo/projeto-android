package com.example.portfolio_android;

import static com.example.portfolio_android.FuncoesAjuda.showToast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

public class pedidosAdm extends AppCompatActivity implements View.OnClickListener {

    String ra, nome, email, senha;
    int numeroDePedidos;
    EditText edtEntregaConfirma;
    Button btnConfEntrega;
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

        btnConfEntrega.setOnClickListener(this);
    }

    public void onClick(View view){
        showToast("something lol", getBaseContext());
    }
}