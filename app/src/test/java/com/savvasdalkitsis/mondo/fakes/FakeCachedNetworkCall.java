package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.infra.network.CachedNetworkCall;
import com.savvasdalkitsis.mondo.model.Response;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.functions.Func1;

public class FakeCachedNetworkCall<F, T> implements CachedNetworkCall<F, T> {

    @Override
    public Observable.Transformer<Result<F>, Response<T>> withMapper(Func1<F, T> mapper, Class<F> itemClass) {
        return observable -> observable.map(result -> Response.success(mapper.call(result.response().body())));
    }
}
