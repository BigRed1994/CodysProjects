package com.lendingcatalog;

import java.util.List;

public class AlbumInfo implements Playable {
    private String albumName;
    private String artistName;
    private List<SongInfo> songs;

    public AlbumInfo(String albumName, String artistName, List<SongInfo> songs){
        this.albumName = albumName;
        this.artistName = artistName;
        this.songs = songs;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public List<SongInfo> getSongs() {
        return songs;
    }

    public void setSongs(List<SongInfo> songs) {
        this.songs = songs;
    }


    @Override
    public void Play() {
        System.out.println("Playing From Album: " + albumName + " by " + artistName);
    }

}
