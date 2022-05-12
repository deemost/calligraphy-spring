package com.arabiccalligraphy.api.model;

import com.arabiccalligraphy.api.DAO.ArtworkDAO;

public class Artwork {

    private String title;
    private String[] tags;
    private String image;
    private Artist artist;

    public Artwork (String title, String[] tags, String image, Artist artist){
        this.title = title;
        this.tags = tags;
        this.image = image;
        this.artist = artist;
    }

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
