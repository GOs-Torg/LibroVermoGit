package com.sitebooks.librovermo.models.plugs;

public class Review {
    private long id_Review;
    private String review_Title;
    private String review_Text;
    private String review_Rate;
    private String review_Status;
    private String review_Data;
    private String  id_Client;
    private String id_Employee;
    private String id_Product;

    public Review(){}
    public Review(long id_Review, String review_Title, String review_Text, String review_Rate, String review_Status, String review_Data, String review_Client, String review_Employee, String review_Product) {
        this.id_Review = id_Review;
        this.review_Title = review_Title;
        this.review_Text = review_Text;
        this.review_Rate = review_Rate;
        this.review_Status = review_Status;
        this.review_Data = review_Data;
        this.id_Client = review_Client;
        this.id_Employee = review_Employee;
        this.id_Product = review_Product;
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

    public String getId_Client() {
        return id_Client;
    }

    public void setId_Client(String id_Client) {
        this.id_Client = id_Client;
    }

    public String getId_Employee() {
        return id_Employee;
    }

    public void setId_Employee(String id_Employee) {
        this.id_Employee = id_Employee;
    }

    public String getId_Product() {
        return id_Product;
    }

    public void setId_Product(String id_Product) {
        this.id_Product = id_Product;
    }
}
