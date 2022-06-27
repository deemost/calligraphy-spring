package com.arabiccalligraphy.api.controller;

import com.arabiccalligraphy.api.DAO.ArtworkDAO;
import com.arabiccalligraphy.api.model.Artist;
import com.arabiccalligraphy.api.model.Artwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArtworkController.class)
@ActiveProfiles("test")
public class ArtworkControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtworkDAO artworkDAO;

    @BeforeEach
    void setUp() {
        Artist index = new Artist("index");
        Artwork indexArt1 = new Artwork("1", "Index Artwork Title 1", new String[]{"tag1", "tag2"}, "img/index_art1.jpg", index, 100, 200);
        Artwork indexArt2 = new Artwork("2", "Index Artwork Title 2", new String[]{"tag2", "tag4"}, "img/index_art2.jpg", index, 100, 200);
        Artwork indexArt3 = new Artwork("3", "Index Artwork Title 3", new String[]{"tag4", "tag5"}, "img/index_art3.jpg", index, 100, 200);
        List<Artwork> indexArtworks = new ArrayList<>(Arrays.asList(indexArt1, indexArt2, indexArt3));

        Artist mokhtar = new Artist("mokhtar");
        Artwork mokhtarArt1 = new Artwork("4", "Mokhtar Artwork Title 1", new String[]{"tag1", "tag3"}, "img/mokhtar_art1.jpg", mokhtar, 100, 200);
        Artwork mokhtarArt2 = new Artwork("5", "Mokhtar Artwork Title 2", new String[]{"tag2", "tag3"}, "img/mokhtar_art2.jpg", mokhtar, 100, 200);
        Artwork mokhtarArt3 = new Artwork("6", "Mokhtar Artwork Title 3", new String[]{"tag2", "tag5"}, "img/mokhtar_art3.jpg", mokhtar, 100, 200);
        List<Artwork> mokhtarArtworks = new ArrayList<>(Arrays.asList(mokhtarArt1, mokhtarArt2, mokhtarArt3));

        Map<String, List<Artwork>> mockedArtwork = new HashMap<>();
        mockedArtwork.put("index.json", indexArtworks);
        mockedArtwork.put("mokhtar.json", mokhtarArtworks);

        Mockito.when(artworkDAO.getArtworks()).thenReturn(mockedArtwork);
    }

    @Test
    void testGetArtworkForIndexAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/artworks").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[1].id", is("2")))
                .andExpect(jsonPath("$[2].id", is("3")))
                .andExpect(content().string(containsString("Index Artwork Title")));
    }

    // todo: add more tests here
}
