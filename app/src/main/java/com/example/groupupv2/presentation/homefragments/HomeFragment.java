package com.example.groupupv2.presentation.homefragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupupv2.data.PostDataSource;
import com.example.groupupv2.databinding.FragmentHomeBinding;
import com.example.groupupv2.databinding.NavigationViewBinding;
import com.example.groupupv2.databinding.PostDetailsBinding;
import com.example.groupupv2.domain.Post;
import com.example.groupupv2.domain.PostRepository;
import com.example.groupupv2.domain.PostsUseCase;
import com.example.groupupv2.presentation.NavigationDrawerViewModel;
import com.example.groupupv2.presentation.PostAdapter;
import com.example.groupupv2.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        HomeFragmentViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                PostRepository repository = new PostDataSource();
                PostsUseCase useCase = new PostsUseCase(repository);

                return (T) new HomeFragmentViewModel(useCase);
            }
        }).get(HomeFragmentViewModel.class);

        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        View view = binding.getRoot();

        binding.setHomeFragmentViewModel(viewModel);

        viewModel.addPosts();

        return view;
    }
}
