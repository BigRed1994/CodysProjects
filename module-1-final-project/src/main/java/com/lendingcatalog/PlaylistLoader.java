package com.lendingcatalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistLoader {

//    private static void addSong(String title, String artist, String album, String genre) {
//
//    }

    public static List<String[]> loadSongsFromFile(String filename) {

        List<String[]> songsParts = new ArrayList<>();

        File file = new File(filename);
        System.out.println("Attempting to load from: " + file.getAbsolutePath());

        try (Scanner fileScanner = new Scanner(file)) {
            System.out.println("Reading File: " + filename);
            System.out.println("Lines From File: ");

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }
                
                String[] parts = line.split(",");

                if(parts.length != 4) {

                    System.out.println("Not The Right Format Skipping Line: " + line);
                    continue;
                }

                songsParts.add(parts);

            }

        } catch (FileNotFoundException e) {

            System.out.println("Error file not found!!" + filename);

        }
        return songsParts;
    }

}
