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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photodemofragment.R;
import com.example.photodemofragment.adapters.RecyclerAdapter;
import com.example.photodemofragment.databinding.FragmentRecycleBinding;
import com.example.photodemofragment.viewModels.RecycleViewModel;

public class RecycleFragment extends Fragment {
    RecycleViewModel recycleViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentRecycleBinding binding = FragmentRecycleBinding.inflate(inflater, container, false);
        recycleViewModel = new ViewModelProvider(this).get(RecycleViewModel.class);
        binding.setRecycleViewModel(recycleViewModel);
        binding.setLifecycleOwner(this);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(recycleViewModel);
        recyclerView.setAdapter(recyclerAdapter);

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
        return binding.getRoot();
    }
}
