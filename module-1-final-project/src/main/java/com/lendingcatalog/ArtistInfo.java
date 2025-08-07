package com.lendingcatalog;

import java.util.List;

public class ArtistInfo {
    private String name;
    private int songCount;
    private List<SongInfo> songs;

    public void ArtistInfo(String name, int songCount, List<SongInfo> songs){
        this.name = name;
        this.songCount = songCount;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }

    public List<SongInfo> getSongs() {
        return songs;
    }

    public void setSongs(List<SongInfo> songs) {
        this.songs = songs;
    }
}
