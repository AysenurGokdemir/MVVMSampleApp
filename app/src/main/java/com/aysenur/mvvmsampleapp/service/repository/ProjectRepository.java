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

    //project detail

    public LiveData<Project> getProjectDetails(String userID, String projectName){

        final MutableLiveData<Project> data =  new MutableLiveData<>();

        githubService.getProjectDetails(userID, projectName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                simulateDelay();
                data.setValue(response.body());
            }


            @Override
            public void onFailure(Call<Project> call, Throwable t) {

                data.setValue(null);
            }
        });
        return data;
    }
    private void simulateDelay() {

        try {
            Thread.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();

        }

    }

}
