package com.aysenur.mvvmsampleapp.di;

import com.aysenur.mvvmsampleapp.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules=FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();


}


