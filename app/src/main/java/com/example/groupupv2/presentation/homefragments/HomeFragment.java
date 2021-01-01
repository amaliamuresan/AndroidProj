package com.example.groupupv2.presentation.homefragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.groupupv2.data.PostDto;
import com.example.groupupv2.data.remote.PostDataSource;
import com.example.groupupv2.data.remote.PostsAPI;
import com.example.groupupv2.databinding.FragmentHomeBinding;
import com.example.groupupv2.domain.Post;
import com.example.groupupv2.domain.PostMediator;
import com.example.groupupv2.domain.PostRepository;
import com.example.groupupv2.domain.PostsUseCase;
import com.example.groupupv2.R;

public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        PostRepository repository = new PostDataSource(PostsAPI.createApi());
        HomeFragmentViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

                PostMediator postMediator = new PostMediator(repository);
                PostsUseCase useCase = new PostsUseCase(postMediator);

                return (T) new HomeFragmentViewModel(useCase);
            }
        }).get(HomeFragmentViewModel.class);

        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        View view = binding.getRoot();

        binding.setHomeFragmentViewModel(viewModel);

        viewModel.addPosts();
        //viewModel.addPost(new PostDto("323", 2323, "19298734333", "Another description", "domaine1"));


        return view;
    }




}
