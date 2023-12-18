package com.sitebooks.librovermo.models.primary;

import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id_Product;
    @Column(name = "Product_Ven_Code", nullable = false, length = 10, unique = true)
    private String product_Ven_Code;
    @Column(name = "Product_Name", nullable = false, length = 100)
    private String product_Name;
    @Column(name = "Product_Description", nullable = false, length = 400)
    private String product_Description;
    @Column(name = "Product_File_Ref", nullable = false)
    private String product_File_Ref;
    @Column(name = "Product_Logo_Ref", nullable = false, length = 300)
    private String product_Logo_Ref;
    @Column(name = "Product_Promo_Ref", nullable = false, length = 300)
    private String product_Promo_Ref;
    @Column(name = "Product_Date", nullable = false, length = 10)
    private String product_Date;
    @Column(name = "Product_Keywords", nullable = false, length = 500)
    private String product_Keywords;
    @Column(name = "Product_Price", nullable = false, length = 10)
    private String product_Price;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_Author",nullable = false)
    private Author ID_Author;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_Language",nullable = false)
    private Language ID_Language;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_Country",nullable = false)
    private Country ID_Country;

    public Product(){}

    public Product(String id_Product, String product_Ven_Code, String product_Name, String product_Description, String product_File_Ref, String product_Logo_Ref, String product_Promo_Ref, String product_Date, String product_Keywords, String product_Price, Author product_Author, Language product_Language, Country product_Country) {
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
        this.ID_Author = product_Author;
        this.ID_Language = product_Language;
        this.ID_Country = product_Country;
    }

    public String getProduct_Ven_Code() {
        return product_Ven_Code;
    }

    public void setProduct_Ven_Code(String product_Ven_Code) {
        this.product_Ven_Code = product_Ven_Code;
    }

    public String getId_Product() {
        return id_Product;
    }

    public void setId_Product(String id_Product) {
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

    public Author getProduct_Author() {
        return ID_Author;
    }

    public void setProduct_Author(Author product_Author) {
        this.ID_Author = product_Author;
    }

    public Language getProduct_Language() {
        return ID_Language;
    }

    public void setProduct_Language(Language product_Language) {
        this.ID_Language = product_Language;
    }

    public Country getProduct_Country() {
        return ID_Country;
    }

    public void setProduct_Country(Country product_Country) {
        this.ID_Country = product_Country;
    }
}
