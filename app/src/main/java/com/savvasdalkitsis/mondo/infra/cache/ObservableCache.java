package com.savvasdalkitsis.mondo.infra.cache;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;

public interface ObservableCache<T> {

    Observable.Transformer<Result<T>, Result<T>> on(Class<T> itemClass);
}