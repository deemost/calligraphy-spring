package com.arabiccalligraphy.api.DAO;

import com.arabiccalligraphy.api.model.Artist;
import com.arabiccalligraphy.api.model.Artwork;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArtworkDAO {

    public List<Artwork> loadArtwork(String page) throws IOException, JSONException {
        List<Artwork> artworks = new ArrayList<>();

        String text = Files.readString(Paths.get(page + ".json"));
        JSONObject object = new JSONObject(text);
        JSONArray array = object.getJSONArray("content");

        for(int i = 0; i < array.length(); i++){

            String name = array.getJSONObject(i).getString("nameAr");

            //String[] tags = array.getJSONObject(i).getString("tags");
            //JSONArray tagArray = array.getJSONObject(i).getJSONArray("tags");
            //String[] tags = new String[tagArray.length()];
            //for(int j = 0; j < tagArray.length(); j++){
            //    tags[j] = tagArray.getJSONObject(j).getString("");
            //}
            JSONArray tagArray = (JSONArray) array.getJSONObject(i).get("cars");
            String[] tags = new String[tagArray.length()];
            for (String t : tagArray.getJSONObject(i).names()) {
                tags[i] = t;
            }

            String image = array.getJSONObject(i).getString("imgSrc");
            Artist artist = new Artist(array.getJSONObject(i).getString("artist"));

            Artwork artwork = new Artwork(name, tags, image, artist);
            artworks.add(artwork);

        }
        return artworks;
    }

}
