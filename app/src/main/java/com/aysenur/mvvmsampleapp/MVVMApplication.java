package com.aysenur.mvvmsampleapp;

import android.app.Activity;
import android.app.Application;

import com.aysenur.mvvmsampleapp.di.AppInjector;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MVVMApplication extends Application implements HasActivityInjector {

    /***
     * Burada dikkat edilmesi gereken iki ana nokta:
     * 1. Application sınıfı, ActivityInjector () methodunda return etmek için HasActivityInjector'ı implement etmeli ve DispatchingAndroidInjector <Activity> 'yi @Inject etmelidir.
     * 2. OnCreate () of Application sınıfında, Enjekte Edilebilir arabirimi uygularlarsa fragmentları otomatik olarak ınject etmek için AppInjector'ı başlatırız.
     */
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
