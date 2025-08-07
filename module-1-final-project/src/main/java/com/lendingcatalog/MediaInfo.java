package com.lendingcatalog;

public abstract class MediaInfo implements Playable{
    protected String title;
    protected String genre;

    public MediaInfo(String title, String genre){
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
