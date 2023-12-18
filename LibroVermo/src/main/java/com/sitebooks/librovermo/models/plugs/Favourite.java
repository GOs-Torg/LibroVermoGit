package com.sitebooks.librovermo.models.plugs;


public class Favourite {
    private long id_Favourite;
    private String id_Product;
    private String id_Client;

    public Favourite(){}
    public Favourite(long id_Favourite, String favourite_Product, String favourite_Client) {
        this.id_Favourite = id_Favourite;
        this.id_Product = favourite_Product;
        this.id_Client = favourite_Client;
    }

    public long getId_Favourite() {
        return id_Favourite;
    }

    public void setId_Favourite(long id_Favourite) {
        this.id_Favourite = id_Favourite;
    }

    public String getFavourite_Product() {
        return id_Product;
    }

    public void setFavourite_Product(String favourite_Product) {
        this.id_Product = favourite_Product;
    }

    public String getFavourite_Client() {
        return id_Client;
    }

    public void setFavourite_Client(String favourite_Client) {
        this.id_Client = favourite_Client;
    }
}
