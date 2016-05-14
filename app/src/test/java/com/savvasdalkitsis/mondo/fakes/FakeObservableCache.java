package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.infra.cache.ObservableCache;

import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.subjects.PublishSubject;

public class FakeObservableCache<T> implements ObservableCache<T> {

    private PublishSubject<Result<T>> subject;

    @Override
    public Observable.Transformer<Result<T>, Result<T>> on(Class<T> itemClass) {
        subject = PublishSubject.create();
        return observable -> subject;
    }

    public void emitSuccessfulResult(T item) {
        subject.onNext(Result.response(Response.success(item)));
        subject.onCompleted();
    }
}
