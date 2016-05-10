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
