package com.savvasdalkitsis.mondo.infra.network;

import com.savvasdalkitsis.mondo.repository.CredentialsRepository;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationHeaderInterceptor implements Interceptor {

    private final CredentialsRepository credentialsRepository;

    public AuthenticationHeaderInterceptor(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String accessToken = credentialsRepository.getAccessToken();
        Request request = chain.request();
        if (accessToken != null && !accessToken.isEmpty()) {
            request = request.newBuilder()
                    .header("Authorization", "Bearer " + accessToken)
                    .build();
        }
        return chain.proceed(request);
    }
}
