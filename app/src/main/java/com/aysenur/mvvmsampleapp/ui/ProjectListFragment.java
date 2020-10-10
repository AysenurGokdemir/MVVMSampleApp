package com.aysenur.mvvmsampleapp.ui;

import android.os.Bundle;

import com.aysenur.mvvmsampleapp.service.model.Project;
import com.aysenur.mvvmsampleapp.view.adapter.ProjectAdapter;
import com.aysenur.mvvmsampleapp.viewmodel.ProjectListViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ProjectListFragment extends Fragment {
    private ProjectAdapter projectAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProjectListViewModel viewModel= ViewModelProviders.of(this).get(ProjectListViewModel.class);


    }

    private void observeViewModel(ProjectListViewModel viewModel){
        // update the list when the data changes

        viewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                if (projects !=null){


                }
            }
        });

    }
}
