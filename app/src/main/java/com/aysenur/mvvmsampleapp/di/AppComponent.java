package com.aysenur.mvvmsampleapp.di;

import android.app.Application;

import com.aysenur.mvvmsampleapp.MVVMApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


/***
 * AppModule ve MainActivityModule dahil olmak üzere
 * AppComponent'e, gerekli tüm bağlamaların mevcut olduğundan emin olmak için
 * gerekli olduğunu belirten resmi belgelere göre ekledik.
 * AndroidSupportInjectionModule, dagger-android'de yerleşik bir modüldür:
 */
@Singleton
@Component(modules={
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }
    void inject(MVVMApplication mvvmApplication);
}
