package com.hadas.krzysztof;

import com.hadas.krzysztof.models.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public interface TidalApi {

    /**
     * login function should called at the start of the program
     * in order to use this API in context of logged account.
     *
     * @param username - Tidal account username
     * @param password - Tidal account password
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void login(String username, String password);

    /**
     * Add new track to playlist
     *
     * @param trackId - id of track you want to add to playlist
     * @param playlistId - id of playlist you want to add track to
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void addTrackToPlaylist(List<String> trackId, String playlistId);

    /**
     * @return - logged user playlists.
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    List<Playlist> getUserPlaylists();

    /**
     *
     * @param title - new playlist title
     * @param description - new playlist description
     * @return created playlist
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    Playlist createPlaylist(String title, String description);

    /**
     * Delete playlist from logged account
     * @param playlistId - playlist id to delete
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void deletePlaylist(String playlistId);

    /**
     * Delete track from playlist
     * @param playlistId - playlist from which track will be deleted
     * @param index - index of track to delete
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void deleteTrackFromPlaylist(String playlistId, int index);

    /**
     * Search for track
     * @param query - Query to use for searching track
     * @return List of found tracks
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    List<Track> searchTrack(String query);

    /**
     * Search for album
     * @param query - Query to use for searching album
     * @return List of found albums
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    List<Album> searchAlbum(String query);

    /**
     * Search for playlist
     * @param query - Query to use for searching playlist
     * @return List of found playlists
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    List<Playlist> searchPlaylist(String query);

    /**
     * Search for artist
     * @param query - Query to use for searching artist
     * @return List of found artists
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    List<Artist> searchArtist(String query);

    /**
     * Generic search function, you can pass more than one type
     * eg. track,album,artist
     * @param query - Query to use for searching
     * @return Search result with aggregated found items
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    SearchResult search(String query, String types);

    /**
     * Get album by id
     * @param albumId - album id to retrieve
     * @return album with given id
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    Album getAlbum(String albumId);

    /**
     * Get artist by id
     * @param artistId - artist id to retrieve
     * @return artist with given id
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    Artist getArtist(String artistId);

    /**
     * Get track by id
     * @param trackId - track id to retrieve
     * @return track with given id
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    Track getTrack(String trackId);

    /**
     * Get playlist by id
     * @param playlistId - playlist id to retrieve
     * @return playlist with given id
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    Playlist getPlaylist(String playlistId);

    /**
     * Get logged account favorites albums
     * @return List of favorites albums
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    List<Album> getFavoriteAlbums();

    /**
     * Get logged account favorites artists
     * @return List of favorites artists
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    List<Artist> getFavoriteArtists();

    /**
     * Get logged account favorites playlists
     * @return List of favorites playlists
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    List<Playlist> getFavoritePlaylists();

    /**
     * Get logged account favorites tracks
     * @return List of favorites tracks
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    List<Track> getFavoriteTracks();

    /**
     * Add album to favorites
     * @param albumId album id to add to favorites
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void addAlbumToFavorite(String albumId);

    /**
     * Add artist to favorites
     * @param artistId artist id to add to favorites
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void addArtistToFavorite(String artistId);

    /**
     * Add track to favorites
     * @param trackId track id to add to favorites
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void addTrackToFavorite(String trackId) ;

    /**
     * Add playlist to favorites
     * @param playlistId playlist id to add to favorites
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void addPlaylistToFavorite(String playlistId);

    /**
     * Remove track from favorites
     * @param trackId track id to be removed from favorites
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void removeTrackFromFavorites(String trackId);

    /**
     * Remove album from favorites
     * @param albumId album id to be removed from favorites
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void removeAlbumFromFavorites(String albumId);

    /**
     * Remove artist from favorites
     * @param artistId artist id to be removed from favorites
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void removeArtistFromFavorites(String artistId);

    /**
     * Remove playlist from favorites
     * @param playlistId playlist id to be removed from favorites
     * @throws com.hadas.krzysztof.exceptions.UncheckedUnirestException
     */
    void removePlaylistFromFavorites(String playlistId);
}
