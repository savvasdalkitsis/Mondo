package com.savvasdalkitsis.mondo;

import android.app.Application;

import com.savvasdalkitsis.mondo.injector.ApplicationInjector;

public class MondoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setApplicationInstance(this);
    }
}
