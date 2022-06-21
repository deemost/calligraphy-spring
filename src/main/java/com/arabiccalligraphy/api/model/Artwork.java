package com.arabiccalligraphy.api.model;

import com.arabiccalligraphy.api.DAO.ArtworkDAO;

import java.util.Arrays;

public class Artwork {

    private String title;
    private String[] tags;
    private String image;
    private Artist artist;
    private int width;
    private int height;
    private String id;

    public Artwork (String id, String title, String[] tags, String image, Artist artist, int width, int height){
        this.title = title;
        this.tags = tags;
        this.image = image;
        this.artist = artist;
        this.width = width;
        this.height = height;
        this.id = id;
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

    public int getWidth() {return width;}

    public void setWidth(int width) {this.width = width;}

    public int getHeight() {return height;}

    public void setHeight(int height) {this.height = height;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    @Override
    public String toString() {
        return "Artwork{" +
                "title='" + title + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", image='" + image + '\'' +
                ", artist=" + artist +
                ", width=" + width +
                ", height=" + height +
                ", id='" + id + '\'' +
                '}';
    }
}
