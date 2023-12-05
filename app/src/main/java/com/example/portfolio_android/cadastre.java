package com.example.portfolio_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cadastre extends AppCompatActivity implements View.OnClickListener {
    EditText txtNome, txtEmail, txtSenha, txtConfSenha, txtRA;
    Button btGravarUsuario;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastre);

        txtNome  = (EditText) findViewById(R.id.txtNome);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtConfSenha=(EditText) findViewById(R.id.txtConfSenha);
        txtRA =(EditText) findViewById(R.id.txtRa);
        btGravarUsuario=(Button) findViewById(R.id.btCadastrar);

        btGravarUsuario.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String nome = txtNome.getText().toString();
        String email= txtEmail.getText().toString();
        String senha= txtSenha.getText().toString();
        String confsenha=txtConfSenha.getText().toString();
        String ra = txtRA.getText().toString();
        String msg;
        if (!senha.equals(confsenha)){
            msg = "As Senhas não conferem, digite outra!";
            Toast.makeText(getApplicationContext(), msg , Toast.LENGTH_LONG).show();
        }else{
            if (nome.length()==0 || email.length()<10 || ra.length()==0)
            {
                msg = "Atenção - Os campos Nome, E-mail e RA devem ser preenchidos!!!";
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
            }else {
                // gravar os dados
                BancoController bd = new BancoController(getBaseContext());
                String resultado;

                resultado = bd.insereDadosUsuario(nome, email, senha, ra);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                txtNome.setText("");
                txtEmail.setText("");
                txtRA.setText("");
                txtSenha.setText("");
                txtConfSenha.setText("");
            }
        }
    }
}
