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
package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.repository.model.ApiAccounts;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.repository.model.ApiOAuthToken;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface MondoApi {

    @GET("balance")
    Observable<Result<ApiBalance>> getBalance();

    @GET("transactions?expand[]=merchant")
    Observable<Result<ApiTransactions>> getTransactions();

    @FormUrlEncoded
    @POST("oauth2/token")
    Observable<Result<ApiOAuthToken>> oAuthToken(@Field("client_id") String clientId,
                                                 @Field("client_secret") String clientSecret,
                                                 @Field("code") String code,
                                                 @Field("grant_type") String grantType,
                                                 @Field("redirect_uri") String redirectUri
    );

    @GET("accounts")
    Observable<Result<ApiAccounts>> getAccounts();
}
