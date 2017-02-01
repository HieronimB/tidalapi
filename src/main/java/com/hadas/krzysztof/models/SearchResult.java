package com.hadas.krzysztof.models;

public class SearchResult {
    private Result<Artist> artists;
    private Result<Album> albums;
    private Result<Track> tracks;
    private Result<Playlist> playlists;

    public Result<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Result<Artist> artists) {
        this.artists = artists;
    }

    public Result<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Result<Album> albums) {
        this.albums = albums;
    }

    public Result<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Result<Track> tracks) {
        this.tracks = tracks;
    }

    public Result<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Result<Playlist> playlists) {
        this.playlists = playlists;
    }
}
