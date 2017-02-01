package com.hadas.krzysztof.favorites;

import com.hadas.krzysztof.models.*;
import com.hadas.krzysztof.session.Session;
import com.hadas.krzysztof.utils.RestHelper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.owlike.genson.GenericType;

import java.util.List;

public class Favorites {

    private Session currentSession;
    private RestHelper restHelper;

    public Favorites(Session currentSession) {
        this(currentSession, new RestHelper());
    }

    public Favorites(Session currentSession, RestHelper restHelper) {
        this.currentSession = currentSession;
        this.restHelper = restHelper;
    }

    public List<Album> getFavoriteAlbums() {
        return getFavorite("albums", new GenericType<Result<Album>>(){});
    }

    public List<Artist> getFavoriteArtists() {
        return getFavorite("artists", new GenericType<Result<Artist>>(){});
    }

    public List<Playlist> getFavoritePlaylists() {
        return getFavorite("playlists", new GenericType<Result<Playlist>>(){});
    }

    public List<Track> getFavoriteTracks() {
        return getFavorite("tracks", new GenericType<Result<Track>>(){});
    }

    private <T> List<T> getFavorite(String favoriteType, GenericType<Result<T>> type) {
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(
                currentSession.get("users/" + currentSession.getUserId() + "/favorites/" + favoriteType)
                              .queryString("limit", "999"));
        return restHelper.checkAndDeserialize(jsonResponse, type).getItems();
    }

    public void addAlbumToFavorite(String albumId) {
        addToFavorite(albumId, "album");
    }

    public void addArtistToFavorite(String artistId) {
        addToFavorite(artistId, "artist");
    }

    public void addTrackToFavorite(String trackId) {
        addToFavorite(trackId, "track");
    }

    public void addPlaylistToFavorite(String playlistId) {
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(
                currentSession.post("users/" + currentSession.getUserId() + "/favorites/playlists")
                              .field("uuid", playlistId));
        restHelper.checkResponseStatus(jsonResponse);
    }

    public void removeTrackFromFavorites(String trackId) {
        removeFromFavorites(trackId, "track");
    }

    public void removeAlbumFromFavorites(String albumId) {
        removeFromFavorites(albumId, "album");
    }

    public void removeArtistFromFavorites(String artistId) {
        removeFromFavorites(artistId, "artist");
    }

    public void removePlaylistFromFavorites(String playlistId) {
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(
                currentSession.delete("users/" + currentSession.getUserId() + "/favorites/playlists/" + playlistId));
        restHelper.checkResponseStatus(jsonResponse);
    }

    private void removeFromFavorites(String itemId, String itemType) {
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(
                currentSession.delete("users/" + currentSession.getUserId() + "/favorites/" + itemType + "s/" + itemId));
        restHelper.checkResponseStatus(jsonResponse);
    }

    private void addToFavorite(String itemId, String itemType) {
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(
                currentSession.post("users/" + currentSession.getUserId() + "/favorites/" + itemType + "s")
                        .field(itemType + "Id", itemId));
        restHelper.checkResponseStatus(jsonResponse);
    }
}
