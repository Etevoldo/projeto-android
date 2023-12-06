package com.example.portfolio_android;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FuncoesAjuda {

    public static void fazerPedido(String isbn, String ra, Context context ){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        String pedidoData = sdf.format(cal.getTime());

        cal.add(Calendar.DATE, +14);
        String entregaData = sdf.format(cal.getTime());

        boolean resultado = true;
        int idExemplar;

        BancoController bd = new BancoController(context);

        Cursor dados = bd.consultarDisponibilidadeExemplar(isbn);

        if (dados.moveToFirst()) {
            idExemplar = dados.getInt(0);
            resultado = bd.inserirPedido(idExemplar, ra, entregaData, pedidoData);
        } else {
            showToast("Não há exemplares disponíveis!", context);
            return;
        }

        if (resultado)
            showToast("Pedido: " + pedidoData + "\nEntrega: " + entregaData
                     + "\nFeito com sucesso", context);
        else
            showToast("Erro ao inserir pedido", context);
    }

    public static void showToast(String msg, Context context){
        int duracao = Toast.LENGTH_LONG;
        Toast.makeText(context,msg, duracao).show();
    }
}
