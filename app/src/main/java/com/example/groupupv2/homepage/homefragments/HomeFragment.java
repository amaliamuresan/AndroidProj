package com.example.groupupv2.homepage.homefragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupupv2.homepage.Post;
import com.example.groupupv2.homepage.PostAdapter;
import com.example.groupupv2.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    Context context;

    ArrayList<Post> posts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container, false);
        context = container.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.post_recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        posts = new ArrayList<>();
        posts.add(new Post("Johnny", R.drawable.common_full_open_on_phone, "19/09/2020", "Description1", "Domain1" ));
        posts.add(new Post("Bonnie", R.drawable.common_full_open_on_phone, "19/01/2020", "Description2", "Domain2" ));
        myAdapter = new PostAdapter(context, posts);
        recyclerView.setAdapter(myAdapter);




        return view;
    }
}
