package com.example.groupupv2.domain;

public class Post {
    private String autorID;
    private int image;
    private String data;
    private String description;
    private String domain;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Post(String autorID, int image, String data, String description, String domain, String title) {
        this.autorID = autorID;
        this.image = image;
        this.data = data;
        this.description = description;
        this.domain = domain;
        this.title = title;
    }

    public String getAutorID() {
        return autorID;
    }

    public void setAutorID(String autorID) {
        this.autorID = autorID;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
