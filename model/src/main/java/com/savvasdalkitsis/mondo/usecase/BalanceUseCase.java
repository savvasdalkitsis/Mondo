package com.savvasdalkitsis.mondo.usecase;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;

import rx.Observable;

public interface BalanceUseCase {

    Observable<Response<Balance>> getBalance();
}
