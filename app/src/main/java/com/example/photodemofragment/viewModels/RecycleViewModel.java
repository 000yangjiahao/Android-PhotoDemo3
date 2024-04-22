package com.example.photodemofragment.viewModels;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.example.photodemofragment.BR;
import com.example.photodemofragment.adapters.RecyclerAdapter;
import com.example.photodemofragment.entity.UnsplashPhoto;
import com.example.photodemofragment.mvvm.ObservableViewModel;
import com.example.photodemofragment.share.Api;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
@HiltViewModel
public class RecycleViewModel extends ObservableViewModel {

    //    private static final String BASE_URL = "https://api.unsplash.com/photos/random?client_id=D1iV9IoCJ3l76N23H7DpV3hfmBCpu0LPUDw0U734_0Y&count=10";
    private static final String BASE_URL = "https://api.unsplash.com";
    @Bindable
    private String query;
    @Bindable
    private List<UnsplashPhoto> photoListLiveData = new ArrayList<>();

    private UnsplashPhoto unsplashPhoto = null;
    private MutableLiveData<Void> onClick = new MutableLiveData<>();
    private Disposable disposable;
    public Retrofit retrofit;
    private final int variableId;
    private final List<Pair<Integer,Object>> extraVariable;
    @Inject
    public RecycleViewModel(@NonNull Application application, Retrofit retrofit) {
        super(application);
        this.retrofit=retrofit;
        this.variableId=BR.photo;
        this.extraVariable = Collections.singletonList(Pair.create(BR.parent, this));
        loadImage();
    }

    public int getVariableId() {
        return variableId;
    }

    public List<Pair<Integer, Object>> getExtraVariable() {
        return extraVariable;
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
        Api api = retrofit.create(Api.class);
        disposable = api.getPhotos("D1iV9IoCJ3l76N23H7DpV3hfmBCpu0LPUDw0U734_0Y", 10, query)
                .subscribe(
                        this::setPhotoListLiveData,
                        err -> {
                            Logger.e("图片加载失败", err);
                        }
                );
    }

    public void detailClick(UnsplashPhoto photo) {
        setUnsplashPhoto(photo);
        onClick.setValue(null);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
