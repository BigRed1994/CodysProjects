package com.lendingcatalog;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class PlaylistSaver {

    public static void savePlaylistToFile(Map<String, SongInfo> songs, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (SongInfo song : songs.values()) {
                writer.println(song.getTitle() + ", " + song.getArtist() + ", " + song.getAlbum() + ", " + song.getGenre());
            }
            System.out.println("Playlist saved to " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not write to file :(");
        }
    }
}

