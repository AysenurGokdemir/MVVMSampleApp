package com.aysenur.mvvmsampleapp.viewmodel;

import android.util.ArrayMap;

import com.aysenur.mvvmsampleapp.di.ViewModelSubComponent;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

@Singleton
public class ProjectViewModelFactory implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;


    @Inject
    public ProjectViewModelFactory(ViewModelSubComponent viewModelSubComponent) {
        creators= new ArrayMap<>();

        // View models cannot be injected directly because they won't be bound to the owner's
        // view model scope.

        creators.put(ProjectViewModel.class, () -> viewModelSubComponent.projectViewModel());
        creators.put(ProjectListViewModel.class, () -> viewModelSubComponent.projectListViewModel());
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null){
            for (Map.Entry<Class, Callable<? extends  ViewModel>> entry : creators.entrySet()){
                if (modelClass.isAssignableFrom(entry.getKey())){
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null){
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }
        try{
            return (T) creator.call();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
