package com.lendingcatalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;



public class FirstCapStoneProj {

    //This Will Start The Main Loop Of The Program and Wait For The Users Input

    //FINAL CUT!! COMPLETE FINISHED PROJECT!!!
    private static final int MENU_ADD_SONG = 1;
    private static final int MENU_REMOVE_SONG = 2;
    private static final int MENU_PLAY_SONG = 3;
    private static final int MENU_DISPLAY_PLAYLIST = 4;
    private static final int MENU_ARTIST_SEARCH = 5;
    private static final int MENU_LOAD_PLAYLIST = 6;
    private static final int MENU_ARTIST_SORT= 7;
    private static final int MENU_SAVE_PLAYLIST = 8;
    private static final int MENU_DISPLAY_STATS = 9;
    private static final int MENU_EXIT_PROGRAM = 0;

    public static void main(String[] args) {


        FirstCapStoneProj playlist = new FirstCapStoneProj();

        playlist.menu();
    }

    //Title : Artist
    static Map<String, SongInfo> songs;
    //Album List
    private static List<AlbumInfo> albums;
    //Genre Of Music
    private static Set<GenreInfo> genres;
    //"Now Playing" Queue
    static Queue<SongInfo> nowPlayingQueue = null;
    // List For Songs That Were Recently Played
    static List<SongInfo> history = null;
    //Count For The Artist Duplicates


    public FirstCapStoneProj() {

        // Creates A New HashMap()

        songs = new HashMap<>();

        //Creates A New ArrayList()

        albums = new ArrayList<>();

        //Creates A New HashSet()

        genres = new HashSet<>();

        // Creates A New LinkedList

        nowPlayingQueue = new LinkedList<SongInfo>();

        //Creates a New ArrayList()

        history = new ArrayList<>();

    }


    //Interface Usage
    public void demoInterfacePlay() {
        System.out.println("Demo: Playing Song and Album Using The Playable Interface:");
        if (!songs.isEmpty()) {
            SongInfo anySong = songs.values().iterator().next();
            anySong.Play();
        }
        if (!albums.isEmpty()) {
            AlbumInfo anyAlbum = albums.get(0);
            anyAlbum.Play();
        }
    }

    //Custom Exception
//    public class FieldsNotFilledOutException extends Exception {
//        public FieldsNotFilledOutException(String message) {
//            super(message);
//        }
//    }

    // Method To Add a Song *User Interactive
    public void addSong(String title, String artist, String album, String genre) {
        if (songs == null) {
            songs = new HashMap<>();
        }
        try {
            if (title.isEmpty() || artist.isEmpty() || album.isEmpty() || genre.isEmpty()) {
                throw new FieldsNotFilledOutException("Fields Not Filled Out");

            } // define the custom (IncompleteSongException) exception and implement it

            SongInfo song = new SongInfo(title, artist, album, genre) {

            };
            songs.put(title, song);
            System.out.println(title + " by " + artist + " added!");

        } catch (FieldsNotFilledOutException e) {

            System.out.println(e.getMessage());
        }

    }

    //Method To Remove a Song *User Interactive
    public void removeSong(String title) {

        if (songs.containsKey(title)) {

            songs.remove(title);

            System.out.println(title + " Removed");

        } else {
            System.out.println("No Song To Remove");
        }
    }

    public void displayQueue() {

        if (nowPlayingQueue.isEmpty()) {

            System.out.println("Nothing In Queue");

        } else {
            System.out.println("\nUpcoming Songs In Queue: ");

            for (SongInfo song : nowPlayingQueue) {

                System.out.println(song);
            }
        }
    }

    //Method to Play Songs, Will Display the Song Title *User Interactive
    public void playSong(String title) {

        if (songs.containsKey(title)) {

            SongInfo song = songs.get(title);

            nowPlayingQueue.offer(song);

            history.add(songs.get(title));

            System.out.println(" Now Playing: " + song);

            displayQueue();
        } else {

            System.out.println("Song Not Found :(");
        }

    }

    //Method That Will Display The Playlist
    public void displayPlaylist() {

        if (songs.isEmpty()) {
            System.out.println("Playlist Is Empty :(");

        } else {
            System.out.println("Playlist: ");

            for (String title : songs.keySet()) {

                System.out.println(songs.get(title));
            }

        }

    }

    //Method To Take Care Of Searching For An Artist *User Interactive
    public void artistSearch(String artist) {

        boolean artistFound = false;

        System.out.println("\n Songs By " + artist + ":");

        for (Map.Entry<String, SongInfo> entry : songs.entrySet()) {

            if (entry.getValue().getArtist().equalsIgnoreCase(artist)) {

                System.out.println(entry.getKey());

                artistFound = true;
            }
        }
        if (!artistFound) {

            System.out.println("No Songs Found :(");
        }
    }

    //This Method Will Sort The Artists In Alphabetical Order
    public void artistSort() {
        List<String> sortedArtist = new ArrayList<>();

        for (String title : songs.keySet()) {

            String artist = songs.get(title).getArtist();

            sortedArtist.add(artist + " - " + title);
        }
        Collections.sort(sortedArtist);

        System.out.println("\n Sorted By Artist: ");

        for (String song : sortedArtist) {

            System.out.println(song);
        }
    }


    // This Method will display the # of songs artist genres and albums in your playlist
    public void displayPlaylistStats() {

        if (songs.isEmpty()) {

            System.out.println("\nThe playlist is empty. Add some songs first!");

            return;
        }

        System.out.println("Playlist Stats ");

        System.out.println("Total Songs: " + songs.size());

        System.out.println("Artists: " + new HashSet<>(songs.values()).size());

        System.out.println("Albums: " + new HashSet<>(albums).size());

        System.out.println("Genres: " + genres.size());

    }

    //This Method Will Bring Up An Interactive Menu For The Application
    public void menu() {

        demoInterfacePlay();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("\nMusic Playlist Organizer: ");

            System.out.println("1. Add Song");

            System.out.println("2. Remove Song");

            System.out.println("3. Play Song");

            System.out.println("4. Display Playlist");

            System.out.println("5. Search by Artist");

            System.out.println("6. Load In Playlist");

            System.out.println("7. Sort by Artist");

            System.out.println("8. Save Playlist");

            System.out.println("9. Display Playlist Stats");

            System.out.println("0. Exit");

            System.out.print("Choose An Option Number: ");

            //Using A Scanner To Capture The Users Input


            //This Will Display Whatever Option The User Has Selected

            String input = scanner.nextLine();

            if (input.matches("\\d")) {

                int chooseNum = Integer.parseInt(input);

                //This If Statement Will Execute The Users Input

                if (chooseNum == MENU_ADD_SONG) {

                    // Selecting 1 For Option Will Let You Add A Song

                    System.out.println("Please Fill Out Each Section!!");

                    System.out.print("Enter Song Title: ");

                    String title = scanner.nextLine();

                    System.out.print("Enter Artist: ");

                    String artist = scanner.nextLine();

                    System.out.print("Enter Album: ");

                    String album = scanner.nextLine();

                    System.out.print("Enter Genre: ");

                    String genre = scanner.nextLine();

                    addSong(title, artist, album, genre);

                    //User Types In The Title/Artist/Album/Genre Of The Song They Want To Add

                } else if (chooseNum == MENU_REMOVE_SONG) {

                    //Selecting 2 For Option Will Bring Up Prompt To Delete A Song
                    System.out.print("Enter Song Title To Remove: ");

                    String title = scanner.nextLine();

                    removeSong(title);

                    //User Types In Song Title To Be Removed From The Playlist And Presses Enter To Remove It

                } else if (chooseNum == MENU_PLAY_SONG) {

                    //Selecting 3 For Option Will Bring up Prompt To Play A Song

                    System.out.print("Enter Song Title To Play: ");

                    String title = scanner.nextLine();

                    playSong(title);

                    //User Types In The Title Of The Song They Wish To Hear Then Presses Enter For It To Play

                } else if (chooseNum == MENU_DISPLAY_PLAYLIST) {

                    //Selecting 4 For Option Will Display The Playlist

                    displayPlaylist();

                } else if (chooseNum == MENU_ARTIST_SEARCH) {

                    //Selecting 5 For Option Will Bring Up A Prompt To Search For Artist

                    System.out.print("Enter Artist Name: ");

                    String artist = scanner.nextLine();

                    artistSearch(artist);

                    //User Will Type In The Name Of The Artist They're Looking For

                } else if (chooseNum == MENU_LOAD_PLAYLIST) {

                    //Selecting 6 For Option Will Bring Up A Prompt To Load In A file
                    System.out.println("Enter Playlist To Load: ");

                    String filename = scanner.nextLine();

                    List<String[]> songParts = PlaylistLoader.loadSongsFromFile(filename);

                    for (String[] parts : songParts) {
                        String title = parts[0].trim();
                        String artist = parts[1].trim();
                        String album = parts[2].trim();
                        String genre = parts[3].trim();

                        addSong(title, artist, album, genre);
                    }

                    System.out.println("Songs Loaded From File!!!");


                    //User Will Type In The Genre They're Looking For

                } else if (chooseNum == MENU_ARTIST_SORT) {

                    //Selecting 7 For Option Will Sort The Playlist By Artists
                    System.out.print("Artists In This Playlist: ");

                    Set<String> artistSet = new HashSet<>();

                    for(SongInfo song : songs.values()){
                        artistSet.add(song.getArtist());
                    }

                    List<String> sortedArtists = new ArrayList<>(artistSet);

                    Collections.sort(sortedArtists);

                    for(String name : sortedArtists){
                        System.out.println(name);
                    }

                } else if (chooseNum == MENU_SAVE_PLAYLIST) {

                    //Selecting 8 For Option Will Display The Recently Played Songs
                    System.out.print("Enter Playlist Name: ");

                    String playlistName = scanner.nextLine();

                    PlaylistSaver.savePlaylistToFile(songs, playlistName);


                } else if (chooseNum == MENU_DISPLAY_STATS) {

                    displayPlaylistStats();

                } else if (chooseNum == MENU_EXIT_PROGRAM) {

                    //Selecting 0 For Option Will Exit/Leave The Application

                    running = false;

                    System.out.println("Until Next Time :) ");

                   ;

                }else {

                    //If An Invalid Option Is Chosen This Will Display A Prompt To Try Another Option

                    System.out.println("Sorry :( Please Select An Option 0 - 9.");
                }

            } else {
                System.out.println("Sorry :( Please Enter A Number Between 0 And 9");
            }
        }
    }


}


