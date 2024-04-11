package com.example.photodemofragment.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.photodemofragment.mvvm.ObservableViewModel;

public class DetailViewModel extends ObservableViewModel {
    private String photoId;
    private String photoDescription;
    private String photoUrl;
    private MutableLiveData<Void> backPressedEvent = new MutableLiveData<>();

    public LiveData<Void> getBackPressedEvent() {
        return backPressedEvent;
    }
    public String getPhotoId() {
        return photoId;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void returnList(){
        backPressedEvent.setValue(null);
    }
}
