package com.arabiccalligraphy.api.controller;

import com.arabiccalligraphy.api.DAO.ArtworkDAO;
import com.arabiccalligraphy.api.model.Artwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtworkController {

    private static final Logger logger = LoggerFactory.getLogger(ArtworkController.class);

    private final ArtworkDAO artworkDAO;

    public ArtworkController(ArtworkDAO artworkDAO) {
        this.artworkDAO = artworkDAO;
    }

    @GetMapping("/api/v1/artworks")
    public ResponseEntity<List<Artwork>> getAllArtworks(@RequestParam(required = false) String artist,
                                                        @RequestParam(required = false) Integer page,
                                                        @RequestParam(required = false) Integer pageSize) {

        if (artist == null) {
            artist = "index";
        }

        List<Artwork> result = artworkDAO.getArtworks().get(artist + ".json");

        if (result == null) {
            logger.debug("Found 0 artwork for artist: {}, pageSize: {}, page: {}", artist, pageSize, page);
            return new ResponseEntity<List<Artwork>>(HttpStatus.NOT_FOUND);
        }

        if ((pageSize != null) && (page != null)) {
            int start = Math.min(page * pageSize, result.size());
            int end = Math.min(start + pageSize, result.size());

            List<Artwork> pagedResult = result.subList(start, end);
            logger.debug("Found {} paged artwork for artist: {}, pageSize: {}, page: {}", pagedResult.size(), artist, pageSize, page);

            return new ResponseEntity<List<Artwork>>(pagedResult, HttpStatus.OK);
        }

        logger.debug("Found {} total artwork for artist: {}", result.size(), artist);
        return new ResponseEntity<List<Artwork>>(result, HttpStatus.OK);
    }

}
