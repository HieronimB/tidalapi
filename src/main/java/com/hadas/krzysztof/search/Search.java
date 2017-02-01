package com.hadas.krzysztof.search;

import com.hadas.krzysztof.models.*;
import com.hadas.krzysztof.session.Session;
import com.hadas.krzysztof.utils.RestHelper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.util.List;

public class Search {

    private Session currentSession;
    private RestHelper restHelper;

    public Search(Session currentSession) {
        this(currentSession, new RestHelper());
    }

    public Search(Session currentSession, RestHelper restHelper) {
        this.currentSession = currentSession;
        this.restHelper = restHelper;
    }

    public List<Track> searchTrack(String query) {
        return search(query, "TRACKS").getTracks().getItems();
    }

    public List<Album> searchAlbum(String query) {
        return search(query, "ALBUMS").getAlbums().getItems();
    }

    public List<Playlist> searchPlaylist(String query) {
        return search(query, "PLAYLISTS").getPlaylists().getItems();
    }

    public List<Artist> searchArtist(String query) {
        return search(query, "ARTISTS").getArtists().getItems();
    }

    public SearchResult search(String query, String types) {
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(currentSession.get("search")
                .queryString("query", query)
                .queryString("limit", "100")
                .queryString("offset", 0)
                .queryString("types", types));
        return restHelper.checkAndDeserialize(jsonResponse, SearchResult.class);
    }
}
