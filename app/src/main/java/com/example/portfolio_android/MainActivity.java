package com.example.portfolio_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin, btnCadastre;
    EditText strLogin, strSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCadastre = (Button) findViewById(R.id.btnCadastre);
        strLogin = (EditText) findViewById(R.id.lblLogin);
        strSenha = (EditText) findViewById(R.id.lblSenha);

        btnLogin.setOnClickListener(this);
        btnCadastre.setOnClickListener(this);

    }
    public void onClick(View view) {
        if (view.getId() == R.id.btnCadastre) {
            Intent intent = new Intent (this, cadastre.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.btnLogin) {
            String login = strLogin.getText().toString();
            String senha = strSenha.getText().toString();

            BancoController bd = new BancoController(getBaseContext());

            Cursor data = bd.carregaDadosParaLogin(login, senha);

            if (data.moveToFirst()) {
                //ja temos ra,            (id 0);
                String nome = data.getString(1);
                String email = data.getString(2);
                //ja temos senha,            (id 3);
                int numeroDePedidos = data.getInt(4);

                Intent intent = new Intent(this, menu_activities.class);
                Bundle parameters = new Bundle();

                parameters.putString("ra", login);
                parameters.putString("nome", nome);
                parameters.putString("email", email);
                parameters.putString("senha", senha);
                parameters.putInt("numeroDePedidos", numeroDePedidos);
                intent.putExtras(parameters);
                String msg = "65 mainActivity";
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "RA e/ou senha incorretos"
                        , Toast.LENGTH_LONG).show();
            }
        }
    }

}