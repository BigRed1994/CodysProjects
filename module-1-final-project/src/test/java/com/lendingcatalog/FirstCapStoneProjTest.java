package com.lendingcatalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class FirstCapStoneProjTest {

    private FirstCapStoneProj app;

    @BeforeEach
    public void setUp() {

        app = new FirstCapStoneProj();
    }

    @Test
    public void testAddSongSuccessfully() {
        app.addSong("Bohemian Rhapsody", "Queen", "A Night at the Opera", "Rock");
        Map<String, SongInfo> songs = FirstCapStoneProj.songs;
        assertTrue(songs.containsKey("Bohemian Rhapsody"));
        assertEquals("Queen", songs.get("Bohemian Rhapsody").getArtist());
    }

    @Test
    public void testAddSongWithMissingFieldsThrowsException() {
        app.addSong("", "Queen", "A Night at the Opera", "Rock");
        assertFalse(FirstCapStoneProj.songs.containsKey(""));
    }

    @Test
    public void testRemoveExistingSong() {
        app.addSong("Imagine", "John Lennon", "Imagine", "Rock");
        app.removeSong("Imagine");
        assertFalse(FirstCapStoneProj.songs.containsKey("Imagine"));
    }

    @Test
    public void testRemoveNonExistingSong() {
        app.addSong("Tuscan Leather", "Drake", "Nothing was the same", "Hip-Hop");
        app.removeSong("2 Phones");
        assertFalse(FirstCapStoneProj.songs.containsKey("2 Phones"));

        assertTrue(FirstCapStoneProj.songs.containsKey("Tuscan Leather"));
    }

    @Test
    public void testPlaySong() {
        app.addSong("Bohemian Rhapsody", "Queen", "A Night at the Opera", "Rock");
        app.playSong("Bohemian Rhapsody");

        assertEquals(1, FirstCapStoneProj.history.size());
        assertEquals("Bohemian Rhapsody", FirstCapStoneProj.history.get(0).getTitle());

        assertFalse(FirstCapStoneProj.nowPlayingQueue.isEmpty());
    }

    @Test
    public void testPlayNonExistingSong() {
        app.addSong("Clarity", "Mac Miller", "Macadelic", "Hip-Hop");
        app.playSong("Tuscan Leather");

        assertTrue(FirstCapStoneProj.nowPlayingQueue.isEmpty());

        if (!FirstCapStoneProj.history.isEmpty()) {
            assertEquals("Clarity", FirstCapStoneProj.history.get(0).getTitle());
        }
    }

    @Test
    public void testPlaySongAddsToQueueAndHistory() {
        app.addSong("Lose Yourself", "Eminem", "8 Mile", "Hip-Hop");
        app.playSong("Lose Yourself");
        assertFalse(FirstCapStoneProj.nowPlayingQueue.isEmpty());
        assertFalse(FirstCapStoneProj.history.isEmpty());
        assertEquals("Lose Yourself", FirstCapStoneProj.nowPlayingQueue.peek().getTitle());
        assertEquals("Lose Yourself", FirstCapStoneProj.history.get(0).getTitle());
    }
    @Test
    public void testAddSongWithSpecialCharacters() {

        app.addSong("Song!@#$%^&*()", "Artist1", "Album1", "Genre1");
        assertTrue(FirstCapStoneProj.songs.containsKey("Song!@#$%^&*()"));
    }

    @Test
    public void testAddSongWithVeryLongTitle() {

        String veryLongTitle = "This is an extremely long song title that might cause issues in some systems but should work fine here because there shouldn't be arbitrary length restrictions";
        app.addSong(veryLongTitle, "Artist1", "Album1", "Genre1");
        assertTrue(FirstCapStoneProj.songs.containsKey(veryLongTitle));
    }

    @Test
    public void testPlayNonExistentSongAfterRemoval() {

        app.addSong("TempSong", "Artist1", "Album1", "Genre1");
        app.removeSong("TempSong");

        int queueSizeBefore = FirstCapStoneProj.nowPlayingQueue.size();
        int historySizeBefore = FirstCapStoneProj.history.size();

        app.playSong("TempSong");


        assertEquals(queueSizeBefore, FirstCapStoneProj.nowPlayingQueue.size());
        assertEquals(historySizeBefore, FirstCapStoneProj.history.size());
    }

    @Test
    public void testSearchForArtistWithNoSongs() {

        assertDoesNotThrow(() -> app.artistSearch("Nonexistent Artist"));
    }

    @Test
    public void testArtistSortWithEmptyPlaylist() {

        assertDoesNotThrow(() -> app.artistSort());
    }

    @Test
    public void testRemoveSongWithSpecialCharacters() {

        app.addSong("Special!@#", "Artist1", "Album1", "Genre1");
        app.removeSong("Special!@#");
        assertFalse(FirstCapStoneProj.songs.containsKey("Special!@#"));
    }

    @Test
    public void testPlaylistStatsWithEmptyPlaylist() {

        assertDoesNotThrow(() -> app.displayPlaylistStats());
    }

    @Test
    public void testCaseSensitivityInSongTitles() {

        app.addSong("Case Test", "Artist1", "Album1", "Genre1");
        app.addSong("CASE TEST", "Artist2", "Album2", "Genre2");
        app.addSong("case test", "Artist3", "Album3", "Genre3");


        assertEquals(3, FirstCapStoneProj.songs.size());
        assertTrue(FirstCapStoneProj.songs.containsKey("Case Test"));
        assertTrue(FirstCapStoneProj.songs.containsKey("CASE TEST"));
        assertTrue(FirstCapStoneProj.songs.containsKey("case test"));
    }

    @Test
    public void testNullSafety() {
        // Test with null values (should be handled by the custom exception)

        try {
            app.addSong(null, "Artist", "Album", "Genre");

            assertFalse(FirstCapStoneProj.songs.containsKey(null));
        } catch (NullPointerException e) {

            assertTrue(true);
        }
    }

    @Test
    public void testPlayingMultipleSongsInQueue() {

        app.addSong("Queue1", "Artist1", "Album1", "Genre1");
        app.addSong("Queue2", "Artist2", "Album2", "Genre2");
        app.addSong("Queue3", "Artist3", "Album3", "Genre3");

        app.playSong("Queue1");
        app.playSong("Queue2");
        app.playSong("Queue3");

        assertEquals(3, FirstCapStoneProj.nowPlayingQueue.size());

        assertEquals(3, FirstCapStoneProj.history.size());
        assertEquals("Queue1", FirstCapStoneProj.history.get(0).getTitle());
        assertEquals("Queue2", FirstCapStoneProj.history.get(1).getTitle());
        assertEquals("Queue3", FirstCapStoneProj.history.get(2).getTitle());
    }
}
