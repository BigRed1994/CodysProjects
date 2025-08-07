package com.lendingcatalog;

import java.util.Set;

public class GenreInfo {
    private String name;
    private Set<SongInfo> songs;

    public GenreInfo(String name, Set<SongInfo> songs){

        this.name = name;
        this.songs =  songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SongInfo> getSongs() {
        return songs;
    }

    public void setSongs(Set<SongInfo> songs) {
        this.songs = songs;
    }
}
