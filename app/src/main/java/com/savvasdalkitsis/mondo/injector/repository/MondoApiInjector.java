package com.savvasdalkitsis.mondo.injector.repository;

import com.savvasdalkitsis.mondo.repository.MondoApi;

import static com.savvasdalkitsis.mondo.injector.external.retrofit.RetrofitInjector.retrofit;

public class MondoApiInjector {

    public static MondoApi mondoApi() {
        return retrofit().create(MondoApi.class);
    }
}
