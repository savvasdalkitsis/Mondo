package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.model.balance.Balance;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;

public interface MondoApi {

    Observable<Result<Balance>> getBalance();
}
