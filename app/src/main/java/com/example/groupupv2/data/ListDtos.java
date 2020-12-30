package com.example.groupupv2.data;

import com.example.groupupv2.domain.Post;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListDtos {
    @SerializedName("postt")
    public  List<PostDto> dtosList = new ArrayList<>();
}
