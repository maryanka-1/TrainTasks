package com.example.catsgram.model;

import java.net.URL;
import java.time.LocalDateTime;

public class Post {
    private final String author;
    private final LocalDateTime creationDate = LocalDateTime.now();
    private String urlPhoto;
    private String description;


    public Post(String author, String urlPhoto, String description) {
        this.author = author;
        this.urlPhoto = urlPhoto;
        this.description = description;
    }


    public String getAuthor() {
        return author;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

}
