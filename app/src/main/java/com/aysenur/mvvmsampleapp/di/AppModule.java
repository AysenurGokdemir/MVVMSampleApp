package com.aysenur.mvvmsampleapp.di;

import com.aysenur.mvvmsampleapp.service.repository.GithubService;
import com.aysenur.mvvmsampleapp.viewmodel.ProjectViewModelFactory;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)  // Something important here to note, do not forget
class AppModule {
    @Singleton @Provides
    GithubService provideGithubService(){
        return new Retrofit.Builder()
                .baseUrl(GithubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent){

        return new ProjectViewModelFactory(viewModelSubComponent.build());
    }
}
