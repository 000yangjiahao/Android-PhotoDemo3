package com.example.photodemofragment.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photodemofragment.BR;
import com.example.photodemofragment.R;
import com.example.photodemofragment.entity.ItemTemplate;
import com.example.photodemofragment.entity.UnsplashPhoto;
import com.example.photodemofragment.viewModels.MainViewModel;
import com.example.photodemofragment.viewModels.RecycleViewModel;


import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoViewHolder> {

    private final List<UnsplashPhoto> photoList;
    private final ItemTemplate itemTemplate;

    public RecyclerAdapter(ItemTemplate itemTemplate) {
        this.photoList = new ArrayList<>();
        this.itemTemplate=itemTemplate;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<UnsplashPhoto> newData) {
        photoList.clear();
        photoList.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_item, parent, false);
        return new PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        UnsplashPhoto photo = photoList.get(position);
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        assert binding != null;
        binding.setVariable(itemTemplate.getVariableId(), photo);
        if( itemTemplate.getExtraVariable() != null ){
            itemTemplate.getExtraVariable().forEach( t -> binding.setVariable( t.first , t.second ));
        }
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        public PhotoViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
        }

    }
}