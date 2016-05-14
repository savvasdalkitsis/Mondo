package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.infra.cache.ObservableCache;

import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.subjects.PublishSubject;

public class FakeObservableCache<T> implements ObservableCache<T> {

    private PublishSubject<Result<T>> subject;

    @Override
    public Observable<Result<T>> cache(Observable<Result<T>> observable, Class<T> itemClass) {
        subject = PublishSubject.create();
        return subject;
    }

    public void emitSuccessfulResult(T item) {
        subject.onNext(Result.response(Response.success(item)));
        subject.onCompleted();
    }
}
