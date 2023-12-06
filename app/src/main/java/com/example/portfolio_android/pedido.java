package com.example.portfolio_android;

public class pedido {
    int idPedido, idExemplar;
    String ra, dataEntrega, dataPedido;

    public pedido() { }
    public pedido( int idPedido, int idExemplar, String ra
                 , String dataEntrega, String dataPedido ) {
        this.idPedido = idPedido;
        this.idExemplar = idExemplar;
        this.ra = ra;
        this.dataEntrega = dataEntrega;
        this.dataPedido = dataPedido;
    }

    public int getIdPedido() {return idPedido;}
    public int getIdExemplar() {return idExemplar;}
    public String getRa(){return ra;}
    public String getDataEntrega() { return dataEntrega; }
    public String getDataPedido() { return dataPedido; }
    public void setIdExemplar(int idExemplar) { this.idExemplar = idExemplar; }
    public void setRa(String ra) { this.ra = ra; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    public void setDataPedido(String dataPedido) { this.dataPedido = dataPedido; }
    public void setDataEntrega(String dataEntrega) { this.dataEntrega = dataEntrega; }

}
