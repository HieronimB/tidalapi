package com.hadas.krzysztof.items;

import com.hadas.krzysztof.session.Session;
import com.hadas.krzysztof.testutils.Credentials;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemsTest {

    private static final String TEST_ARTIST_NAME = "Lao Che";
    private static final String TEST_ALBUM_TITLE = "Dzieciom";
    private static final String TEST_TRACK_TITLE = "Wojenka";
    private static final String TEST_PLAYLIST_NAME = "TIDAL LOVE";
    private static final String TEST_ARTIST_ID = "4343346";
    private static final String TEST_ALBUM_ID = "42928164";
    private static final String TEST_TRACK_ID = "42928167";
    private static final String TEST_PLAYLIST_ID = "b259317b-bb5b-4e28-b89f-ec1f953a0a8b";

    Items items;

    @Before
    public void setup() throws UnirestException {
        Session session = Session.login(Credentials.USERNAME, Credentials.PASSWORD);
        items = new Items(session);
    }

    @Test
    public void shouldBeAbleToGetAlbum() throws UnirestException {
        assertEquals(TEST_ALBUM_TITLE, items.getAlbum(TEST_ALBUM_ID).getTitle());
    }

    @Test
    public void shouldBeAbleToGetArtist() throws UnirestException {
        assertEquals(TEST_ARTIST_NAME, items.getArtist(TEST_ARTIST_ID).getName());
    }

    @Test
    public void shouldBeAbleToGetTrack() throws UnirestException {
        assertEquals(TEST_TRACK_TITLE, items.getTrack(TEST_TRACK_ID).getTitle());
    }

    @Test
    public void shouldBeAbleToGetPlaylist() throws UnirestException {
        assertEquals(TEST_PLAYLIST_NAME, items.getPlaylist(TEST_PLAYLIST_ID).getTitle());
    }
}