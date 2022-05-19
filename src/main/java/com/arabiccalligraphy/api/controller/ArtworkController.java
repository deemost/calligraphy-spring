package com.arabiccalligraphy.api.controller;

import com.arabiccalligraphy.api.DAO.ArtworkDAO;
import com.arabiccalligraphy.api.model.Artwork;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ArtworkController {

    private final ArtworkDAO artworkDAO;

    public ArtworkController(ArtworkDAO artworkDAO){
        this.artworkDAO = artworkDAO;
    }

    @GetMapping("/api/v1/artworks")
    public ResponseEntity<List<Artwork>> getAllArtworks(@RequestParam(required = false) String artist) {
        if(artist == null){
            return new ResponseEntity<List<Artwork>>(artworkDAO.getArtworks().get("index.json"), HttpStatus.OK);
        }


        List<Artwork> result = artworkDAO.getArtworks().get(artist + ".json");

        if(result == null){
            return new ResponseEntity<List<Artwork>>( HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Artwork>>(result, HttpStatus.OK);
    }

}
