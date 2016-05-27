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
package com.savvasdalkitsis.mondo.usecase.authentication;

import com.savvasdalkitsis.mondo.BuildConfig;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.repository.CredentialsRepository;
import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.rx.RxTransformers;

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
        return mondoApi.oAuthToken(clientId, clientSecret, authenticationData.getCode(),
                BuildConfig.API_CALL_GRANT_TYPE, BuildConfig.API_CALL_REDIRECT_URI)
                .map(result -> result.response().body())
                .doOnNext(oAuth -> credentialsRepository.saveAccessToken(oAuth.getAccessToken()))
                .doOnNext(oAuth -> credentialsRepository.saveRefreshToken(oAuth.getRefreshToken()))
                .flatMap(token -> mondoApi.getAccounts())
                .map(result -> result.response().body())
                .doOnNext(accounts -> credentialsRepository.saveAccountId(accounts.getAccounts().get(0).getId()))
                .map(apiOAuthTokenResult -> Response.<Void>success(null))
                .compose(RxTransformers.applyAndroidSchedulers())
                .compose(RxTransformers.onErrorToErrorResponse());
    }
}
