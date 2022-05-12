package com.arabiccalligraphy.api.DAO;

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
import java.util.List;

@Service
public class ArtworkDAO {
    private List<Artwork> artworks = new ArrayList<>();

    public List<Artwork> getArtworks() {
        return artworks;
    }

    @PostConstruct
    public void loadArtwork() throws IOException {

        File file = ResourceUtils.getFile("classpath:data/index.json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(file);

        ArrayNode contentNode = (ArrayNode) root.get("content");
        for (JsonNode contentItem : contentNode) {
            String title = contentItem.get("nameAr").asText();
            if (title.equals("null")) {
                title = contentItem.get("name").asText();
            }
            String image = contentItem.get("imgSrc").asText();

            ArrayNode tagsNode = (ArrayNode) contentItem.get("tags");
            String[] tags = new String[tagsNode.size()];

            for (int i = 0; i < tagsNode.size(); i++) {
                tags[i] = tagsNode.get(i).asText();
            }

            Artwork artwork = new Artwork(title, tags, image, null);
            artworks.add(artwork);
        }

        System.out.println(artworks);
    }

}
