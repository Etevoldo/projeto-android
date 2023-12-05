package com.example.portfolio_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco_biblioteca.db";
    private static final int VERSAO = 15;
    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE pessoa ("
                + "ra text primary key,"
                + "nome text,"
                + "email text,"
                + "senha text,"
                + "numeroDePedidos int)";
        db.execSQL(sql);
        String sql2 = "CREATE TABLE livro ("
                + "isbn text primary key,"
                + "titulo text,"
                + "autor text)";
        db.execSQL(sql2);
        String sql3 = "CREATE TABLE exemplar ("
                + "idExemplar integer primary key autoincrement,"
                + "isbn text,"
                + "dataRecebida date,"
                + "disponivel bool,"
                + "constraint fk_EXEMPLAR_LIVRO FOREIGN KEY (isbn) REFERENCES livro (isbn))";
        db.execSQL(sql3);
        String sql4 = "CREATE TABLE pedido ("
                + "idPedido integer primary key autoincrement,"
                + "idExemplar integer,"
                + "ra text,"
                + "isbn text,"
                + "dataEntrega text,"
                + "dataPedido text,"
                + "CONSTRAINT fk_PEDIDO_ID FOREIGN KEY (idExemplar)"
                    + "REFERENCES exemplar(idExemplar))";
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pessoa");
        db.execSQL("DROP TABLE IF EXISTS livro");
        db.execSQL("DROP TABLE IF EXISTS exemplar");
        db.execSQL("DROP TABLE IF EXISTS pedido");
        onCreate(db);

    }
}