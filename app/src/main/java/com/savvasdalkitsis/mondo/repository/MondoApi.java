package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.repository.model.ApiAccounts;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.repository.model.ApiOAuthToken;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface MondoApi {

    @GET("balance")
    Observable<Result<ApiBalance>> getBalance();

    @GET("transactions?expand[]=merchant")
    Observable<Result<ApiTransactions>> getTransactions();

    @POST("oauth2/token?grant_type=authorization_code&redirect_uri=http%3A%2F%2Fmondotest%2Fredirect")
    Observable<Result<ApiOAuthToken>> oAuthToken(@Query("client_id") String clientId,
                                                 @Query("client_secret") String clientSecret,
                                                 @Query("code") String code);

    @GET("accounts")
    Observable<Result<ApiAccounts>> getAccounts();
}
