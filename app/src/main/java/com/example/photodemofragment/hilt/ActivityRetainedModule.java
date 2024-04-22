package com.example.photodemofragment.hilt;

import com.example.photodemofragment.adapters.RecyclerAdapter;
import com.example.photodemofragment.entity.ItemTemplate;
import com.example.photodemofragment.viewModels.RecycleViewModel;
import com.orhanobut.logger.Logger;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ActivityRetainedComponent.class)
public class ActivityRetainedModule {

    @ActivityRetainedScoped
    @Provides
    public static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
//    @ActivityRetainedScoped
//    @Provides
//    public static RecyclerAdapter provideRecyclerAdapter(ItemTemplate itemTemplate){
//        return new RecyclerAdapter(itemTemplate);
//    }

}
