package com.example.librovermosite.models.plugs;

public class Genre {
    private long id_Genre;
    private String genre_Name;

    public Genre(){}
    public Genre(long id_Genre, String genre_Name) {
        this.id_Genre = id_Genre;
        this.genre_Name = genre_Name;
    }

    public long getId_Genre() {
        return id_Genre;
    }

    public void setId_Genre(long id_Genre) {
        this.id_Genre = id_Genre;
    }

    public String getGenre_Name() {
        return genre_Name;
    }

    public void setGenre_Name(String genre_Name) {
        this.genre_Name = genre_Name;
    }
}
