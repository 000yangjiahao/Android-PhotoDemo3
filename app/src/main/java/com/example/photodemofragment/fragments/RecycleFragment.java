package com.example.photodemofragment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.photodemofragment.databinding.FragmentRecycleBinding;
import com.example.photodemofragment.viewModels.RecycleViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecycleFragment extends Fragment {
    private FragmentRecycleBinding binding;
    private RecycleViewModel recycleViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecycleBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        setupRecyclerView();

        return binding.getRoot();
    }

    private void setupRecyclerView() {

        recycleViewModel = new ViewModelProvider(this).get(RecycleViewModel.class);
        binding.setRecycleViewModel(recycleViewModel);

        recycleViewModel.getOnClick().observe(getViewLifecycleOwner(), avoid -> {
            if (recycleViewModel.getUnsplashPhoto() != null) {
                RecycleFragmentDirections.ActionRecycleFragmentToDetailFragment action =
                        RecycleFragmentDirections.actionRecycleFragmentToDetailFragment(
                                recycleViewModel.getUnsplashPhoto().getId(),
                                recycleViewModel.getUnsplashPhoto().getAlt_description(),
                                recycleViewModel.getUnsplashPhoto().getUrls().getRaw()
                        );
                Navigation.findNavController(requireView()).navigate(action);
                recycleViewModel.setUnsplashPhoto(null);
            }
        });
    }
}

