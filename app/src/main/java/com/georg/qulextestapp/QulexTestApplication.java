package com.georg.qulextestapp;

import android.app.Application;
import android.content.Context;

import com.georg.qulextestapp.injection.ApplicationComponent;
import com.georg.qulextestapp.injection.ApplicationModule;
import com.georg.qulextestapp.injection.DaggerApplicationComponent;

/**
 * Created by georg on 23.09.2016.
 */
public class QulexTestApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    public static QulexTestApplication get(Context context) {
        return (QulexTestApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

}
