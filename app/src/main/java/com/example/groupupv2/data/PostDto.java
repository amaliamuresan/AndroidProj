package com.example.groupupv2.data;

public class PostDto {
    private final String autorID;
    private final int image;
    private final String data;
    private final String description;
    private final String domain;

    public PostDto(String autorID, int image, String data, String description, String domain) {
        this.autorID = autorID;
        this.image = image;
        this.data = data;
        this.description = description;
        this.domain = domain;
    }

    public String getAutorID() {
        return autorID;
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
