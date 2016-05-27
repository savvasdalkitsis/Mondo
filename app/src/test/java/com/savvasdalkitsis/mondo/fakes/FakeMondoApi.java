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

import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiAccounts;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.repository.model.ApiOAuthToken;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.Field;
import rx.Observable;
import rx.subjects.ReplaySubject;

public class FakeMondoApi implements MondoApi {

    private ReplaySubject<Result<ApiBalance>> balanceSubject;
    private ReplaySubject<Result<ApiAccounts>> accountsSubject;
    private ReplaySubject<Result<ApiTransactions>> transactionsSubject;
    private final Map<String, ReplaySubject<Result<ApiOAuthToken>>> oAuthSubjects = new HashMap<>();

    @Override
    public Observable<Result<ApiBalance>> getBalance() {
        balanceSubject = ReplaySubject.create();
        return balanceSubject;
    }

    @Override
    public Observable<Result<ApiTransactions>> getTransactions() {
        transactionsSubject = ReplaySubject.create();
        return transactionsSubject;
    }

    @Override
    public Observable<Result<ApiOAuthToken>> oAuthToken(@Field("client_id") String clientId,
                                                        @Field("client_secret") String clientSecret,
                                                        @Field("code") String code,
                                                        @Field("grant_type") String grantType,
                                                        @Field("redirect_uri") String redirectUri) {
        return oAuthSubjects.get(keyFor(clientId, clientSecret, code, grantType, redirectUri));
    }

    public Observable<Result<ApiAccounts>> getAccounts() {
        accountsSubject = ReplaySubject.create();
        return accountsSubject;
    }

    public void emitSuccessfulBalance(ApiBalance balance) {
        emitBalanceAndFinish(Result.response(Response.success(balance)));
    }

    public void emitBalanceError() {
        balanceSubject.onError(ioException());
    }

    public void emitBalanceErrorResponse() {
        emitBalanceAndFinish(Result.response(Response.error(500, ResponseBody.create(null, ""))));
    }

    private void emitBalanceAndFinish(Result<ApiBalance> result) {
        balanceSubject.onNext(result);
        balanceSubject.onCompleted();
    }

    public void emitSuccessfulTransactionPage(ApiTransactions apiTransactions) {
        transactionsSubject.onNext(Result.response(Response.success(apiTransactions)));
        transactionsSubject.onCompleted();
    }

    public void emitTransactionsError() {
        transactionsSubject.onError(ioException());
    }

    private IOException ioException() {
        return new IOException("Error during api call");
    }

    public void emitTransactionsErrorResponse() {
        transactionsSubject.onNext(Result.response(Response.error(500, ResponseBody.create(null, ""))));
        transactionsSubject.onCompleted();
    }

    public void acceptsOAuthCall(String clientId, String clientSecret, String code,
                                 String grantType, String redirectUri) {
        oAuthSubjects.put(keyFor(clientId, clientSecret, code, grantType, redirectUri), ReplaySubject.create());
    }

    public void emitSuccessfulOAuthFor(String clientId, String clientSecret, String code,
                                       String grantType, String redirectUri, ApiOAuthToken apiOAuthToken) {
        ReplaySubject<Result<ApiOAuthToken>> subject = oAuthSubjects.get(keyFor(clientId, clientSecret, code, grantType, redirectUri));
        subject.onNext(Result.response(Response.success(apiOAuthToken)));
        subject.onCompleted();
    }

    public void emitErrorOAuth(String clientId, String clientSecret, String code,
                               String grantType, String redirectUri) {
        ReplaySubject<Result<ApiOAuthToken>> subject = oAuthSubjects.get(keyFor(clientId, clientSecret, code, grantType, redirectUri));
        subject.onError(new IOException("error getting oauth token"));
    }

    public void emitSuccessfulAccounts(ApiAccounts apiAccounts) {
        accountsSubject.onNext(Result.response(Response.success(apiAccounts)));
        accountsSubject.onCompleted();
    }

    private String keyFor(String clientId, String clienSecret, String code, String grantType, String redirectUri) {
        return clientId + "::" + clienSecret + "::" + code + "::" + grantType + "::" + redirectUri;
    }
}
