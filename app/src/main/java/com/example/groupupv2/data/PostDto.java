package com.example.groupupv2.data;

import com.google.gson.annotations.SerializedName;

public class PostDto {
    private String authorName;
    private int image;
    private String timestamp;
    private int id;
    private String description;
    private String domain;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getId() {
        return id;
    }

    public PostDto() {
        this.authorName = "";
        this.image = 0;
        this.timestamp = "";
        this.description = "";
        this.domain = "";
        this.id = 0;
    }

    public PostDto(String authorName, int image, String data, String description, String domain, int id1, String title) {
        this.authorName = authorName;
        this.image = image;
        this.timestamp = data;
        this.description = description;
        this.domain = domain;
        this.id = id1;
        this.title = title;
    }
    public String getAuthorName() {
        return authorName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getAutorID() {
        return authorName;
    }

    public int getImage() {
        return image;
    }

    public String getData() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public String getDomain() {
        return domain;
    }
}
