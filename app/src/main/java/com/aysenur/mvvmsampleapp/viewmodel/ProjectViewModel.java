package com.aysenur.mvvmsampleapp.viewmodel;

import android.app.Application;
import android.util.Log;

import com.aysenur.mvvmsampleapp.service.model.Project;
import com.aysenur.mvvmsampleapp.service.repository.ProjectRepository;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class ProjectViewModel extends AndroidViewModel {
    private static final String TAG= ProjectViewModel.class.getName();
    private static final MutableLiveData ABSENT= new MutableLiveData(); // absent -> bulunmayan, yok
    {
        ABSENT.setValue(null);
    }

    private final LiveData<Project> projectObservable;
    private final MutableLiveData<String> projectID;

    public ObservableField<Project> project= new ObservableField<>();


    @Inject
    public ProjectViewModel(@NonNull ProjectRepository projectRepository, @NonNull Application application) {
        super(application);

        this.projectID= new MutableLiveData<>();

        projectObservable= Transformations.switchMap(projectID, input -> {
            if (input.isEmpty()) {
                Log.i(TAG, "ProjectViewModel projectID is absent!!!");
                return ABSENT;
            }
            Log.i(TAG,"ProjectViewModel projectID is " + projectID.getValue());

            return projectRepository.getProjectDetails("Google", projectID.getValue());
        });
    }
}
