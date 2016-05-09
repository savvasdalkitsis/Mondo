package com.savvasdalkitsis.mondo.presenter.authentication;

import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.usecase.authentication.AuthenticationUseCase;
import com.savvasdalkitsis.mondo.view.authentication.AuthenticationView;

public class AuthenticationPresenter {

    private AuthenticationUseCase authenticationUseCase;

    public AuthenticationPresenter(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    public void startPresenting(AuthenticationView authenticationView, AuthenticationData authenticationData) {
        authenticationView.displayLoading();
        authenticationUseCase.authenticate(authenticationData).subscribe(response -> {
            if (response.isError()) {
                authenticationView.errorAuthenticating();
            } else {
                authenticationView.successfulAuthentication();
            }
        });
    }
}
