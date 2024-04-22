package com.example.photodemofragment.adapters;

import android.util.Pair;

import androidx.annotation.LayoutRes;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photodemofragment.entity.ItemTemplate;
import com.example.photodemofragment.entity.UnsplashPhoto;

import java.util.List;

import javax.inject.Inject;

public class RecyclerViewBindingAdapter {
    @BindingAdapter(value = {"items", "itemTemplate", "variableId", "extraVariable"},requireAll = false)
    public static void setItems(RecyclerView recyclerView, List<UnsplashPhoto> items, @LayoutRes int itemTemplate, int variableId, List<Pair<Integer, Object>> extraVariable) {
        RecyclerAdapter adapter;

        RecyclerAdapter oldAdapter = (RecyclerAdapter) recyclerView.getAdapter();
        if (oldAdapter == null) {
            ItemTemplate template = new ItemTemplate(variableId, itemTemplate);
            if( extraVariable != null && !extraVariable.isEmpty() ){
                template.setExtraVariable( extraVariable );
            }
            adapter = new RecyclerAdapter(template);
        } else {
            adapter = oldAdapter;
        }
        adapter.updateData(items);
        if (oldAdapter != adapter) {
            recyclerView.setAdapter(adapter);
        }
    }
}
