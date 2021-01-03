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
    List<PostDto> dtoList = new ArrayList<>();


    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref = database.child("posts");


    public PostDataSource(PostsAPI api) {
        this.api = api;
    }


    public List<PostDto> getItems(ICallBack iCallBack) {

        List<PostDto> list = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                dtoList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    PostDto post = ds.getValue(PostDto.class);
                    System.out.println(post.getDescription() + "Description");
                    list.add(post);
                }
                iCallBack.onCallBack(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Timber.w("Get posts failed");
            }
        });
        return list;

    }

    public interface ICallBack {
        void onCallBack(List<PostDto> items);
    }

    @Override
    public void postItem(PostDto post) {
        try {
            api.postItem(post).execute();
        } catch (Exception e) {
            Timber.w("PostItem(Post post) failed");
        }
    }
}
