package com.aysenur.mvvmsampleapp;

import android.app.Activity;
import android.app.Application;

import com.aysenur.mvvmsampleapp.di.AppInjector;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MVVMApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity>dispatchingAndroidInjector;

    @Override
    public void onCreate(){
        super.onCreate();
        AppInjector.init(this);
    }

    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
