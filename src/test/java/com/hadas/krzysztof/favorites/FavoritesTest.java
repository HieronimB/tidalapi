package com.hadas.krzysztof.favorites;

import com.hadas.krzysztof.session.Session;
import com.hadas.krzysztof.testutils.Credentials;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FavoritesTest {

    private static final String TEST_FAVORITE_ALBUM = "68053196";
    private static final String TEST_FAVORITE_PLAYLIST = "8fba833d-e395-4f06-a3eb-4a60959ef5c7";
    private static final String TEST_FAVORITE_ARTIST = "4066469";
    private static final String TEST_FAVORITE_TRACK = "68161143";

    private Favorites favorites;

    @Before
    public void setup() throws UnirestException {
        Session session = Session.login(Credentials.USERNAME, Credentials.PASSWORD);
        favorites = new Favorites(session);
    }

    @Test
    public void shouldBeAbleToGetFavoriteAlbums() throws UnirestException {
        assertNotNull(favorites.getFavoriteAlbums());
    }

    @Test
    public void shouldBeAbleToGetFavoriteArtists() throws UnirestException {
        assertNotNull(favorites.getFavoriteArtists());
    }

    @Test
    public void shouldBeAbleToGetFavoritePlaylists() throws UnirestException {
        assertNotNull(favorites.getFavoritePlaylists());
    }

    @Test
    public void shouldBeAbleToGetFavoriteTracks() throws UnirestException {
        assertNotNull(favorites.getFavoriteTracks());
    }

    @Test
    public void shouldBeAbleToAddAndRemoveAlbumToFavorites() throws UnirestException {
        int size = favorites.getFavoriteAlbums().size();
        favorites.addAlbumToFavorite(TEST_FAVORITE_ALBUM);
        assertEquals(size + 1, favorites.getFavoriteAlbums().size());
        favorites.removeAlbumFromFavorites(TEST_FAVORITE_ALBUM);
        assertEquals(size, favorites.getFavoriteAlbums().size());
    }

    @Test
    public void shouldBeAbleToAddAndRemovePlaylistToFavorites() throws UnirestException {
        int size = favorites.getFavoritePlaylists().size();
        favorites.addPlaylistToFavorite(TEST_FAVORITE_PLAYLIST);
        assertEquals(size + 1, favorites.getFavoritePlaylists().size());
        favorites.removePlaylistFromFavorites(TEST_FAVORITE_PLAYLIST);
        assertEquals(size, favorites.getFavoritePlaylists().size());
    }

    @Test
    public void shouldBeAbleToAddAndRemoveArtistToFavorites() throws UnirestException {
        int size = favorites.getFavoriteArtists().size();
        favorites.addArtistToFavorite(TEST_FAVORITE_ARTIST);
        assertEquals(size + 1, favorites.getFavoriteArtists().size());
        favorites.removeArtistFromFavorites(TEST_FAVORITE_ARTIST);
        assertEquals(size, favorites.getFavoriteArtists().size());
    }

    @Test
    public void shouldBeAbleToAddAndRemoveTrackInFavorites() throws UnirestException {
        int size = favorites.getFavoriteTracks().size();
        favorites.addTrackToFavorite(TEST_FAVORITE_TRACK);
        assertEquals(size + 1, favorites.getFavoriteTracks().size());
        favorites.removeTrackFromFavorites(TEST_FAVORITE_TRACK);
        assertEquals(size, favorites.getFavoriteTracks().size());
    }
}