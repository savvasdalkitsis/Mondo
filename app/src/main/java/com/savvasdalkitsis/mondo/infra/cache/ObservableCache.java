package com.savvasdalkitsis.mondo.infra.cache;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;

public interface ObservableCache<T> {
    Observable<Result<T>> cache(Observable<Result<T>> observable, Class<T> itemClass);
}