package com.savvasdalkitsis.mondo.injector.infra.external.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonInjector {

    private static final Gson GSON = new GsonBuilder().create();

    public static Gson gson() {
        return GSON;
    }
}
