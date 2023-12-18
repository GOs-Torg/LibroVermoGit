package com.sitebooks.librovermo.models.primary;

import jakarta.persistence.*;

@Entity
@Table(name = "Purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Purchase;
    @Column(name = "Purchase_Cost", nullable = false, length = 10)
    private String purchase_Cost;
    @Column(name = "Purchase_Num", nullable = false, length = 8, unique = true)
    private String purchase_Num;
    @Column(name = "Purchase_Date", nullable = false, length = 10)
    private String purchase_Date;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_Client",nullable = false)
    private Client ID_Client;

    public Purchase(){}
    public Purchase(long id_Purchase, String purchase_Cost, String purchase_Num, String purchase_Date, Client purchase_Client) {
        this.id_Purchase = id_Purchase;
        this.purchase_Cost = purchase_Cost;
        this.purchase_Num = purchase_Num;
        this.purchase_Date = purchase_Date;
        this.ID_Client = purchase_Client;
    }

    public long getId_Purchase() {
        return id_Purchase;
    }

    public void setId_Purchase(long id_Purchase) {
        this.id_Purchase = id_Purchase;
    }

    public String getPurchase_Cost() {
        return purchase_Cost;
    }

    public void setPurchase_Cost(String purchase_Cost) {
        this.purchase_Cost = purchase_Cost;
    }

    public String getPurchase_Num() {
        return purchase_Num;
    }

    public void setPurchase_Num(String purchase_Num) {
        this.purchase_Num = purchase_Num;
    }

    public String getPurchase_Date() {
        return purchase_Date;
    }

    public void setPurchase_Date(String purchase_Date) {
        this.purchase_Date = purchase_Date;
    }

    public Client getPurchase_Client() {
        return ID_Client;
    }

    public void setPurchase_Client(Client purchase_Client) {
        this.ID_Client = purchase_Client;
    }
}
