package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.model.balance.Balance;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import rx.Observable;

public interface MondoApi {

    @GET("balance")
    Observable<Result<Balance>> getBalance();
}
