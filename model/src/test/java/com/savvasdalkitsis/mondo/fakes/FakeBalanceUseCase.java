package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;

import rx.Observable;
import rx.subjects.PublishSubject;

public class FakeBalanceUseCase implements BalanceUseCase {

    private PublishSubject<Response<Balance>> publishSubject = PublishSubject.create();

    @Override
    public Observable<Response<Balance>> getBalance() {
        return publishSubject;
    }

    public void emitBalance(Balance balance) {
        publishSubject.onNext(Response.success(balance));
    }

    public void emitError() {
        publishSubject.onNext(Response.error());
    }
}
