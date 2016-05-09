package com.savvasdalkitsis.mondo.usecase.authentication;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.repository.CredentialsRepository;
import com.savvasdalkitsis.mondo.repository.MondoApi;

import rx.Observable;

public class MondoAuthenticationUseCase implements AuthenticationUseCase {
    private final MondoApi mondoApi;
    private final CredentialsRepository credentialsRepository;
    private final String clientId;
    private final String clientSecret;

    public MondoAuthenticationUseCase(MondoApi mondoApi,
                                      CredentialsRepository credentialsRepository,
                                      String clientId,
                                      String clientSecret) {
        this.mondoApi = mondoApi;
        this.credentialsRepository = credentialsRepository;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public Observable<Response<Void>> authenticate(AuthenticationData authenticationData) {
        return mondoApi.oAuthToken(clientId, clientSecret, authenticationData.getCode())
                .map(result -> result.response().body())
                .doOnNext(result -> credentialsRepository.saveAccessToken(result.getAccessToken()))
                .doOnNext(result -> credentialsRepository.saveRefreshToken(result.getRefreshToken()))
                .map(apiOAuthTokenResult -> Response.success(null));
    }
}
