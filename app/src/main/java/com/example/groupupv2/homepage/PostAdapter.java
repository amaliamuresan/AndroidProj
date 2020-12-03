package com.example.groupupv2.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupupv2.R;
import com.example.groupupv2.domain.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<Post> posts;

    public PostAdapter(Context context, ArrayList<Post> list)
    {
        posts = list;
    }
     public class ViewHolder extends RecyclerView.ViewHolder
     {
         ImageView ivProfilePic;
         TextView tvDate;
         TextView tvAuthorName;
         TextView tvDescription;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);

             ivProfilePic = itemView.findViewById(R.id.post_profilePic);
             tvDate = itemView.findViewById(R.id.post_date);
             tvAuthorName = itemView.findViewById(R.id.post_authorName);
             tvDescription = itemView.findViewById(R.id.post_description);

         }
     }


    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(posts.get(position));
        holder.tvDate.setText((CharSequence) posts.get(position).getData());
        holder.tvAuthorName.setText(posts.get(position).getAutorID());
        holder.ivProfilePic.setImageResource(R.drawable.user_ic);
        holder.tvDescription.setText(posts.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
