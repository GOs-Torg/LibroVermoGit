package com.sitebooks.librovermo.models.primary;

import jakarta.persistence.*;

@Entity
@Table(name = "Language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_Language;

    @Column(name = "Language_Name", nullable = false, unique = true)
    private String language_Name;

    public Language(){}

    public Language(long IDLanguage, String languageName) {
        this.id_Language = IDLanguage;
        this.language_Name = languageName;
    }

    public long getIDLanguage() {
        return id_Language;
    }

    public void setIDLanguage(long IDLanguage) {
        this.id_Language = IDLanguage;
    }

    public String getLanguageName() {
        return language_Name;
    }

    public void setLanguageName(String languageName) {
        this.language_Name = languageName;
    }
}
