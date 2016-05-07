package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.repository.model.ApiBalance;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import rx.Observable;

public interface MondoApi {

    @GET("balance")
    Observable<Result<ApiBalance>> getBalance();
}
