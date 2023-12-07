package com.example.portfolio_android;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FuncoesAjuda {

    private static final int semana = 7;

    public static void fazerPedido(String isbn, String ra, Context context ){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        String pedidoData = sdf.format(cal.getTime());

        cal.add(Calendar.DATE, + semana * 2);
        String entregaData = sdf.format(cal.getTime());

        boolean resultInserir, resultAlterar;
        int idExemplar;

        BancoController bd = new BancoController(context);

        Cursor dados = bd.consultarDisponibilidadeExemplar(isbn);
        if (dados.moveToFirst()) {
            idExemplar = dados.getInt(0);
            resultInserir = bd.inserirPedido(idExemplar, ra, entregaData, pedidoData);
        } else {
            showToast("Não há exemplares disponíveis!", context);
            return;
        }

        Cursor dadosAluno = bd.consultarDadosAluno(ra);
        if (dados.moveToFirst()){
            resultAlterar = bd.alterarNumeroPedidos(ra, (dadosAluno.getInt(4) + 1));
        }
        else {
            showToast("Erro ao encontrar aluno", context);
            return;
        }

        if (resultInserir && resultAlterar)
            showToast("Pedido: " + pedidoData + "\nEntrega: " + entregaData
                     + "\nFeito com sucesso", context);
        else
            showToast("Erro ao inserir pedido", context);
    }

    public static int fetchCover(String isbn) {
        String name;
        switch (isbn) {
            case "00001": return R.drawable.eurobocapa;
            case "00002": return R.drawable.umaodisseia;
            case "00003": return R.drawable.umlugar;
            default: return R.drawable.ebiblio;
        }
    }

    public static void showToast(String msg, Context context){
        int duracao = Toast.LENGTH_LONG;
        Toast.makeText(context,msg, duracao).show();
    }
}
