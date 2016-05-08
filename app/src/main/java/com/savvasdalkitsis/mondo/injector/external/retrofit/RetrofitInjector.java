package com.savvasdalkitsis.mondo.injector.external.retrofit;

import com.savvasdalkitsis.mondo.infra.network.AccountIdInterceptor;
import com.savvasdalkitsis.mondo.infra.network.AuthenticationValidatingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.savvasdalkitsis.mondo.injector.infra.AuthenticationNavigatorInjector.authenticationRepository;
import static com.savvasdalkitsis.mondo.injector.repository.AccountIdRepositoryInjector.accountIdRepository;
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
                    .addInterceptor(new AccountIdInterceptor(accountIdRepository()))
                    .addInterceptor(new AuthenticationValidatingInterceptor(authenticationRepository()))
                    .build())
            .build();

    public static Retrofit retrofit() {
        return RETROFIT;
    }
}