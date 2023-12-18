package com.example.librovermosite.models.plugs;

public class Fullproduct {
    public long id_product;
    public String product_name;
    public String product_ven_code;
    public String product_description;
    public String product_date;
    public String product_keywords;
    public String product_price;
    public String product_file_ref;
    public String product_logo_ref;
    public String product_promo_ref;
    public String id_author;
    public String author_alias;
    public String id_language;
    public String language_name;
    public String id_country;
    public String country_name;
    public String genre_name;
    public Fullproduct(){}

    public Fullproduct(long id_product, String product_name, String product_ven_code, String product_description, String product_date, String product_keywords, String product_price, String product_file_ref, String product_logo_ref, String product_promo_ref, String id_author, String author_alias, String id_language, String language_name, String id_country, String country_name, String genre_name) {
        this.id_product = id_product;
        this.product_name = product_name;
        this.product_ven_code = product_ven_code;
        this.product_description = product_description;
        this.product_date = product_date;
        this.product_keywords = product_keywords;
        this.product_price = product_price;
        this.product_file_ref = product_file_ref;
        this.product_logo_ref = product_logo_ref;
        this.product_promo_ref = product_promo_ref;
        this.id_author = id_author;
        this.author_alias = author_alias;
        this.id_language = id_language;
        this.language_name = language_name;
        this.id_country = id_country;
        this.country_name = country_name;
        this.genre_name = genre_name;
    }

    public long getId_product() {
        return id_product;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_ven_code() {
        return product_ven_code;
    }

    public void setProduct_ven_code(String product_ven_code) {
        this.product_ven_code = product_ven_code;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_date() {
        return product_date;
    }

    public void setProduct_date(String product_date) {
        this.product_date = product_date;
    }

    public String getProduct_keywords() {
        return product_keywords;
    }

    public void setProduct_keywords(String product_keywords) {
        this.product_keywords = product_keywords;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_file_ref() {
        return product_file_ref;
    }

    public void setProduct_file_ref(String product_file_ref) {
        this.product_file_ref = product_file_ref;
    }

    public String getProduct_logo_ref() {
        return product_logo_ref;
    }

    public void setProduct_logo_ref(String product_logo_ref) {
        this.product_logo_ref = product_logo_ref;
    }

    public String getProduct_promo_ref() {
        return product_promo_ref;
    }

    public void setProduct_promo_ref(String product_promo_ref) {
        this.product_promo_ref = product_promo_ref;
    }

    public String getId_author() {
        return id_author;
    }

    public void setId_author(String id_author) {
        this.id_author = id_author;
    }

    public String getAuthor_alias() {
        return author_alias;
    }

    public void setAuthor_alias(String author_alias) {
        this.author_alias = author_alias;
    }

    public String getId_language() {
        return id_language;
    }

    public void setId_language(String id_language) {
        this.id_language = id_language;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    public String getId_country() {
        return id_country;
    }

    public void setId_country(String id_country) {
        this.id_country = id_country;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }
}
