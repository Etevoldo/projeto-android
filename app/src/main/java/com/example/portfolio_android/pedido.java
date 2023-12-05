package com.example.portfolio_android;

public class pedido {
    int idPedido;
    int idExemplar;
    String ra;
    String isbn;
    String dataEntrega;
    String dataPedido;

    public pedido() { }
    public pedido( int idPedido, int idExemplar, String ra,
                   String isbn, String dataEntrega, String dataPedido ) {
        this.idPedido = idPedido;
        this.idExemplar = idExemplar;
        this.ra = ra;
        this.isbn = isbn;
        this.dataEntrega = dataEntrega;
        this.dataPedido = dataPedido;
    }

    public int getIdPedido() {return idPedido;}
    public int getIdExemplar() {return idExemplar;}
    public String getRa(){return ra;}
    public String getDataEntrega() { return dataEntrega; }
    public String getDataPedido() { return dataPedido; }
    public void setIdExemplar(int idExemplar) { this.idExemplar = idExemplar; } // sem certeza se é necessario
    public void setRa(String ra) { this.ra = ra; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    public void setDataPedido(String dataPedido) { this.dataPedido = dataPedido; }
    public void setDataEntrega(String dataEntrega) { this.dataEntrega = dataEntrega; }

}
