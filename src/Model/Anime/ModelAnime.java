/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Anime;

import Model.Universal;

/**
 *
 * @author BINTORO
 */
public class ModelAnime extends Universal{
    private int id;
    private String title;
    private String imageUrl;
    private String synopsis;
    private double rating;

    // Getters and setters
    public int getId() {
        return id; 
    }
    public void setId(int id) {
        this.id = id; 
    }

    public String getTitle() {
        return title; 
    }
    public void setTitle(String title) {
        this.title = title; 
    }

    public String getImageUrl() {
        return imageUrl; 
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl; 
    }

    public double getRating() {
        return rating; 
    }
    public void setRating(double rating) {
        this.rating = rating; 
    }
    
    public String getSynopsis() {
        return synopsis; 
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis; 
    }
}
