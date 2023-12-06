package com.example.portfolio_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
   private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public String inserePedido(int idPedido, int idExemplar, String ra,
                               String dataEntrega, String dataPedido) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("idPedido", idPedido);
        valores.put("idExemplar", idExemplar);
        valores.put("ra", ra);
        valores.put("dataEntrega", dataEntrega);
        valores.put("dataPedido", dataPedido);

        resultado = db.insert("pedido", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public String insereDadosUsuario(String nome, String email, String senha, String ra) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);
        valores.put("senha", senha);
        valores.put("ra", ra);
        valores.put("numeroDePedidos", 0);

        resultado = db.insert("pessoa", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public String insereExemplar(String isbn, String dataRecebida, Boolean disponivel) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("isbn", isbn);
        valores.put("dataRecebida", dataRecebida);
        valores.put("disponivel", disponivel);

        resultado = db.insert("exemplar", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir Exemplares";
        else
            return "Registro Inserido com sucesso";
    }
    public String insereLivro(String isbn, String titulo, String autor) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("isbn", isbn);
        valores.put("titulo", titulo);
        valores.put("autor", autor);

        resultado = db.insert("livro", null, valores);
        db.close();

        if (resultado == -1)
            return "Livros ja inserido";
        else
            return "Registro Inserido com sucesso";
    }

    public Cursor carregaDadosParaLogin(String ra, String senha) {
        Cursor cursor;
        String[] campos = { "ra", "nome", "email", "senha", "numeroDePedidos"};
        String filtro = "ra='" + ra + "' and senha='" + senha + "'";

        db = banco.getReadableDatabase();

        cursor = db.query("pessoa", campos, filtro,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
    public Cursor consultarTodosExemplar() {
        Cursor cursor;
        String[] campos = { "idExemplar", "isbn", "dataRecebida", "disponivel"};

        db = banco.getReadableDatabase();

        cursor = db.query("exemplar", campos, null,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
    public Cursor consultarTodosPedido() {
        Cursor cursor;
        String[] campos = { "idPedido", "idExemplar", "ra", "dataEntrega", "dataPedido"};

        db = banco.getReadableDatabase();

        cursor = db.query("pedido", campos, null,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor consultarExemplar(int idExemplar) {
        Cursor cursor;
        String[] campos = { "idExemplar", "isbn", "dataRecebida", "disponivel"};
        String filtro = "idExemplar='" + idExemplar + "'";

        db = banco.getReadableDatabase();

        cursor = db.query("exemplar", campos, filtro,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
    public boolean inserirPedido(int idExemplar, String ra, String dataEntrega, String dataPedido){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        // etapa 1: inserir o pedido

        valores = new ContentValues();
        valores.put("idExemplar", idExemplar);
        valores.put("ra", ra);
        valores.put("dataEntrega", dataEntrega);
        valores.put("dataPedido", dataPedido);

        resultado = db.insert("pedido", null, valores);

        // etapa 2: trocar o boolean de disponibilidade
        Cursor cursor;
        String[] campos = { "idExemplar", "isbn", "dataRecebida", "disponivel"};
        String filtro = "idExemplar='" + idExemplar + "'";

        cursor = db.query("exemplar", campos, filtro,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        ContentValues valores2 = new ContentValues() ;
        if ("1".equals(cursor.getString(3)))
            valores2.put("disponivel" , "0" );
        else
            valores2.put("disponivel" , "1" );

        int linha;
        String condicao = "idExemplar = " + idExemplar;
        linha = db.update("exemplar", valores2, condicao, null);

        db.close();
        if (resultado == -1 || linha < 1)
            return false;
        else
            return true;

    }
    public Cursor consultarDisponibilidadeExemplar(String isbn) {
        Cursor cursor;
        String[] campos = { "idExemplar", "isbn", "dataRecebida", "disponivel"};
        String filtro = "isbn='" + isbn + "' and disponivel = '1'";

        db = banco.getReadableDatabase();

        cursor = db.query("exemplar", campos, filtro,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
    //coloca o exemplares de um certo id como disponivel
    public String resetarDisponibilidade(int idExemplar){

        String msg = "Dados database resetada com sucesso!!!";

        db = banco.getReadableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("disponivel", "1");

        String condicao = "disponivel = " + "0";

        int linha ;
        linha = db.update("exemplar", valores, condicao, null);

        if (linha < 1){
            msg = "Erro ao alterar os dados";
        }

        db.close();
        return msg;
    }
    //apenas de referencia
    public String excluirDados(int id){
        String msg = "Registro Excluído" ;

        db = banco.getReadableDatabase();

        String condicao = "codigo = " + id ;

        int linhas ;
        linhas = db.delete("contatos", condicao, null) ;

        if ( linhas < 1) {
            msg = "Erro ao Excluir" ;
        }

        db.close();
        return msg;
    }

}

