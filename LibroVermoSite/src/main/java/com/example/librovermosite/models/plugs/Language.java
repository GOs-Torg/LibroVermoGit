package com.example.librovermosite.models.plugs;


public class Language {
    private long id_Language;
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
