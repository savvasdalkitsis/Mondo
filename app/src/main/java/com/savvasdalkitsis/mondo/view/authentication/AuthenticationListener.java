package com.savvasdalkitsis.mondo.view.authentication;

public interface AuthenticationListener {
    void onRetryAuthentication();

    void onAuthenticationSuccess();
}
