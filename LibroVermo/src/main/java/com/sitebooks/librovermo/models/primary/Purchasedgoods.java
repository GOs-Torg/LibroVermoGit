package com.sitebooks.librovermo.models.primary;

import jakarta.persistence.*;

@Entity
@Table(name = "Purchasedgoods")
public class Purchasedgoods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Purchasedgoods;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_Product",nullable = false)
    private Product ID_Product;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_Client",nullable = false)
    private Client ID_Client;

    public  Purchasedgoods(){}
    public Purchasedgoods(long id_Purchasedgoods, Product purchasedgoods_Product, Client purchasedgoods_Client) {
        this.id_Purchasedgoods = id_Purchasedgoods;
        ID_Product = purchasedgoods_Product;
        ID_Client = purchasedgoods_Client;
    }

    public long getId_Purchasedgoods() {
        return id_Purchasedgoods;
    }

    public void setId_Purchasedgoods(long id_Purchasedgoods) {
        this.id_Purchasedgoods = id_Purchasedgoods;
    }

    public Product getPurchasedgoods_Product() {
        return ID_Product;
    }

    public void setPurchasedgoods_Product(Product purchasedgoods_Product) {
        ID_Product = purchasedgoods_Product;
    }

    public Client getPurchasedgoods_Client() {
        return ID_Client;
    }

    public void setPurchasedgoods_Client(Client purchasedgoods_Client) {
        ID_Client = purchasedgoods_Client;
    }
}
