package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.infra.cache.ObservableCache;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;

public class NoOpObservableCache<T> implements ObservableCache<T> {

    @Override
    public Observable.Transformer<Result<T>, Result<T>> on(Class<T> itemClass) {
        return observable -> observable;
    }
}
