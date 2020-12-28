package com.example.groupupv2.domain;

import com.example.groupupv2.domain.Post;

import java.util.ArrayList;

public class User {

    private String email;
    private String name;
    private String username;
    private ArrayList<Post> posts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User()
    {

    }

    public User(String email, String name, String username) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.posts = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
