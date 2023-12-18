package com.sitebooks.librovermo.models.primary;

import jakarta.persistence.*;

@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Author;

    @Column(name = "Author_Name", nullable = false)
    private String author_Name;
    @Column(name = "Author_Second_Name", nullable = false)
    private String author_Second_Name;
    @Column(name = "Author_Middle_Name", nullable = false)
    private String author_Middle_Name;
    @Column(name = "Author_Alias", nullable = false, unique = true)
    private String author_Alias;
    @Column(name = "Author_Date", nullable = false, length = 10)
    private String author_Date;
    @Column(name = "Author_Description", nullable = false, length = 600)
    private String author_Description;

    public Author(){}
    public Author(long id_Author, String author_Name, String author_Second_Name, String author_Middle_Name, String author_Alias, String author_Date, String author_Description) {
        this.id_Author = id_Author;
        this.author_Name = author_Name;
        this.author_Second_Name = author_Second_Name;
        this.author_Middle_Name = author_Middle_Name;
        this.author_Alias = author_Alias;
        this.author_Date = author_Date;
        this.author_Description = author_Description;
    }

    public long getId_Author() {
        return id_Author;
    }

    public void setId_Author(long id_Author) {
        this.id_Author = id_Author;
    }

    public String getAuthor_Name() {
        return author_Name;
    }

    public void setAuthor_Name(String author_Name) {
        this.author_Name = author_Name;
    }

    public String getAuthor_Second_Name() {
        return author_Second_Name;
    }

    public void setAuthor_Second_Name(String author_Second_Name) {
        this.author_Second_Name = author_Second_Name;
    }

    public String getAuthor_Middle_Name() {
        return author_Middle_Name;
    }

    public void setAuthor_Middle_Name(String author_Middle_Name) {
        this.author_Middle_Name = author_Middle_Name;
    }

    public String getAuthor_Alias() {
        return author_Alias;
    }

    public void setAuthor_Alias(String author_Alias) {
        this.author_Alias = author_Alias;
    }

    public String getAuthor_Date() {
        return author_Date;
    }

    public void setAuthor_Date(String author_Date) {
        this.author_Date = author_Date;
    }

    public String getAuthor_Description() {
        return author_Description;
    }

    public void setAuthor_Description(String author_Description) {
        this.author_Description = author_Description;
    }
}
