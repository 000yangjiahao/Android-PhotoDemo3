package com.example.photodemofragment.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.photodemofragment.mvvm.ObservableViewModel;

public class MainViewModel extends ObservableViewModel {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
