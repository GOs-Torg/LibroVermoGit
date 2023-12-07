package com.example.librovermosite.models.plugs;


public class Bookgenre {
    private long id_Bookgenre;
    private String id_Product;
    private String id_Genre;

    public Bookgenre(){}

    public Bookgenre(long id_Bookgenre, String bookgenre_Product, String bookgenre_Genre) {
        this.id_Bookgenre = id_Bookgenre;
        id_Product = bookgenre_Product;
        id_Genre = bookgenre_Genre;
    }

    public long getId_Bookgenre() {
        return id_Bookgenre;
    }

    public void setId_Bookgenre(long id_Bookgenre) {
        this.id_Bookgenre = id_Bookgenre;
    }

    public String getBookgenre_Product() {
        return id_Product;
    }

    public void setBookgenre_Product(String bookgenre_Product) {
        id_Product = bookgenre_Product;
    }

    public String getBookgenre_Genre() {
        return id_Genre;
    }

    public void setBookgenre_Genre(String bookgenre_Genre) {
        id_Genre = bookgenre_Genre;
    }
}
