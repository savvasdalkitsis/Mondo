package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.usecase.transactions.TransactionsUseCase;

import rx.Observable;
import rx.subjects.PublishSubject;

public class FakeTransactionsUseCase implements TransactionsUseCase {

    private PublishSubject<Response<TransactionsPage>> publishSubject = PublishSubject.create();

    public void emitPage(TransactionsPage transactionsPage) {
        publishSubject.onNext(Response.success(transactionsPage));
        publishSubject.onCompleted();
    }

    @Override
    public Observable<Response<TransactionsPage>> getTransactions() {
        return publishSubject;
    }

    public void emitError() {
        publishSubject.onNext(Response.error());
        publishSubject.onCompleted();
    }
}
