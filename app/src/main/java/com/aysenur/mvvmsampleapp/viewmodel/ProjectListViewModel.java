package com.aysenur.mvvmsampleapp.viewmodel;

import android.app.Application;

import com.aysenur.mvvmsampleapp.service.model.Project;
import com.aysenur.mvvmsampleapp.service.repository.ProjectRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ProjectListViewModel extends AndroidViewModel {

    private final LiveData<List<Project>> projectListObservable;


    public ProjectListViewModel(@NonNull ProjectRepository projectRepository, @NonNull Application application) {
        super(application);



        //If any transformation is needed, this can be simply done by Transformations class ...
        /**
         * costructor Google GitHub projelerini almak için getProjectList("Google") çağırır.
         */
        projectListObservable= projectRepository.getProjectList("Google");
    }

    /**
     * Kullanıcı arabiriminin gözlemleyebilmesi için LiveData Projeleri sorgusunu gösterin.
     * @return
     */
    public  LiveData<List<Project>> getProjectListObservable(){
        return projectListObservable;
    }
}
