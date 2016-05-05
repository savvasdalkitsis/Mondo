package com.savvasdalkitsis.mondo.injector;

import com.savvasdalkitsis.mondo.MondoApplication;

public class ApplicationInjector {

    private static MondoApplication application;

    public static void setApplicationInstance(MondoApplication applicationInstance) {
        ApplicationInjector.application = applicationInstance;
    }
}
