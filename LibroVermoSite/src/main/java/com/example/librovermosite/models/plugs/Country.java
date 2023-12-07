package com.example.librovermosite.models.plugs;


public class Country {
    private long id_Country;
    private String Country_Name;

    public Country(){}
    public Country(long id_Country, String country_Name) {
        this.id_Country = id_Country;
        Country_Name = country_Name;
    }

    public long getId_Country() {
        return id_Country;
    }

    public void setId_Country(long id_Country) {
        this.id_Country = id_Country;
    }

    public String getCountry_Name() {
        return Country_Name;
    }

    public void setCountry_Name(String country_Name) {
        Country_Name = country_Name;
    }
}
