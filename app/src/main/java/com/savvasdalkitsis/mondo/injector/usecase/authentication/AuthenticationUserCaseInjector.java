package com.savvasdalkitsis.mondo.injector.usecase.authentication;

import com.savvasdalkitsis.mondo.BuildConfig;
import com.savvasdalkitsis.mondo.usecase.authentication.AuthenticationUseCase;
import com.savvasdalkitsis.mondo.usecase.authentication.MondoAuthenticationUseCase;

import static com.savvasdalkitsis.mondo.injector.repository.CredentialsRepositoryInjector.credentialsRepository;
import static com.savvasdalkitsis.mondo.injector.repository.MondoApiInjector.mondoApi;

public class AuthenticationUserCaseInjector {

    public static AuthenticationUseCase authenticationUseCase() {
        return new MondoAuthenticationUseCase(mondoApi(), credentialsRepository(),
                BuildConfig.MONDO_CLIENT_ID, BuildConfig.MONDO_CLIENT_SECRET);
    }
}
