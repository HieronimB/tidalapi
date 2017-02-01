package com.hadas.krzysztof.models;

import java.util.List;
import java.util.Map;

public class Track {
    private String copyright;
    private Map<String, Object> album;
    private String title;
    private String url;
    private int duration;
    private int popularity;
    private List<Object> artists;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Map<String, Object> getAlbum() {
        return album;
    }

    public void setAlbum(Map<String, Object> album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public List<Object> getArtists() {
        return artists;
    }

    public void setArtists(List<Object> artists) {
        this.artists = artists;
    }
}
