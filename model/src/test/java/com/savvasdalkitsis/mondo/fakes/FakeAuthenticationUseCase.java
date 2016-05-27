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
package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.usecase.authentication.AuthenticationUseCase;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.subjects.PublishSubject;

public class FakeAuthenticationUseCase implements AuthenticationUseCase {

    private final Map<AuthenticationData, PublishSubject<Response<Void>>> responses = new HashMap<>();

    public void emitSuccessfulResponseFor(AuthenticationData authenticationData) {
        emitItem(authenticationData, Response.success(null));
    }

    public void emitErrorResponseFor(AuthenticationData authenticationData) {
        emitItem(authenticationData, Response.error());
    }

    private void emitItem(AuthenticationData authenticationData, Response<Void> response) {
        PublishSubject<Response<Void>> publishSubject = responses.get(authenticationData);
        publishSubject.onNext(response);
        publishSubject.onCompleted();
    }

    @Override
    public Observable<Response<Void>> authenticate(AuthenticationData authenticationData) {
        return responses.get(authenticationData);
    }

    public void accepts(AuthenticationData authenticationData) {
        responses.put(authenticationData, PublishSubject.create());
    }
}
