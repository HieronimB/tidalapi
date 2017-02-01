package com.hadas.krzysztof.items;

import com.hadas.krzysztof.models.Album;
import com.hadas.krzysztof.models.Artist;
import com.hadas.krzysztof.models.Playlist;
import com.hadas.krzysztof.models.Track;
import com.hadas.krzysztof.session.Session;
import com.hadas.krzysztof.utils.RestHelper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

public class Items {

    private Session currentSession;
    private RestHelper restHelper;

    public Items(Session currentSession) {
        this(currentSession, new RestHelper());
    }

    public Items(Session currentSession, RestHelper restHelper) {
        this.currentSession = currentSession;
        this.restHelper = restHelper;
    }

    public Album getAlbum(String albumId) {
        return getItem(albumId, "album", Album.class);
    }

    public Artist getArtist(String artistId){
        return getItem(artistId, "artist", Artist.class);
    }

    public Track getTrack(String trackId) {
        return getItem(trackId, "track", Track.class);
    }

    public Playlist getPlaylist(String playlistId) {
        return getItem(playlistId, "playlist", Playlist.class);
    }

    private <T> T getItem(String itemId, String itemType, Class<T> clazz) {
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(currentSession.get(itemType + "s/" + itemId)
                .queryString("limit", "999"));
        return restHelper.checkAndDeserialize(jsonResponse, clazz);
    }
}
