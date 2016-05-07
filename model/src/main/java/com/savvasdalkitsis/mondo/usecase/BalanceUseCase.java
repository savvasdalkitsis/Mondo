package com.savvasdalkitsis.mondo.usecase;

import com.savvasdalkitsis.mondo.model.balance.Balance;

import rx.Observable;

public interface BalanceUseCase {

    Observable<Balance> getBalance();
}
