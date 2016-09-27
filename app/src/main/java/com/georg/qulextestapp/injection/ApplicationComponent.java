package com.georg.qulextestapp.injection;

import com.georg.qulextestapp.view.GifListActivity;
import com.georg.qulextestapp.view.GifListView;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class
})
public interface ApplicationComponent {

    void inject(GifListActivity gifListActivity);

}