package com.savvasdalkitsis.mondo.infra.network;

import com.savvasdalkitsis.mondo.repository.CredentialsRepository;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccountIdInterceptor implements Interceptor {

    private CredentialsRepository credentialsRepository;

    public AccountIdInterceptor(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.method().equals("GET")) {
            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("account_id", accountId())
                    .build();
            return chain.proceed(request.newBuilder().url(url).build());
        }
        return chain.proceed(request);
    }

    private String accountId() {
        String accountId = credentialsRepository.getAccountId();
        return accountId != null && !accountId.isEmpty() ? accountId : "INVALID_ACCOUNT_ID";
    }
}
