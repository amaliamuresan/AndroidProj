package com.example.groupupv2.presentation.homefragments;

import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.ViewModel;

import com.example.groupupv2.data.PostDto;
import com.example.groupupv2.domain.PostsUseCase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

public class CreatePostViewModel extends ViewModel {
    private final PostsUseCase useCase;

    public CreatePostViewModel(PostsUseCase useCase)
    {
        this.useCase = useCase;
    }

    public boolean emptyFields(EditText title, EditText description) {
        if (title.getText().toString() == null) {
            title.setError("Empty field!");
            return true;
        }
        if (description.getText().toString() == null) {
            description.setError("Empty field!");
            return true;
        }

        return false;

    }

    public String getAuthorID() {
        FirebaseUser currentUser;
        String currentUserUid = "None";
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null)
            currentUserUid = currentUser.getDisplayName();

        return currentUserUid;
    }

    public String getCurrentTime() {
        Date date = new Date();
        long ret = date.getTime();
        return Long.toString(ret);
    }

    public void postItem(PostDto postDto)
    {
        useCase.postItem(postDto);
    }
}
