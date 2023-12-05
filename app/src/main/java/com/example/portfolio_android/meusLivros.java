package com.example.portfolio_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class meusLivros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_livros);


        BancoController bd = new BancoController(getBaseContext());

        Cursor dados = bd.carregaDadosExemplar("00001");
        if (!dados.moveToFirst()) {
            bd.insereLivro("00001", "Eu Robô", "Isaac Assimov");
            bd.insereLivro("00002", "2001", "Arthur C. Clarke");
            bd.insereLivro("00003", "Um lugar longe daqui", "Della Owes");
            bd.insereExemplar("00001", "10/10/10000", true);
            bd.insereExemplar("00001", "11/10/10000", true);
            bd.insereExemplar("00001", "11/10/10000", true);
            bd.insereExemplar("00002", "15/10/10000", true);
            bd.insereExemplar("00003", "15/10/10000", true);
            Toast.makeText(getApplicationContext(), "novos livros carregados", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "dados ja carregados",
                    Toast.LENGTH_LONG).show();
        }
    }
}