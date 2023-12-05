package com.example.portfolio_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class exemplarListaAdapter extends ArrayAdapter<exemplar>{
    private Context context;
    private List<exemplar> listaExemplares = null;
    public exemplarListaAdapter(Context context, List<exemplar> listaExemplares) {
            super(context, 0, listaExemplares);
            this.listaExemplares = listaExemplares;
            this.context = context;
    }
    public View getView(int position, View view, ViewGroup parent) {
        exemplar exemplarItem = listaExemplares.get(position);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.itens_exemplares, null);

        TextView textViewNumero = (TextView) view.findViewById(R.id.txtIdExemplarExemplares);
        Button btnPedirExemplar = (Button) view.findViewById(R.id.btnPedirExemplar);

        textViewNumero.setText(String.valueOf(exemplarItem.getIdExemplar()));

        if (exemplarItem.isDisponivel())
            btnPedirExemplar.setText("Pedir");
        else
            btnPedirExemplar.setText("Alugado");

        return view;
    }
}

