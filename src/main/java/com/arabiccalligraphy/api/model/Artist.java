package com.arabiccalligraphy.api.model;

import java.util.List;

public class Artist {

    private String name;
    private List<Artwork> artworks;

    public Artist(String name){
        this.name = name;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                '}';
    }
}
