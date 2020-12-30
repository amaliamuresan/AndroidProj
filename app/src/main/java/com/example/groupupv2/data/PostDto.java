package com.example.groupupv2.data;

import com.google.gson.annotations.SerializedName;

public class PostDto {
    private final String authorName;
    private final int image;

    @SerializedName("timestamp")
    private final String data;
    private final int id;
    private final String description;
    private final String domain;

    public int getId() {
        return id;
    }

    public PostDto() {
        this.authorName = "";
        this.image = 0;
        this.data = "";
        this.description = "";
        this.domain = "";
        this.id = 0;
    }

    public PostDto(String authorName, int image, String data, String description, String domain, int id1) {
        this.authorName = authorName;
        this.image = image;
        this.data = data;
        this.description = description;
        this.domain = domain;
        this.id = id1;
    }

    public String getAutorID() {
        return authorName;
    }

    public int getImage() {
        return image;
    }

    public String getData() {
        return data;
    }

    public String getDescription() {
        return description;
    }

    public String getDomain() {
        return domain;
    }
}
