package com.hadas.krzysztof.search;

import com.hadas.krzysztof.session.Session;
import com.hadas.krzysztof.testutils.Credentials;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SearchTest {

    private static final String TEST_SONG_QUERY = "Lao Che Wojenka";
    private static final String TEST_ALBUM_QUERY = "Lao Che Dzieciom";
    private static final String TEST_PLAYLIST_QUERY = "TIDAL LOVE";
    private static final String TEST_ARTIST_QUERY = "Lao Che";
    private static final String TEST_TRACK_ID = "42928167";
    private static final String TEST_ALBUM_ID = "42928164";
    private static final String TEST_PLAYLIST_ID = "b259317b-bb5b-4e28-b89f-ec1f953a0a8b";
    private static final String TEST_ARTIST_ID = "4343346";

    private Search search;

    @Before
    public void setup() throws UnirestException {
        Session session = Session.login(Credentials.USERNAME, Credentials.PASSWORD);
        search = new Search(session);
    }

    @Test
    public void shouldBeAbleToFindTracks() throws UnirestException {
        assertEquals(TEST_TRACK_ID, search.searchTrack(TEST_SONG_QUERY).get(0).getId().toString());
    }

    @Test
    public void shouldBeAbleToFindAlbums() throws UnirestException {
        assertEquals(TEST_ALBUM_ID, search.searchAlbum(TEST_ALBUM_QUERY).get(0).getId().toString());
    }

    @Test
    public void shouldBeAbleToFindPlaylist() throws UnirestException {
        assertEquals(TEST_PLAYLIST_ID, search.searchPlaylist(TEST_PLAYLIST_QUERY).get(0).getUuid());
    }

    @Test
    public void shouldBeAbleToFindArtists() throws UnirestException {
        assertEquals(TEST_ARTIST_ID, search.searchArtist(TEST_ARTIST_QUERY).get(0).getId().toString());
    }
}