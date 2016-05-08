package com.savvasdalkitsis.mondo.infra.network;

import com.savvasdalkitsis.mondo.repository.AccountIdRepository;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccountIdInterceptor implements Interceptor {

    private final AccountIdRepository accountIdRepository;

    public AccountIdInterceptor(AccountIdRepository accountIdRepository) {
        this.accountIdRepository = accountIdRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("account_id", accountIdRepository.getAccountId())
                .build();
        return chain.proceed(request.newBuilder().url(url).build());
    }
}
