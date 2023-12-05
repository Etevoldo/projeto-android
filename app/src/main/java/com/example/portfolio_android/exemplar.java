package com.example.portfolio_android;

public class exemplar {
    int idExemplar;
    String isbn;
    String dataRecebida;
    boolean disponivel;

    public void exemplar() { }
   public void exemplar( int idExemplar, String isbn, String dataRecebida, Boolean disponivel ) {
        this.idExemplar = idExemplar; this.isbn = isbn;
        this.dataRecebida = dataRecebida; this.disponivel = disponivel;
    }

    public int getIdExemplar() { return idExemplar; }
    public String getIsbn() { return isbn; }
    public String getDataRecebida() { return dataRecebida; }
    public boolean isDisponivel() { return disponivel; }

    public void setIdExemplar(int idExemplar) { this.idExemplar = idExemplar; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public void setDataRecebida(String dataRecebida) { this.dataRecebida = dataRecebida; }
}
