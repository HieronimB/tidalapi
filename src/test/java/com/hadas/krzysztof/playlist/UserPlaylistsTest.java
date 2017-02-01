package com.hadas.krzysztof.playlist;

import com.hadas.krzysztof.session.Session;
import com.hadas.krzysztof.testutils.Credentials;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class UserPlaylistsTest {

    private static final String TEST_PLAYLIST_TITLE = "Test title";
    private static final String TEST_PLAYLIST_DESC = "Test description";
    private static final String TEST_TRACK_ID = "42928167";

    private UserPlaylists userPlaylists;

    @Before
    public void setup() throws UnirestException {
        Session session = Session.login(Credentials.USERNAME, Credentials.PASSWORD);
        userPlaylists = new UserPlaylists(session);
    }

    @Test
    public void shouldBeAbleToGetPlaylists() throws UnirestException {
        assertTrue(userPlaylists.getUserPlaylists().size() > 0);
    }

    @Test
    public void shouldBeAbleToCreateAndDeletePlaylist() throws UnirestException {
        int size = userPlaylists.getUserPlaylists().size();
        com.hadas.krzysztof.models.Playlist playlist = userPlaylists.createPlaylist(TEST_PLAYLIST_TITLE, TEST_PLAYLIST_DESC);
        assertEquals(TEST_PLAYLIST_TITLE, playlist.getTitle());
        assertEquals(TEST_PLAYLIST_DESC, playlist.getDescription());
        assertEquals(size + 1, userPlaylists.getUserPlaylists().size());
        userPlaylists.deletePlaylist(playlist.getUuid());
        assertEquals(size, userPlaylists.getUserPlaylists().size());
    }

    @Test
    public void shouldBeAbleToAddAndRemoveTrackFromPlaylist() throws UnirestException {
        com.hadas.krzysztof.models.Playlist playlist = userPlaylists.createPlaylist(TEST_PLAYLIST_TITLE, TEST_PLAYLIST_DESC);
        userPlaylists.addTrackToPlaylist(Collections.singletonList(TEST_TRACK_ID), playlist.getUuid());
        assertEquals(java.util.Optional.of(1L), userPlaylists.getUserPlaylists().stream()
                .filter(p -> p.getTitle().equals(TEST_PLAYLIST_TITLE))
                .findFirst().map(com.hadas.krzysztof.models.Playlist::getNumberOfTracks));
        userPlaylists.deleteTrackFromPlaylist(playlist.getUuid(), 0);
        assertEquals(java.util.Optional.of(0L), userPlaylists.getUserPlaylists().stream()
                .filter(p -> p.getTitle().equals(TEST_PLAYLIST_TITLE))
                .findFirst().map(com.hadas.krzysztof.models.Playlist::getNumberOfTracks));
        userPlaylists.deletePlaylist(playlist.getUuid());
    }

}