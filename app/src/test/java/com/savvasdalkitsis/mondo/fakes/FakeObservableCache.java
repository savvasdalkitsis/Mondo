package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.infra.cache.ObservableCache;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.subjects.PublishSubject;

public class FakeObservableCache<T> implements ObservableCache<T> {

    private final Map<Class<?>, PublishSubject<Result<T>>> subjects = new HashMap<>();

    @Override
    public Observable.Transformer<Result<T>, Result<T>> on(Class<T> itemClass) {
        PublishSubject<Result<T>> subject = PublishSubject.create();
        subjects.put(itemClass, subject);
        return  observable -> subject;
    }

    public void emitSuccessfulResultFor(Class<T> itemClass, T item) {
        PublishSubject<Result<T>> subject = subjects.get(itemClass);
        subject.onNext(Result.response(Response.success(item)));
        subject.onCompleted();
    }
}
