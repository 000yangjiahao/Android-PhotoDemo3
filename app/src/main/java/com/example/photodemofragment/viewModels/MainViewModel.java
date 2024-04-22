package com.example.photodemofragment.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.photodemofragment.mvvm.ObservableViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ObservableViewModel {
    @Inject
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
