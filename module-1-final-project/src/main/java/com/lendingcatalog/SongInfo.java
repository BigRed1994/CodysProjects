package com.lendingcatalog;

public class SongInfo extends MediaInfo {

    private String artist;
    private String album;


    public SongInfo(String title, String artist, String album, String genre){
        super(title, genre);
        this.album = album;
        this.artist = artist;

    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


    @Override
    public void Play() {
        System.out.println("Playing Song: " + title + " by " + artist);
    }

    @Override
    public String toString(){
        return title + " by " + artist;
    }


}

