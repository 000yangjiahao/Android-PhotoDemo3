package com.example.photodemofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.photodemofragment.databinding.ActivityMainBinding;
import com.example.photodemofragment.viewModels.MainViewModel;
import androidx.fragment.app.FragmentManager;
import com.example.photodemofragment.fragments.RecycleFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(MainViewModel.class);
        mainBinding.setViewModel(mainViewModel);
        mainBinding.setLifecycleOwner(this);
    }

}