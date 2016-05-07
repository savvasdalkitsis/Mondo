package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.repository.MondoApi;

import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.subjects.PublishSubject;

public class FakeMondoApi implements MondoApi {

    private PublishSubject<Result<Balance>> subject;

    @Override
    public Observable<Result<Balance>> getBalance() {
        subject = PublishSubject.create();
        return subject;
    }


    public void emitSuccess(Balance balance) {
        subject.onNext(Result.response(Response.success(balance)));
        subject.onCompleted();
    }
}
