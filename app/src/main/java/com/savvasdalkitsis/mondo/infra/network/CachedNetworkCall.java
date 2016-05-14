package com.savvasdalkitsis.mondo.infra.network;

import com.savvasdalkitsis.mondo.model.Response;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.functions.Func1;

public interface CachedNetworkCall<F, T> {
    Observable.Transformer<Result<F>, Response<T>> withMapper(Func1<F, T> mapper, Class<F> itemClass);
}
