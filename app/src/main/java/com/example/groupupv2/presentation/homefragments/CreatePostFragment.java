package com.example.groupupv2.presentation.homefragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.groupupv2.R;
import com.example.groupupv2.data.PostDto;
import com.example.groupupv2.data.remote.PostDataSource;
import com.example.groupupv2.data.remote.PostsAPI;
import com.example.groupupv2.databinding.FragmentCreatePostBinding;
import com.example.groupupv2.databinding.FragmentHomeBinding;
import com.example.groupupv2.domain.PostMediator;
import com.example.groupupv2.domain.PostRepository;
import com.example.groupupv2.domain.PostsUseCase;
import com.example.groupupv2.presentation.NavigationDrawerViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.util.Date;

public class CreatePostFragment extends Fragment {

    EditText titleET;
    EditText descriptionET;
    Button postBtn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CreatePostViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                PostRepository repository = new PostDataSource(PostsAPI.createApi());
                PostMediator postMediator = new PostMediator(repository);
                PostsUseCase useCase = new PostsUseCase(postMediator);

                return  (T) new CreatePostViewModel(useCase);
            }
        }).get(CreatePostViewModel.class);
        FragmentCreatePostBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_post, container, false);

        View view = binding.getRoot();

        binding.setCreatePostViewModel(viewModel);
        titleET = view.findViewById(R.id.post_title);
        descriptionET = view.findViewById(R.id.post_description);
        postBtn = view.findViewById(R.id.post_btn);

        PostRepository repository = new PostDataSource(PostsAPI.createApi());
        PostMediator postMediator = new PostMediator(repository);
        PostsUseCase useCase = new PostsUseCase(postMediator);

        postBtn.setOnClickListener(view1 -> {

            if (!viewModel.emptyFields(titleET, descriptionET)) {
                viewModel.postItem(new PostDto(
                        viewModel.getAuthorID(),
                        133123,
                        viewModel.getCurrentTime(),
                        descriptionET.getText().toString(),
                        "Domain",
                        (useCase.getLastItem() + 1)));
            }


        });

        return view;

    }


}
