package com.example.librovermosite.models.plugs;

public class authorizereview {
    public Long id_review;
    public String id_client;
    public String id_product;

    public String client_nickname;
    public String review_title;
    public String review_text;
    public String review_data;
    public String review_rate;
    public authorizereview(){}

    public authorizereview(Long id_review, String id_client, String id_product, String client_nickname, String review_title, String review_text, String review_data, String review_rate) {
        this.id_review = id_review;
        this.id_client = id_client;
        this.id_product = id_product;
        this.client_nickname = client_nickname;
        this.review_title = review_title;
        this.review_text = review_text;
        this.review_data = review_data;
        this.review_rate = review_rate;
    }

    public Long getId_review() {
        return id_review;
    }

    public void setId_review(Long id_review) {
        this.id_review = id_review;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getClient_nickname() {
        return client_nickname;
    }

    public void setClient_nickname(String client_nickname) {
        this.client_nickname = client_nickname;
    }

    public String getReview_title() {
        return review_title;
    }

    public void setReview_title(String review_title) {
        this.review_title = review_title;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public String getReview_data() {
        return review_data;
    }

    public void setReview_data(String review_data) {
        this.review_data = review_data;
    }

    public String getReview_rate() {
        return review_rate;
    }

    public void setReview_rate(String review_rat) {
        this.review_rate = review_rat;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }
}
