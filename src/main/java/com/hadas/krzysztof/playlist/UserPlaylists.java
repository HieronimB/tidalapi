package com.hadas.krzysztof.playlist;

import com.hadas.krzysztof.models.Playlist;
import com.hadas.krzysztof.models.Result;
import com.hadas.krzysztof.session.Session;
import com.hadas.krzysztof.utils.RestHelper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.owlike.genson.GenericType;

import java.util.List;

public class UserPlaylists {

    private static final String ETAG = "ETag";

    private Session currentSession;
    private RestHelper restHelper;

    public UserPlaylists(Session currentSession) {
        this(currentSession, new RestHelper());
    }

    public UserPlaylists(Session currentSession, RestHelper restHelper) {
        this.currentSession = currentSession;
        this.restHelper = restHelper;
    }

    public void addTrackToPlaylist(List<String> trackId, String playlistId) {
        String etag = getPlaylistEtag(playlistId);
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(
                currentSession.post("playlists/" + playlistId + "/items")
                              .header("If-None-Match", etag)
                              .field("trackIds", String.join(",", trackId))
                              .field("toIndex", "0"));
        restHelper.checkResponseStatus(jsonResponse);
    }

    public List<Playlist> getUserPlaylists() {
        HttpResponse<JsonNode> jsonResponse =
                restHelper.executeRequest(currentSession.get("users/" + currentSession.getUserId() + "/playlists"));
        return restHelper.checkAndDeserialize(jsonResponse, new GenericType<Result<Playlist>>(){}).getItems();
    }

    public Playlist createPlaylist(String title, String description) {
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(
                currentSession.post("users/" + currentSession.getUserId() + "/playlists")
                              .field("title", title)
                              .field("description", description));
        return restHelper.checkAndDeserialize(jsonResponse, Playlist.class);
    }

    public void deletePlaylist(String playlistId) {
        HttpResponse<JsonNode> jsonResponse =
                restHelper.executeRequest(currentSession.delete("playlists/" + playlistId));
        restHelper.checkResponseStatus(jsonResponse);
    }

    public void deleteTrackFromPlaylist(String playlistId, int index) {
        String etag = getPlaylistEtag(playlistId);
        HttpResponse<JsonNode> jsonResponse =
                restHelper.executeRequest(currentSession.delete("playlists/" + playlistId + "/items/" + index)
                .header("If-None-Match", etag));
        restHelper.checkResponseStatus(jsonResponse);
    }

    private String getPlaylistEtag(String playlistId)  {
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(currentSession.post("playlists/" + playlistId));
        restHelper.checkResponseStatus(jsonResponse);
        return jsonResponse.getHeaders().get(ETAG).get(0);
    }
}
