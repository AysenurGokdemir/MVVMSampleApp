package com.aysenur.mvvmsampleapp.di;

//ViewModel instances oluşturulmasından sorumlu olan ViewModelSubComponent interface .

import com.aysenur.mvvmsampleapp.viewmodel.ProjectListViewModel;
import com.aysenur.mvvmsampleapp.viewmodel.ProjectViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder{
        ViewModelSubComponent build();
    }

    ProjectListViewModel projectListViewModel();
    ProjectViewModel projectViewModel();



}

/*** Note that;
 *      ViewModelSubComponent'in, ViewModel örneklerini almak için ProjectViewModelFactory tarafından çağrılacağını unutmayın.
 */