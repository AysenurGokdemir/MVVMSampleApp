package com.aysenur.mvvmsampleapp.service.repository;

import com.aysenur.mvvmsampleapp.service.model.Project;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/***
 * ProjectRepository is the data provider for ViewModel, it has getProjectList() which simply wraps the response into LiveData Object.
 */

public class ProjectRepository {

    private GithubService githubService;

    public LiveData<List<Project>> getProjectList(String userId){
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        githubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {

            }
        });
        return data;
    }
}
