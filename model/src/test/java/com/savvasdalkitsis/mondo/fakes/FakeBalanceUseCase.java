package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;

import rx.Observable;
import rx.subjects.PublishSubject;

public class FakeBalanceUseCase implements BalanceUseCase {

    private PublishSubject<Balance> publishSubject = PublishSubject.create();

    @Override
    public Observable<Balance> getBalance() {
        return publishSubject;
    }

    public void emitBalance(Balance balance) {
        publishSubject.onNext(balance);
    }
}
