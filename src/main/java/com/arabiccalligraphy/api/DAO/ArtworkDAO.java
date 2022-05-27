package com.arabiccalligraphy.api.DAO;

import com.arabiccalligraphy.api.model.Artist;
import com.arabiccalligraphy.api.model.Artwork;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArtworkDAO {
    private Map<String, List<Artwork>> artworks = new HashMap<>();

    public Map<String, List<Artwork>> getArtworks() {
        return artworks;
    }

    @PostConstruct
    public void loadArtwork() throws IOException {

        artworks.put("index.json", new ArrayList<>());
        artworks.put("kamel.json", new ArrayList<>());
        artworks.put("mokhtar.json", new ArrayList<>());

        loadArtworkFromFile("index.json");
        loadArtworkFromFile("kamel.json");
        loadArtworkFromFile("mokhtar.json");
    }


    private void loadArtworkFromFile(String fileName) throws IOException {

        File file = ResourceUtils.getFile("classpath:data/" + fileName);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(file);

        ArrayNode contentNode = (ArrayNode) root.get("content");
        for (JsonNode contentItem : contentNode) {

            //title creation
            String title = contentItem.get("nameAr").asText();
            if (title.equals("null")) {
                title = contentItem.get("name").asText();
            }

            //image creation
            String image = contentItem.get("imgSrc").asText();

            //tags creation
            ArrayNode tagsNode = (ArrayNode) contentItem.get("tags");
            String[] tags = new String[tagsNode.size()];
            for (int i = 0; i < tagsNode.size(); i++) {
                tags[i] = tagsNode.get(i).asText();
            }

            //Artist creation
            Artist artist = new Artist(contentItem.get("artist").asText());

            //width creation
            int width = contentItem.get("width").asInt();

            //height creation
            int height = contentItem.get("height").asInt();

            //id creation
            String id = contentItem.get("id").asText();

            //artwork created
            Artwork artwork = new Artwork(id, title, tags, image, artist, width, height);

            //artwork added
            artworks.get(fileName).add(artwork);
        }

        System.out.println(artworks);
    }

}
