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
package com.savvasdalkitsis.mondo.presenter.authentication;

import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.usecase.authentication.AuthenticationUseCase;
import com.savvasdalkitsis.mondo.view.authentication.AuthenticationView;

import rx.Subscription;

public class AuthenticationPresenter {

    private AuthenticationUseCase authenticationUseCase;
    private Subscription subscription;

    public AuthenticationPresenter(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    public void startPresenting(AuthenticationView authenticationView, AuthenticationData authenticationData) {
        authenticationView.displayLoading();
        subscription = authenticationUseCase.authenticate(authenticationData).subscribe(response -> {
            if (response.isError()) {
                authenticationView.errorAuthenticating();
            } else {
                authenticationView.successfulAuthentication();
            }
        });
    }

    public void stopPresenting() {
        subscription.unsubscribe();
    }
}
