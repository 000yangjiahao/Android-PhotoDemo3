package com.example.photodemofragment.share;

import com.example.photodemofragment.entity.UnsplashPhoto;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/photos/random")
    Single<List<UnsplashPhoto>> getPhotos(
            @Query("client_id") String clientId,
            @Query("count") int count,
            @Query("query") String query
    );
}
