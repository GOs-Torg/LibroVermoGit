package com.example.librovermosite.models.plugs;

public class Purchase {
    private long id_Purchase;
    private String purchase_Cost;
    private String purchase_Num;
    private String purchase_Date;
    private String id_Client;

    public Purchase(){}
    public Purchase(long id_Purchase, String purchase_Cost, String purchase_Num, String purchase_Date, String purchase_Client) {
        this.id_Purchase = id_Purchase;
        this.purchase_Cost = purchase_Cost;
        this.purchase_Num = purchase_Num;
        this.purchase_Date = purchase_Date;
        this.id_Client = purchase_Client;
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

    public String getPurchase_Client() {
        return id_Client;
    }

    public void setPurchase_Client(String purchase_Client) {
        this.id_Client = purchase_Client;
    }
}
