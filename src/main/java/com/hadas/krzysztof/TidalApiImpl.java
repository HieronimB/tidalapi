package com.hadas.krzysztof;

import com.hadas.krzysztof.favorites.Favorites;
import com.hadas.krzysztof.items.Items;
import com.hadas.krzysztof.models.*;
import com.hadas.krzysztof.playlist.UserPlaylists;
import com.hadas.krzysztof.search.Search;
import com.hadas.krzysztof.session.Session;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public class TidalApiImpl implements TidalApi {
    private Favorites favorites;
    private Search search;
    private UserPlaylists userPlaylists;
    private Items items;

    public void login(String username, String password) {
        Session currentSession = Session.login(username, password);
        favorites = new Favorites(currentSession);
        search = new Search(currentSession);
        userPlaylists = new UserPlaylists(currentSession);
        items = new Items(currentSession);
    }

    public void addTrackToPlaylist(List<String> trackId, String playlistId) {
        userPlaylists.addTrackToPlaylist(trackId, playlistId);
    }

    public List<Playlist> getUserPlaylists() {
        return userPlaylists.getUserPlaylists();
    }

    public Playlist createPlaylist(String title, String description) {
        return userPlaylists.createPlaylist(title, description);
    }

    public void deletePlaylist(String playlistId) {
        userPlaylists.deletePlaylist(playlistId);
    }

    public void deleteTrackFromPlaylist(String playlistId, int index) {
        userPlaylists.deleteTrackFromPlaylist(playlistId, index);
    }

    public List<Track> searchTrack(String query) {
        return search.searchTrack(query);
    }

    public List<Album> searchAlbum(String query) {
        return search.searchAlbum(query);
    }

    public List<Playlist> searchPlaylist(String query) {
        return search.searchPlaylist(query);
    }

    public List<Artist> searchArtist(String query) {
        return search.searchArtist(query);
    }

    public SearchResult search(String query, String types) {
        return search.search(query, types);
    }

    public Album getAlbum(String albumId) {
        return items.getAlbum(albumId);
    }

    public Artist getArtist(String artistId) {
        return items.getArtist(artistId);
    }

    public Track getTrack(String trackId) {
        return items.getTrack(trackId);
    }

    public Playlist getPlaylist(String playlistId) {
        return items.getPlaylist(playlistId);
    }

    public List<Album> getFavoriteAlbums() {
        return favorites.getFavoriteAlbums();
    }

    public List<Artist> getFavoriteArtists() {
        return favorites.getFavoriteArtists();
    }

    public List<Playlist> getFavoritePlaylists() {
        return favorites.getFavoritePlaylists();
    }

    public List<Track> getFavoriteTracks() {
        return favorites.getFavoriteTracks();
    }

    public void addAlbumToFavorite(String albumId) {
        favorites.addAlbumToFavorite(albumId);
    }

    public void addArtistToFavorite(String artistId) {
        favorites.addArtistToFavorite(artistId);
    }

    public void addTrackToFavorite(String trackId)  {
        favorites.addTrackToFavorite(trackId);
    }

    public void addPlaylistToFavorite(String playlistId) {
        favorites.addPlaylistToFavorite(playlistId);
    }

    public void removeTrackFromFavorites(String trackId) {
        favorites.removeTrackFromFavorites(trackId);
    }

    public void removeAlbumFromFavorites(String albumId) {
        favorites.removeAlbumFromFavorites(albumId);
    }

    public void removeArtistFromFavorites(String artistId) {
        favorites.removeArtistFromFavorites(artistId);
    }

    public void removePlaylistFromFavorites(String playlistId) {
        favorites.removePlaylistFromFavorites(playlistId);
    }
}
