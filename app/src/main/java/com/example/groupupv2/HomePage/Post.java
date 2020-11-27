package com.example.groupupv2.HomePage;

import android.media.Image;
import android.widget.ImageView;

import java.text.DateFormat;

public class Post {
    private String autorName;
    private int image;
    private String data;
    private String description;
    private String domain;

    public Post(String autorName, int image, String data, String description, String domain) {
        this.autorName = autorName;
        this.image = image;
        this.data = data;
        this.description = description;
        this.domain = domain;
    }

    public String getAutorName() {
        return autorName;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
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
