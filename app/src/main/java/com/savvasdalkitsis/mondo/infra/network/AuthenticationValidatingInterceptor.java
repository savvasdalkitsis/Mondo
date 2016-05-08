package com.savvasdalkitsis.mondo.infra.network;

import com.savvasdalkitsis.mondo.infra.AuthenticationNavigator;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class AuthenticationValidatingInterceptor implements Interceptor {

    private AuthenticationNavigator authenticationNavigator;

    public AuthenticationValidatingInterceptor(AuthenticationNavigator authenticationNavigator) {
        this.authenticationNavigator = authenticationNavigator;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.code() == 401) {
            authenticationNavigator.navigateToAuthentication();
        }
        return response;
    }
}
