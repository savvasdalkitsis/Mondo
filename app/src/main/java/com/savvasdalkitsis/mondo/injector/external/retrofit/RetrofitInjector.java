package com.savvasdalkitsis.mondo.injector.external.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.savvasdalkitsis.mondo.injector.repository.MondoApiBaseUrlProviderInjector.mondoApiBaseUrlProvider;

public class RetrofitInjector {

    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(mondoApiBaseUrlProvider().getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(new OkHttpClient.Builder()
                    .connectTimeout(3, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build())
            .build();

    public static Retrofit retrofit() {
        return RETROFIT;
    }
}