package com.georg.qulextestapp.injection;

import android.app.Application;

import com.georg.qulextestapp.model.GifListModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    GifListModel provideWeatherModel() {
        return new GifListModel();
    }

}