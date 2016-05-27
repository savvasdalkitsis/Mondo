/**
 * Copyright (C) 2016 Savvas Dalkitsis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.savvasdalkitsis.mondo.injector.external.retrofit;

import com.savvasdalkitsis.mondo.infra.network.AccountIdInterceptor;
import com.savvasdalkitsis.mondo.infra.network.AuthenticationHeaderInterceptor;
import com.savvasdalkitsis.mondo.infra.network.AuthenticationValidatingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.savvasdalkitsis.mondo.injector.infra.NavigatorInjector.authenticationRepository;
import static com.savvasdalkitsis.mondo.injector.repository.CredentialsRepositoryInjector.credentialsRepository;
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
                    .addInterceptor(new AccountIdInterceptor(credentialsRepository()))
                    .addInterceptor(new AuthenticationValidatingInterceptor(authenticationRepository()))
                    .addInterceptor(new AuthenticationHeaderInterceptor(credentialsRepository()))
                    .build())
            .build();

    public static Retrofit retrofit() {
        return RETROFIT;
    }
}