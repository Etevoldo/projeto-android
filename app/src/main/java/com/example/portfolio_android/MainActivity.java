package com.example.portfolio_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner SpCores = (Spinner) findViewById(R.id.nome_do_Spinner);

        //String[] opcoes = new String[] {"Manha 1", "Manha 2", "Noite 1", "Noite 2"};
        String[] opcoes = getResources().getStringArray(R.array.cores);

        ArrayAdapter<String> aad = new
                ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, opcoes);
        Spinner selecioneHorario = (Spinner) findViewById(R.id.nome_do_Spinner);
        SpCores.setAdapter(aad);
    }
}