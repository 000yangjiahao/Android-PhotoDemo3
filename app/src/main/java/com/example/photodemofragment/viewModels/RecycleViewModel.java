package com.example.photodemofragment.viewModels;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.example.photodemofragment.BR;
import com.example.photodemofragment.entity.UnsplashPhoto;
import com.example.photodemofragment.mvvm.ObservableViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RecycleViewModel extends ObservableViewModel {

    private static final String BASE_URL = "https://api.unsplash.com/photos/random?client_id=D1iV9IoCJ3l76N23H7DpV3hfmBCpu0LPUDw0U734_0Y&count=10";
    @Bindable
    private String query;
    @Bindable
    private List<UnsplashPhoto> photoListLiveData = new ArrayList<>();

    private UnsplashPhoto unsplashPhoto = null;
    private MutableLiveData<Void> onClick = new MutableLiveData<>();

    // 省略其他代码

    OkHttpClient client = new OkHttpClient();

    private final Gson gson;

    public RecycleViewModel(@NonNull Application application) {
        super(application);
        gson = new Gson();
        loadImage();
    }

    public UnsplashPhoto getUnsplashPhoto() {
        return unsplashPhoto;
    }

    public void setUnsplashPhoto(UnsplashPhoto unsplashPhoto) {
        this.unsplashPhoto = unsplashPhoto;
    }

    public MutableLiveData<Void> getOnClick() {
        return onClick;
    }

    public void setPhotoListLiveData(List<UnsplashPhoto> photoListLiveData) {
        this.photoListLiveData = photoListLiveData;
        notifyPropertyChanged(BR.photoListLiveData);
    }

    public List<UnsplashPhoto> getPhotoListLiveData() {
        return photoListLiveData;
    }

    public void setQuery(String query) {
        this.query = query;
        notifyPropertyChanged(BR.query);
    }

    public String getQuery() {
        return query;
    }

    public void loadImage() {
        String finalUrl = BASE_URL;
        if (query != null && !query.isEmpty()) {
            finalUrl += "&query=" + query;
        }

        Request request = new Request.Builder()
                .url(finalUrl)
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Logger.e("图片加载失败", e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (response.isSuccessful() && responseBody != null) {
                        String json = responseBody.string();
                        List<UnsplashPhoto> photos = gson.fromJson(json, new TypeToken<List<UnsplashPhoto>>() {
                        }.getType());
                        setPhotoListLiveData(photos);
                        Logger.i("photos", photos);
                    }
                }
            }
        });

    }

    public void detailClick(UnsplashPhoto photo) {
        setUnsplashPhoto(photo);
        onClick.setValue(null);
    }
}
