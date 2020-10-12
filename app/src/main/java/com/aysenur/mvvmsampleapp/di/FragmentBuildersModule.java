package com.aysenur.mvvmsampleapp.di;

import com.aysenur.mvvmsampleapp.ui.ProjectFragment;
import com.aysenur.mvvmsampleapp.ui.ProjectListFragment;

import dagger.android.ContributesAndroidInjector;

// Fragments Dagger Module
public abstract class FragmentBuildersModule {

    //Starting from Dagger 2.10, @ContributesAndroidInjector easily attaches activities and fragments to dagger graph
    @ContributesAndroidInjector
    abstract ProjectFragment contributeProjectFragment();

    @ContributesAndroidInjector
    abstract ProjectListFragment contributeProjectListFragment();

}
