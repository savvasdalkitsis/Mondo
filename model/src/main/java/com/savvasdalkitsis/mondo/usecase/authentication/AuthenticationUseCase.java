package com.savvasdalkitsis.mondo.usecase.authentication;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;

import rx.Observable;

public interface AuthenticationUseCase {

    Observable<Response<Void>> authenticate(AuthenticationData authenticationData);
}
