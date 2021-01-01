package com.example.groupupv2.data.remote;

import androidx.annotation.NonNull;

import com.example.groupupv2.data.PostDto;
import com.example.groupupv2.domain.PostRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class PostDataSource implements PostRepository {
    private final PostsAPI api;
    static List<PostDto> dtoList = new ArrayList<>();

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref = database.child("posts");


    public PostDataSource(PostsAPI api) {
        this.api = api;
    }


    /*@Override
    public ListDtos getItems() {
        Timber.d("getItem() called");
        try {
            response = api.getItems().execute();
            return response.body();
        } catch (IOException e) {
            Timber.tag("PostDataSource").w("getItems failed");
            return null;
        }
    }*/

    public List<PostDto> getItems() {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                dtoList = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    PostDto post = ds.getValue(PostDto.class);
                    System.out.println(post.getDescription());
                    dtoList.add(post);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Timber.w("Get posts failed");
            }
        });

        System.out.println(dtoList.toString() + " 2");
        return dtoList;
    }

    @Override
    public void postItem(PostDto post) {
        try {
            api.postItem(post).execute();
            //api.postItem(3, post).execute();
        } catch (Exception e) {
            Timber.w("PostItem(Post post) failed");
        }
    }
}
