package com.aysenur.mvvmsampleapp.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.aysenur.mvvmsampleapp.MVVMApplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;


/***
 * if they implement Injectable interface;
 * otomatik olarak enjekte etmek için aşağıdaki AppInjector yardımcı sınıfı,
 * onFragmentCreated () üzerine aşağıdaki gibi fragment örneğini inject etmek için oluşturulur.
 *
 *
 * AppInjector is a helper class to automatically inject fragments if they implement {@link Injectable}.
 */
public class AppInjector {

    private AppInjector(){}

    /***
     * Unutulmaması gereken bir şey,
     * AppInjector.init (), Uygulama başlangıcında çağrılacaktır (Özel Uygulama sınıfı bölümünde göstereceğimiz gibi).
     *
     * @param mvvmApplication
     */
    public static void init(MVVMApplication mvvmApplication){
        DaggerAppComponent.builder().application(mvvmApplication)
                .build().inject(mvvmApplication);

        mvvmApplication
                .registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                        handleActivity(activity);
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {

                    }

                    @Override
                    public void onActivityResumed(Activity activity) {

                    }

                    @Override
                    public void onActivityPaused(Activity activity) {

                    }

                    @Override
                    public void onActivityStopped(Activity activity) {

                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {

                    }
                });
    }

    private static void handleActivity(Activity activity) {

        if (activity instanceof HasSupportFragmentInjector){
            AndroidInjection.inject(activity);
        }
        if (activity instanceof FragmentActivity){
            ((FragmentActivity) activity).getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(
                            new FragmentManager.FragmentLifecycleCallbacks() {
                                @Override
                                public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment fragment, @Nullable Bundle savedInstanceState) {

                                        if (fragment instanceof Injectable) { //fragment, Injectable' a aitse true donecektir
                                            AndroidSupportInjection.inject(fragment);
                                        }
                                }
                            },true);
        }
    }
}
