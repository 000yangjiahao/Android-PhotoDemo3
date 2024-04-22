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

import com.example.photodemofragment.databinding.FragmentDetailBinding;
import com.example.photodemofragment.viewModels.DetailViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDetailBinding detailBinding = FragmentDetailBinding.inflate(inflater, container, false);
        DetailViewModel detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailBinding.setDetailViewModel(detailViewModel);
        detailBinding.setLifecycleOwner(this);

        DetailFragmentArgs args = DetailFragmentArgs.fromBundle(getArguments());
        detailViewModel.setPhotoId(args.getPhotoId());
        detailViewModel.setPhotoUrl(args.getPhotoUrl());
        detailViewModel.setPhotoDescription(args.getPhotoDescription());

        detailViewModel.getBackPressedEvent().observe(getViewLifecycleOwner(), event -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return detailBinding.getRoot();
    }
}
