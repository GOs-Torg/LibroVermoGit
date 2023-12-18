package com.sitebooks.librovermo.models.plugs;

public class Product {
    private long id_Product;
    private String product_Ven_Code;
    private String product_Name;
    private String product_Description;
    private String product_File_Ref;
    private String product_Logo_Ref;
    private String product_Promo_Ref;
    private String product_Date;
    private String product_Keywords;
    private String product_Price;
    private String id_Author;
    private String id_Language;
    private String id_Country;

    public Product(){}

    public Product(long id_Product, String product_Ven_Code, String product_Name, String product_Description, String product_File_Ref, String product_Logo_Ref, String product_Promo_Ref, String product_Date, String product_Keywords, String product_Price, String product_Author, String product_Language, String product_Country) {
        this.id_Product = id_Product;
        this.product_Ven_Code = product_Ven_Code;
        this.product_Name = product_Name;
        this.product_Description = product_Description;
        this.product_File_Ref = product_File_Ref;
        this.product_Logo_Ref = product_Logo_Ref;
        this.product_Promo_Ref = product_Promo_Ref;
        this.product_Date = product_Date;
        this.product_Keywords = product_Keywords;
        this.product_Price = product_Price;
        this.id_Author = product_Author;
        this.id_Language = product_Language;
        this.id_Country = product_Country;
    }

    public String getProduct_Ven_Code() {
        return product_Ven_Code;
    }

    public void setProduct_Ven_Code(String product_Ven_Code) {
        this.product_Ven_Code = product_Ven_Code;
    }

    public long getId_Product() {
        return id_Product;
    }

    public void setId_Product(long id_Product) {
        this.id_Product = id_Product;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public String getProduct_Description() {
        return product_Description;
    }

    public void setProduct_Description(String product_Description) {
        this.product_Description = product_Description;
    }

    public String getProduct_File_Ref() {
        return product_File_Ref;
    }

    public void setProduct_File_Ref(String product_File_Ref) {
        this.product_File_Ref = product_File_Ref;
    }

    public String getProduct_Logo_Ref() {
        return product_Logo_Ref;
    }

    public void setProduct_Logo_Ref(String product_Logo_Ref) {
        this.product_Logo_Ref = product_Logo_Ref;
    }

    public String getProduct_Promo_Ref() {
        return product_Promo_Ref;
    }

    public void setProduct_Promo_Ref(String product_Promo_Ref) {
        this.product_Promo_Ref = product_Promo_Ref;
    }

    public String getProduct_Date() {
        return product_Date;
    }

    public void setProduct_Date(String product_Date) {
        this.product_Date = product_Date;
    }

    public String getProduct_Keywords() {
        return product_Keywords;
    }

    public void setProduct_Keywords(String product_Keywords) {
        this.product_Keywords = product_Keywords;
    }

    public String getProduct_Price() {
        return product_Price;
    }

    public void setProduct_Price(String product_Price) {
        this.product_Price = product_Price;
    }

    public String getId_Author() {
        return id_Author;
    }

    public void setId_Author(String id_Author) {
        this.id_Author = id_Author;
    }

    public String getId_Language() {
        return id_Language;
    }

    public void setId_Language(String id_Language) {
        this.id_Language = id_Language;
    }

    public String getId_Country() {
        return id_Country;
    }

    public void setId_Country(String id_Country) {
        this.id_Country = id_Country;
    }
}
