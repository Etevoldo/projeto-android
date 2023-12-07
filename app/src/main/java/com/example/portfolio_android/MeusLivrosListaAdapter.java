package com.example.portfolio_android;

import static com.example.portfolio_android.FuncoesAjuda.fetchCover;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

public class MeusLivrosListaAdapter extends ArrayAdapter<LivroEPedidos> {
    private Context context;
    private List<LivroEPedidos> listaLivroEPedidos = null;

    MeusLivrosListaAdapter(Context context, List<LivroEPedidos> listaLivroEPedidos){
        super(context, 0, listaLivroEPedidos);
        this.context = context;
        this.listaLivroEPedidos = listaLivroEPedidos;
    }
    public View getView(int position, View view, ViewGroup parent){
        LivroEPedidos livroEPedidosItem = listaLivroEPedidos.get(position);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_meus_livros, null);

        TextView txtTitulo = (TextView) view.findViewById(R.id.txtTituloIML);
        TextView txtDataPedido = (TextView) view.findViewById(R.id.txtDataPedidoIML);
        TextView txtEntregaPedido = (TextView) view.findViewById(R.id.txtDataEntregaIML);
        ImageView imgIcone = (ImageView) view.findViewById(R.id.imgIconeMeusLivrosIML);

        txtTitulo.setText(livroEPedidosItem.getTitulo());
        txtDataPedido.setText(livroEPedidosItem.getDataPedido());
        txtEntregaPedido.setText(livroEPedidosItem.getDataEntrega());

        int redID = fetchCover(livroEPedidosItem.getIsbn());
        imgIcone.setImageResource(redID);


        return view;
    }
}
