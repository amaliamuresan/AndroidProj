package com.example.groupupv2.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupupv2.R;
import com.example.groupupv2.databinding.PostDetailsBinding;
import com.example.groupupv2.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final List<Post> posts;

    public PostAdapter()
    {
        posts = new ArrayList<>();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          PostDetailsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                  R.layout.post_details, parent, false);

        return new PostViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);

    }

    public void updatePosts(List<Post> posts) {
        this.posts.clear();
        this.posts.addAll(posts);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        private final PostDetailsBinding binding;

        public PostViewHolder(@NonNull PostDetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Post model) {
            binding.setModel(model);
        }
    }
}
