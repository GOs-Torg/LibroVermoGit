package com.sitebooks.librovermo.models.primary;

import jakarta.persistence.*;

@Entity
@Table(name = "Bookgenre")
public class Bookgenre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Bookgenre;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_Product",nullable = false)
    private Product ID_Product;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_Genre",nullable = false)
    private Genre id_Genre;

    public Bookgenre(){}

    public Bookgenre(long id_Bookgenre, Product bookgenre_Product, Genre bookgenre_Genre) {
        this.id_Bookgenre = id_Bookgenre;
        ID_Product = bookgenre_Product;
        id_Genre = bookgenre_Genre;
    }

    public long getId_Bookgenre() {
        return id_Bookgenre;
    }

    public void setId_Bookgenre(long id_Bookgenre) {
        this.id_Bookgenre = id_Bookgenre;
    }

    public Product getBookgenre_Product() {
        return ID_Product;
    }

    public void setBookgenre_Product(Product bookgenre_Product) {
        ID_Product = bookgenre_Product;
    }

    public Genre getBookgenre_Genre() {
        return id_Genre;
    }

    public void setBookgenre_Genre(Genre bookgenre_Genre) {
        id_Genre = bookgenre_Genre;
    }
}
