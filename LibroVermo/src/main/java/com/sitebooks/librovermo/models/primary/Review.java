package com.sitebooks.librovermo.models.primary;

import jakarta.persistence.*;


@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Review;
    @Column(name = "Review_Title", nullable = false, length = 30)
    private String review_Title;
    @Column(name = "Review_Text", nullable = false, length = 2300)
    private String review_Text;
    @Column(name = "Review_Rate", nullable = false, length = 3)
    private String review_Rate;
    @Column(name = "Review_Status", nullable = false, length = 10)
    private String review_Status;
    @Column(name = "Review_Data", nullable = false, length = 10)
    private String review_Data;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_Client",nullable = false)
    private Client ID_Client;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_Employee",nullable = true)
    private Employee ID_Employee;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_Product",nullable = false)
    private Product ID_Product;

    public Review(){}
    public Review(long id_Review, String review_Title, String review_Text, String review_Rate, String review_Status, String review_Data, Client review_Client, Employee review_Employee, Product review_Product) {
        this.id_Review = id_Review;
        this.review_Title = review_Title;
        this.review_Text = review_Text;
        this.review_Rate = review_Rate;
        this.review_Status = review_Status;
        this.review_Data = review_Data;
        this.ID_Client = review_Client;
        this.ID_Employee = review_Employee;
        this.ID_Product = review_Product;
    }

    public long getId_Review() {
        return id_Review;
    }

    public void setId_Review(long id_Review) {
        this.id_Review = id_Review;
    }

    public String getReview_Title() {
        return review_Title;
    }

    public void setReview_Title(String review_Title) {
        this.review_Title = review_Title;
    }

    public String getReview_Text() {
        return review_Text;
    }

    public void setReview_Text(String review_Text) {
        this.review_Text = review_Text;
    }

    public String getReview_Rate() {
        return review_Rate;
    }

    public void setReview_Rate(String review_Rate) {
        this.review_Rate = review_Rate;
    }

    public String getReview_Status() {
        return review_Status;
    }

    public void setReview_Status(String review_Status) {
        this.review_Status = review_Status;
    }

    public String getReview_Data() {
        return review_Data;
    }

    public void setReview_Data(String review_Data) {
        this.review_Data = review_Data;
    }

    public Client getReview_Client() {
        return ID_Client;
    }

    public void setReview_Client(Client review_Client) {
        this.ID_Client = review_Client;
    }

    public Employee getReview_Employee() {
        return ID_Employee;
    }

    public void setReview_Employee(Employee review_Employee) {
        this.ID_Employee = review_Employee;
    }

    public Product getReview_Product() {
        return ID_Product;
    }

    public void setReview_Product(Product review_Product) {
        this.ID_Product = review_Product;
    }
}
