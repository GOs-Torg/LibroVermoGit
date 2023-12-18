package com.sitebooks.librovermo.models.plugs;


public class Language {
    private long id_Language;
    private String language_Name;

    public Language(){}

    public Language(long IDLanguage, String languageName) {
        this.id_Language = IDLanguage;
        this.language_Name = languageName;
    }

    public long getId_Language() {
        return id_Language;
    }

    public void setId_Language(long id_Language) {
        this.id_Language = id_Language;
    }

    public String getLanguage_Name() {
        return language_Name;
    }

    public void setLanguage_Name(String language_Name) {
        this.language_Name = language_Name;
    }
}
