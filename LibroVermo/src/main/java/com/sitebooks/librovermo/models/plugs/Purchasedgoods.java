package com.sitebooks.librovermo.models.plugs;

public class Purchasedgoods {
    private long id_Purchasedgoods;
    private String id_Product;
    private String id_Client;

    public  Purchasedgoods(){}
    public Purchasedgoods(long id_Purchasedgoods, String purchasedgoods_Product, String purchasedgoods_Client) {
        this.id_Purchasedgoods = id_Purchasedgoods;
        id_Product = purchasedgoods_Product;
        id_Client = purchasedgoods_Client;
    }

    public long getId_Purchasedgoods() {
        return id_Purchasedgoods;
    }

    public void setId_Purchasedgoods(long id_Purchasedgoods) {
        this.id_Purchasedgoods = id_Purchasedgoods;
    }

    public String getPurchasedgoods_Product() {
        return id_Product;
    }

    public void setPurchasedgoods_Product(String purchasedgoods_Product) {
        id_Product = purchasedgoods_Product;
    }

    public String getPurchasedgoods_Client() {
        return id_Client;
    }

    public void setPurchasedgoods_Client(String purchasedgoods_Client) {
        id_Client = purchasedgoods_Client;
    }
}
