package com.example.portfolio_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class menu_activities extends AppCompatActivity implements View.OnClickListener {

    String ra, nome, email, senha , msg;
    int numeroDePedidos;
    private static final String admRA = "321";
    TextView txtnomeUsuarioMenu;
    Button btBiblioteca, btEscanear, btMeusLivros, btLogOut, btSuporte;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activities);

        Intent intent = getIntent();
        Bundle parametros = intent.getExtras();
        ra = parametros.getString("ra");
        nome = parametros.getString("nome");
        email = parametros.getString("email");
        senha = parametros.getString("senha");
        numeroDePedidos = parametros.getInt("numeroDePedidos");

        btBiblioteca = (Button) findViewById(R.id.btBiblioteca);
        btEscanear = (Button) findViewById(R.id.btEscanear);
        btMeusLivros = (Button) findViewById(R.id.btMeusLivros);
        btLogOut = (Button) findViewById(R.id.btLogOut);
        btSuporte = (Button) findViewById(R.id.btSuporte);
        txtnomeUsuarioMenu = (TextView) findViewById(R.id.txtNomeUsuarioMenu);

        if (ra.equals(admRA)){
            btMeusLivros.setText("Pedidos");
            txtnomeUsuarioMenu.setText("Administrador");
        }


        btBiblioteca.setOnClickListener(this);
        btEscanear.setOnClickListener(this);
        btMeusLivros.setOnClickListener(this);
        btLogOut.setOnClickListener(this);
        btSuporte.setOnClickListener(this);
        txtnomeUsuarioMenu.setText(nome);
    }

    public void onClick(View view){
        Bundle parameters = new Bundle();

        parameters.putString("ra", ra);
        parameters.putString("nome", nome);
        parameters.putString("email", email);
        parameters.putString("senha", senha);
        parameters.putInt("numeroDePedidos", numeroDePedidos);

        if (view.getId() == R.id.btBiblioteca){
            Intent intent = new Intent(this, bibliotecaLivros.class);
            intent.putExtras(parameters);
            startActivity(intent);

        }
        else if (view.getId() == R.id.btEscanear) {
            Intent intent = new Intent(this, escanear.class);
            intent.putExtras(parameters);
            startActivity(intent);
        }
        else if (view.getId() == R.id.btMeusLivros) {
            Intent intent;
            if (ra.equals(admRA))
                intent = new Intent(this, pedidosAdm.class);
            else
                intent = new Intent(this, meusLivros.class);
            intent.putExtras(parameters);
            startActivity(intent);
        }
        else if (view.getId() == R.id.btLogOut) { // temporario
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
            /*
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(parameters);
            startActivity(intent);
             */

            BancoController bd =  new BancoController(getBaseContext());
            bd.resetarDisponibilidade(1);
            bd.resetarDisponibilidade(3);
            bd.resetarDisponibilidade(2);
        }
        else if (view.getId() == R.id.btSuporte) {
            Intent intent = new Intent(this, suporte.class);
            intent.putExtras(parameters);
            startActivity(intent);
        }
        else {
            msg = "Erro";
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
        }

    }
}