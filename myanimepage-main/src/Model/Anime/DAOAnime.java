/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Anime;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BINTORO
 */
public class DAOAnime implements InterfaceDAOAnime{
    public List<ModelAnime> fetchTopAnime(int page) {
        List<ModelAnime> animeList = new ArrayList<>();
        try {
            // URI ke URL
            URI uri = new URI("https://api.jikan.moe/v4/anime?page=" + page);
            URL url = uri.toURL(); 
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            JsonElement root = JsonParser.parseReader(new InputStreamReader(connection.getInputStream()));
            JsonObject jsonObj = root.getAsJsonObject();
            JsonArray data = jsonObj.getAsJsonArray("data");
            
            for(JsonElement animeElement : data){
                JsonObject animeObj = animeElement.getAsJsonObject();
                ModelAnime anime = new ModelAnime();
                
                anime.setId(animeObj.get("mal_id").getAsInt());
                anime.setTitle(animeObj.get("title").getAsString());
                anime.setImageUrl(animeObj.getAsJsonObject("images")
                        .getAsJsonObject("jpg")
                        .get("image_url").getAsString()
                );
                anime.setRating(animeObj.has("score") && !animeObj.get("score").isJsonNull()
                    ? animeObj.get("score").getAsDouble()
                    : 0.0);
                animeList.add(anime);
                anime.setSynopsis(animeObj.get("synopsis").getAsString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animeList;
    }
    
    public ModelAnime fetchAnimeById(int id) {
        ModelAnime anime = null;
        try {
            URI uri = new URI("https://api.jikan.moe/v4/anime/" + id);
            URL url = uri.toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            JsonElement root = JsonParser.parseReader(new InputStreamReader(connection.getInputStream()));
            JsonObject jsonObj = root.getAsJsonObject().getAsJsonObject("data");

            anime = new ModelAnime();
            anime.setId(jsonObj.get("mal_id").getAsInt());
            anime.setTitle(jsonObj.get("title").getAsString());
            anime.setImageUrl(jsonObj.getAsJsonObject("images")
                    .getAsJsonObject("jpg")
                    .get("image_url").getAsString());
            anime.setRating(jsonObj.has("score") && !jsonObj.get("score").isJsonNull()
                    ? jsonObj.get("score").getAsDouble()
                    : 0.0);
            anime.setSynopsis(jsonObj.has("synopsis") && !jsonObj.get("synopsis").isJsonNull()
                    ? jsonObj.get("synopsis").getAsString()
                    : "No synopsis available.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return anime;
    }
    
    public List<ModelAnime> searchAnime(String query) {
    List<ModelAnime> animeList = new ArrayList<>();
    try {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        URI uri = new URI("https://api.jikan.moe/v4/anime?q=" + encodedQuery);
        URL url = uri.toURL();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        JsonElement root = JsonParser.parseReader(new InputStreamReader(connection.getInputStream()));
        JsonObject jsonObj = root.getAsJsonObject();
        JsonArray data = jsonObj.getAsJsonArray("data");

        for (JsonElement animeElement : data) {
            JsonObject animeObj = animeElement.getAsJsonObject();
            ModelAnime anime = new ModelAnime();

            anime.setId(animeObj.get("mal_id").getAsInt());
            anime.setTitle(animeObj.get("title").getAsString());
            anime.setImageUrl(animeObj.getAsJsonObject("images")
                    .getAsJsonObject("jpg")
                    .get("image_url").getAsString());
            anime.setRating(animeObj.has("score") && !animeObj.get("score").isJsonNull()
                    ? animeObj.get("score").getAsDouble()
                    : 0.0);
            anime.setSynopsis(animeObj.has("synopsis") && !animeObj.get("synopsis").isJsonNull()
                    ? animeObj.get("synopsis").getAsString()
                    : "No synopsis available.");

            animeList.add(anime);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return animeList;
}
}
