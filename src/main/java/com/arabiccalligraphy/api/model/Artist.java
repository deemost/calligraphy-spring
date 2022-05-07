package com.arabiccalligraphy.api.model;

import java.util.List;

public class Artist {

    private String name;
    private List<Artwork> artwork;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artwork> getArtwork() {
        return artwork;
    }

    public void setArtwork(List<Artwork> artwork) {
        this.artwork = artwork;
    }
}
