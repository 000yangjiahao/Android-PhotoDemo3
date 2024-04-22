package com.example.photodemofragment.entity;

import android.util.Pair;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.orhanobut.logger.Logger;
import androidx.databinding.library.baseAdapters.BR;

import java.util.List;

public class ItemTemplate {

    private int variableId;
    @LayoutRes
    private int templateId;

    private List<Pair<Integer,Object>> extraVariable;

    public ItemTemplate( int variableId , @LayoutRes int templateId){
        this.variableId = variableId;
        this.templateId = templateId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public List<Pair<Integer, Object>> getExtraVariable() {
        return extraVariable;
    }

    public int getVariableId() {
        return variableId;
    }


    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public void setVariableId(int variableId) {
        this.variableId = variableId;
    }

    public void setExtraVariable(List<Pair<Integer, Object>> extraVariable) {
        this.extraVariable = extraVariable;
    }
}
