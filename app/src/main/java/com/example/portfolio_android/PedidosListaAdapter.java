package com.example.portfolio_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PedidosListaAdapter extends ArrayAdapter<pedido> {
    private Context context;
    private List<pedido> listaPedidos = null;

    PedidosListaAdapter(Context context, List<pedido> listaPedidos){
        super(context, 0, listaPedidos);
        this.context = context;
        this.listaPedidos = listaPedidos;
    }
    public View getView(int position, View view, ViewGroup parent){
        pedido exemplarItem = listaPedidos.get(position);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.itens_pedido, null);

        TextView txtPedidosExemplar = (TextView) view.findViewById(R.id.txtExemplarPedido);
        TextView txtDataPedido = (TextView) view.findViewById(R.id.txtDataPedido);
        TextView txtEntregaPedido = (TextView) view.findViewById(R.id.txtEntregaPedido);
        TextView txtRaPedido = (TextView) view.findViewById(R.id.txtRaPedido);

        txtPedidosExemplar.setText(String.valueOf(exemplarItem.getIdExemplar()));
        txtDataPedido.setText(exemplarItem.getDataPedido());
        txtEntregaPedido.setText(exemplarItem.getDataEntrega());
        txtRaPedido.setText(exemplarItem.getRa());

        return view;
    }
}
