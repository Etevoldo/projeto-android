package com.example.portfolio_android;

public class LivroEPedidos {
    String isbn, titulo, autor, ra, dataEntrega, dataPedido;
    int idPedido, idExemplar;

    public LivroEPedidos() { }
    public LivroEPedidos(String isbn, String titulo, String autor, String ra,
                         String dataEntrega, String dataPedido,
                         int idPedido, int idExemplar) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.ra = ra;
        this.dataEntrega = dataEntrega;
        this.dataPedido = dataPedido;
        this.idPedido = idPedido;
        this.idExemplar = idExemplar;
    }

    public String getIsbn() { return isbn; }
    public String getAutor() { return autor; }
    public String getTitulo() { return titulo; }
    public int getIdPedido() { return idPedido; }
    public int getIdExemplar() { return idExemplar; }
    public String getRa() { return ra; }
    public String getDataEntrega() { return dataEntrega; }
    public String getDataPedido() { return dataPedido; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    public void setIdExemplar(int idExemplar) { this.idExemplar = idExemplar; }
    public void setRa(String ra) { this.ra = ra; }
    public void setDataEntrega(String dataEntrega) { this.dataEntrega = dataEntrega; }
    public void setDataPedido(String dataPedido) { this.dataPedido = dataPedido; }
}
