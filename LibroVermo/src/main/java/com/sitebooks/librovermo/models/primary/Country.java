package com.sitebooks.librovermo.models.primary;

import jakarta.persistence.*;

@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Country;

    @Column(name = "Country_Name", nullable = false, unique = true)
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
